package com.seoudi.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
 codex/generate-complete-ui-test-automation-framework-8pf3uv
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
 main

import java.time.Duration;

/**
 * Manages WebDriver instances in a thread-safe manner for parallel execution.
 */
public class DriverFactory {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverFactory() {
        // Utility class
    }
 codex/generate-complete-ui-test-automation-framework-8pf3uv

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
            String firefoxBinary = ConfigReader.getFirefoxBinary();
            if (firefoxBinary != null && !firefoxBinary.isBlank()) {
                options.setBinary(firefoxBinary);
            }
            if (ConfigReader.isHeadless()) {
                options.addArguments("-headless");
            }

            WebDriver driver = new FirefoxDriver(options);


    public static void initDriver() {
        String browser = ConfigReader.getBrowser();
        if (browser == null || browser.isBlank() || browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--disable-gpu", "--no-sandbox");
            WebDriver driver = new ChromeDriver(options);
 main
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
