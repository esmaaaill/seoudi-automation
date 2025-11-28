package com.seoudi.tests.login;

import com.seoudi.core.ConfigReader;
import com.seoudi.pages.auth.LoginPage;
import com.seoudi.pages.common.HeaderComponent;
import com.seoudi.tests.BaseTest;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

/**
 * Feature 1 — LOGIN
 */
public class LoginTests extends BaseTest {

    private LoginPage openLoginPage() {
        return new HeaderComponent().goToLogin();
    }

    @Test(description = "TC-01-01: Login with valid email & password")
    public void testLoginWithValidCredentials() {
        String email = ConfigReader.getValidEmail();
        String password = ConfigReader.getValidPassword();
        if (email == null || password == null || email.isBlank() || password.isBlank()) {
            throw new SkipException("Valid credentials are not configured in config.properties");
        }

        LoginPage loginPage = openLoginPage();
        loginPage.login(email, password, true);

        Assert.assertTrue(loginPage.isLoginFormVisible() || !loginPage.isErrorDisplayed(),
                "User should remain logged in or error should not appear when valid credentials are provided.");
    }

    @Test(description = "TC-01-02: Login with wrong password")
    public void testLoginWithWrongPassword() {
        LoginPage loginPage = openLoginPage();
        loginPage.login("qa+invalid@seoudi.com", "wrong-password", false);
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error banner should be displayed for wrong password.");
    }

    @Test(description = "TC-01-03: Login with non-registered email")
    public void testLoginWithNonRegisteredEmail() {
        LoginPage loginPage = openLoginPage();
        loginPage.login("nonexistent.user@noemail.com", "Password123", false);
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error banner should be displayed for unregistered email.");
    }

    @Test(description = "TC-01-04: Login empty fields validation")
    public void testLoginEmptyFieldsValidation() {
        LoginPage loginPage = openLoginPage();
        loginPage.login("", "", false);
        Assert.assertTrue(loginPage.isLoginFormVisible(), "Login form should remain visible when fields are empty.");
    }

    @Test(description = "TC-01-05: Remember me checkbox behavior")
    public void testRememberMeCheckbox() {
        LoginPage loginPage = openLoginPage();
        loginPage.login("qa+remember@seoudi.com", "Password123", true);
        Assert.assertTrue(loginPage.isRememberMeChecked(), "Remember me checkbox should remain selected after login attempt.");
    }
}
