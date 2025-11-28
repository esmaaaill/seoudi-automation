package com.seoudi.pages.auth;

import com.seoudi.core.BasePage;
import com.seoudi.core.ConfigReader;
import org.openqa.selenium.By;

/**
 * Login page object.
 */
public class LoginPage extends BasePage {

    private final By emailInput = By.id("email"); // TODO: Update locator after inspecting the live site.
    private final By passwordInput = By.id("password"); // TODO: Update locator after inspecting the live site.
    private final By rememberMeCheckbox = By.cssSelector("input[type='checkbox'][name*='remember']"); // TODO: Update locator after inspecting the live site.
    private final By loginButton = By.cssSelector("button[type='submit'], button.login"); // TODO: Update locator after inspecting the live site.
    private final By errorBanner = By.cssSelector(".alert-danger, .message-error"); // TODO: Update locator after inspecting the live site.

    public LoginPage open() {
        openUrl(ConfigReader.getBaseUrl() + "/login");
        return this;
    }

    public LoginPage login(String email, String password, boolean rememberMe) {
        type(emailInput, email);
        type(passwordInput, password);
        if (rememberMe && !driver.findElement(rememberMeCheckbox).isSelected()) {
            click(rememberMeCheckbox);
        }
        click(loginButton);
        return this;
    }

    public boolean isErrorDisplayed() {
        return isElementPresent(errorBanner);
    }

    public boolean isRememberMeChecked() {
        return driver.findElement(rememberMeCheckbox).isSelected();
    }

    public boolean isLoginFormVisible() {
        return isDisplayed(emailInput) && isDisplayed(passwordInput);
    }
}
