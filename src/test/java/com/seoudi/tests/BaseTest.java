package com.seoudi.tests;

import com.seoudi.core.ConfigReader;
import com.seoudi.core.DriverFactory;
import org.openqa.selenium.WebDriver;
 codex/generate-complete-ui-test-automation-framework-8pf3uv

import org.testng.SkipException;
 main
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Base test class to manage WebDriver lifecycle and navigation.
 */
public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
 codex/generate-complete-ui-test-automation-framework-8pf3uv
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        ensureDriverAvailable();
        driver.get(ConfigReader.getBaseUrl());

        try {
            DriverFactory.initDriver();
            driver = DriverFactory.getDriver();
            ensureDriverAvailable();
            driver.get(ConfigReader.getBaseUrl());
        } catch (Exception e) {
            throw new SkipException("Skipping test because WebDriver failed to initialize or navigate: " + e.getMessage());
        }
 main
    }

    protected void ensureDriverAvailable() {
        if (driver == null) {
 codex/generate-complete-ui-test-automation-framework-8pf3uv
            throw new IllegalStateException("WebDriver was not initialized (null instance)");

            throw new SkipException("WebDriver was not initialized (null instance)");
 main
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
