package com.seoudi.pages.common;

import com.seoudi.core.BasePage;
import com.seoudi.pages.auth.LoginPage;
import org.openqa.selenium.By;

/**
 * Reusable header shared across pages.
 */
public class HeaderComponent extends BasePage {

    private final By signInLink = By.cssSelector("a[href*='login'], a[href*='signin']"); // TODO: Update locator after inspecting the live site.
    private final By searchInput = By.cssSelector("input[type='search']"); // TODO: Update locator after inspecting the live site.
    private final By miniCartIcon = By.cssSelector("a[href*='cart'], button[aria-label='cart']"); // TODO: Update locator after inspecting the live site.

    public LoginPage goToLogin() {
        click(signInLink);
        return new LoginPage();
    }

    public void search(String query) {
        type(searchInput, query);
        driver.findElement(searchInput).submit();
    }

    public boolean isMiniCartVisible() {
        return isDisplayed(miniCartIcon);
    }
}
