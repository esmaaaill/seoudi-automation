package com.seoudi.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

/**
 * Manages WebDriver instances in a thread-safe manner for parallel execution.
 */
public class DriverFactory {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverFactory() {
        // Utility class
    }

    public static void initDriver() {
        String browser = ConfigReader.getBrowser();
        if (browser == null || browser.isBlank() || browser.equalsIgnoreCase("firefox")) {
            String driverPath = ConfigReader.getGeckoDriverPath();
            if (driverPath != null && !driverPath.isBlank()) {
                System.setProperty("webdriver.gecko.driver", driverPath);
            } else {
                WebDriverManager.firefoxdriver().setup();
            }

            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--width=1920", "--height=1080");
            options.addArguments("--disable-gpu");
            if (ConfigReader.isHeadless()) {
                options.addArguments("-headless");
            }

            WebDriver driver = new FirefoxDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
            driver.manage().deleteAllCookies();
            DRIVER.set(driver);
        } else {
            throw new UnsupportedOperationException("Browser not supported yet: " + browser);
        }
    }

    public static WebDriver getDriver() {
        return DRIVER.get();
    }

    public static void quitDriver() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }
}
