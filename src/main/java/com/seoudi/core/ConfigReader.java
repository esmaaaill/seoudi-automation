package com.seoudi.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utility to read configuration values from config.properties located under src/test/resources.
 */
public class ConfigReader {

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (inputStream == null) {
                throw new IllegalStateException("config.properties not found in classpath");
            }
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load config.properties", e);
        }
    }

    private ConfigReader() {
        // Utility class
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }

    public static String getBaseUrl() {
        return get("baseUrl");
    }

    public static String getStoreSelectionUrl() {
        return get("storeSelectionUrl");
    }

    public static String getBrowser() {
        String browser = System.getenv("BROWSER");
        if (browser != null && !browser.isBlank()) {
            return browser;
        }
        return get("browser");
    }

    public static boolean isHeadless() {
        String headless = System.getenv("HEADLESS");
        if (headless == null || headless.isBlank()) {
            headless = get("headless");
        }
        return headless != null && Boolean.parseBoolean(headless);
    }

    public static String getGeckoDriverPath() {
        String fromEnv = System.getenv("GECKO_DRIVER_PATH");
        if (fromEnv != null && !fromEnv.isBlank()) {
            return fromEnv;
        }
        return get("geckoDriverPath");
    }

    public static String getFirefoxBinary() {
        String binary = System.getenv("FIREFOX_BINARY");
        if (binary != null && !binary.isBlank()) {
            return binary;
        }
        return get("firefoxBinary");
    }

    public static String getValidEmail() {
        return get("validEmail");
    }

    public static String getValidPassword() {
        return get("validPassword");
    }
}
