package com.seoudi.pages.catalog;

import com.seoudi.core.BasePage;
import org.openqa.selenium.By;

/**
 * Product details interactions.
 */
public class ProductDetailsPage extends BasePage {

    private final By addToCartButton = By.cssSelector("button.add-to-cart, button[aria-label*='Add to cart']"); // TODO: Update locator after inspecting the live site.
    private final By variantDropdown = By.cssSelector("select[id*='variant'], .variant-select"); // TODO: Update locator after inspecting the live site.
    private final By quantityIncrease = By.cssSelector("button[aria-label='Increase'], button.qty-plus"); // TODO: Update locator after inspecting the live site.

    public boolean selectFirstVariantIfAvailable() {
        if (isElementPresent(variantDropdown)) {
            driver.findElement(variantDropdown).click();
            driver.findElement(variantDropdown).sendKeys("\n");
            return true;
        }
        return false;
    }

    public void addToCart() {
        click(addToCartButton);
    }

    public void increaseQuantity() {
        click(quantityIncrease);
    }
}
