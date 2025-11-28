package com.seoudi.tests;

import com.seoudi.core.ConfigReader;
import com.seoudi.core.DriverFactory;
import org.openqa.selenium.WebDriver;


import org.testng.SkipException;
 main
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
 main
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
 main
    }

    protected void ensureDriverAvailable() {
        if (driver == null) {
            throw new IllegalStateException("WebDriver was not initialized (null instance)");

            throw new IllegalStateException("WebDriver was not initialized (null instance)");

            throw new SkipException("WebDriver was not initialized (null instance)");
 main
 main
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
