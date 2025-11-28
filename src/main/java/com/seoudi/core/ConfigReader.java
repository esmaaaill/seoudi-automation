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

            if (inputStream != null) {
                PROPERTIES.load(inputStream);
            } else {
                throw new IllegalStateException("config.properties not found in classpath");
            }
           main
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
        return get("browser");
    }

    public static boolean isHeadless() {
        String headless = get("headless");
        return headless != null && Boolean.parseBoolean(headless);

 codex/generate-complete-ui-test-automation-framework-8pf3uv
    public static boolean isHeadless() {
        String headless = get("headless");
        return headless == null || headless.isBlank() || Boolean.parseBoolean(headless);
        main
    }

    public static String getGeckoDriverPath() {
        return get("geckoDriverPath");
    }

    public static String getFirefoxBinary() {
        return get("firefoxBinary");
    }

    public static String getValidEmail() {
        return get("validEmail");
    }

    public static String getValidPassword() {
        return get("validPassword");
    }
}
