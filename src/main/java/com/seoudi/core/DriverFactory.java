package com.seoudi.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * Manages thread-safe WebDriver lifecycle for parallel execution.
 */
public final class DriverFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(DriverFactory.class);
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverFactory() {
    }

    public static void initDriver() {
        String browser = BrowserConfigResolver.resolveBrowser(ConfigReader.getBrowser(), "firefox");
        LOGGER.info("Initializing browser: {}", browser);

        WebDriver driver = switch (browser) {
            case "firefox" -> createFirefoxDriver();
            case "chrome" -> createChromeDriver();
            default -> throw new UnsupportedOperationException("Unsupported browser: " + browser);
        };

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().deleteAllCookies();
        DRIVER.set(driver);
    }

    private static WebDriver createFirefoxDriver() {
        String driverPath = ConfigReader.getGeckoDriverPath();
        if (driverPath != null && !driverPath.isBlank()) {
            System.setProperty("webdriver.gecko.driver", driverPath);
        } else {
            WebDriverManager.firefoxdriver().setup();
        }

        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--width=1920", "--height=1080", "--disable-gpu");

        String firefoxBinary = ConfigReader.getFirefoxBinary();
        if (firefoxBinary != null && !firefoxBinary.isBlank()) {
            options.setBinary(firefoxBinary);
        }

        if (ConfigReader.isHeadless()) {
            options.addArguments("-headless");
        }

        return new FirefoxDriver(options);
    }

    private static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*", "--window-size=1920,1080", "--disable-gpu", "--no-sandbox");

        if (ConfigReader.isHeadless()) {
            options.addArguments("--headless=new");
        }

        return new ChromeDriver(options);
    }

    public static WebDriver getDriver() {
        WebDriver driver = DRIVER.get();
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized. Call initDriver() first.");
        }
        return driver;
    }

    public static void quitDriver() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
            LOGGER.info("WebDriver session closed successfully.");
        }
    }
}
