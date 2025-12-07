package com.seoudi.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
        if (browser == null || browser.isBlank()) {
            browser = "firefox";
        }

        WebDriver driver;
        switch (browser.toLowerCase()) {
            case "firefox":
                String driverPath = ConfigReader.getGeckoDriverPath();
                if (driverPath != null && !driverPath.isBlank()) {
                    System.setProperty("webdriver.gecko.driver", driverPath);
                } else {
                    WebDriverManager.firefoxdriver().setup();
                }

                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--width=1920", "--height=1080", "--disable-gpu");
                String firefoxBinary = ConfigReader.getFirefoxBinary();
                if (firefoxBinary != null && !firefoxBinary.isBlank()) {
                    firefoxOptions.setBinary(firefoxBinary);
                }
                if (ConfigReader.isHeadless()) {
                    firefoxOptions.addArguments("-headless");
                }

                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*", "--window-size=1920,1080", "--disable-gpu", "--no-sandbox");
                if (ConfigReader.isHeadless()) {
                    chromeOptions.addArguments("--headless=new");
                }
                driver = new ChromeDriver(chromeOptions);
                break;

            default:
                throw new UnsupportedOperationException("Browser not supported yet: " + browser);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().deleteAllCookies();
        DRIVER.set(driver);
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
