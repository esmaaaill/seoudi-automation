package com.seoudi.core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Common reusable wrapper for WebDriver interactions.
 */
public abstract class BasePage {

    protected WebDriver driver;
    private final WebDriverWait wait;

    protected BasePage() {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    protected void openUrl(String url) {
        driver.get(url);
    }

    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void click(By locator) {
        waitForVisibility(locator).click();
    }

    protected void type(By locator, String text) {
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected boolean isDisplayed(By locator) {
        try {
            return waitForVisibility(locator).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected boolean isElementPresent(By locator) {
        try {
            List<WebElement> elements = driver.findElements(locator);
            return !elements.isEmpty();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected String getText(By locator) {
        return waitForVisibility(locator).getText();
    }

    protected void scrollIntoView(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void waitForPageTitle(String title) {
        wait.until(ExpectedConditions.titleContains(title));
    }
}
