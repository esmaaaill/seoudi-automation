package com.seoudi.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

/**
 * Provides typed access to runtime configuration from config.properties and environment variables.
 */
public final class ConfigReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigReader.class);
    private static final String CONFIG_FILE = "config.properties";
    private static final Properties PROPERTIES = loadProperties();

    private ConfigReader() {
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (inputStream == null) {
                throw new IllegalStateException(CONFIG_FILE + " not found in classpath");
            }
            properties.load(inputStream);
            LOGGER.info("Loaded configuration file: {}", CONFIG_FILE);
            return properties;
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to load " + CONFIG_FILE, exception);
        }
    }

    public static String getRequired(String key) {
        String value = PROPERTIES.getProperty(key);
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Missing required configuration key: " + key);
        }
        return value.trim();
    }

    public static String getOptional(String key) {
        String value = PROPERTIES.getProperty(key);
        return value == null ? null : value.trim();
    }

    public static String getBaseUrl() {
        return getRequired("baseUrl");
    }

    public static String getStoreSelectionUrl() {
        return getRequired("storeSelectionUrl");
    }

    public static String getBrowser() {
        return resolveWithEnvOverride("BROWSER", "browser").orElse("firefox");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(resolveWithEnvOverride("HEADLESS", "headless").orElse("true"));
    }

    public static String getGeckoDriverPath() {
        return resolveWithEnvOverride("GECKO_DRIVER_PATH", "geckoDriverPath").orElse(null);
    }

    public static String getFirefoxBinary() {
        return resolveWithEnvOverride("FIREFOX_BINARY", "firefoxBinary").orElse(null);
    }

    public static String getValidEmail() {
        return getOptional("validEmail");
    }

    public static String getValidPassword() {
        return getOptional("validPassword");
    }

    static Optional<String> resolveWithEnvOverride(String envKey, String propertyKey) {
        String envValue = System.getenv(envKey);
        if (envValue != null && !envValue.isBlank()) {
            return Optional.of(envValue.trim());
        }

        String propertyValue = getOptional(propertyKey);
        if (propertyValue != null && !propertyValue.isBlank()) {
            return Optional.of(propertyValue);
        }

        return Optional.empty();
    }
}
