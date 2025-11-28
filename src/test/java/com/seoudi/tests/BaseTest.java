package com.seoudi.tests;

import com.seoudi.core.ConfigReader;
import com.seoudi.core.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Base test class to manage WebDriver lifecycle and navigation.
 */
public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        ensureDriverAvailable();
        driver.get(ConfigReader.getBaseUrl());
    }

    protected void ensureDriverAvailable() {
        if (driver == null) {
            throw new IllegalStateException("WebDriver was not initialized (null instance)");
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
