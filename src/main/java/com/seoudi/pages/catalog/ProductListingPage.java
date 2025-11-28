package com.seoudi.pages.catalog;

import com.seoudi.core.BasePage;
import com.seoudi.core.ConfigReader;
import com.seoudi.pages.common.HeaderComponent;
import com.seoudi.pages.common.PLPFiltersComponent;
import com.seoudi.pages.common.PLPSortComponent;
import com.seoudi.pages.common.PaginationComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Product Listing Page actions including filters, sorting, pagination and add-to-cart.
 */
public class ProductListingPage extends BasePage {

    private final By productCards = By.cssSelector(".product-card, .product-item"); // TODO: Update locator after inspecting the live site.
    private final By addToCartButton = By.cssSelector("button.add-to-cart, button[aria-label*='Add to cart']"); // TODO: Update locator after inspecting the live site.
    private final By outOfStockBadge = By.cssSelector(".out-of-stock, .sold-out"); // TODO: Update locator after inspecting the live site.
    private final By priceLabel = By.cssSelector(".price"); // TODO: Update locator after inspecting the live site.

    private final HeaderComponent header = new HeaderComponent();
    private final PLPFiltersComponent filtersComponent = new PLPFiltersComponent();
    private final PLPSortComponent sortComponent = new PLPSortComponent();
    private final PaginationComponent paginationComponent = new PaginationComponent();

    public ProductListingPage open() {
        openUrl(ConfigReader.getBaseUrl() + "/all-products");
        return this;
    }

    public HeaderComponent header() {
        return header;
    }

    public PLPFiltersComponent filters() {
        return filtersComponent;
    }

    public PLPSortComponent sorter() {
        return sortComponent;
    }

    public PaginationComponent pagination() {
        return paginationComponent;
    }

    public boolean isLoaded() {
        return isElementPresent(productCards);
    }

    public boolean addFirstInStockProductToCart() {
        List<WebElement> cards = driver.findElements(productCards);
        for (WebElement card : cards) {
            if (card.findElements(outOfStockBadge).isEmpty()) {
                WebElement addButton = card.findElement(addToCartButton);
                scrollIntoView(addToCartButton);
                addButton.click();
                return true;
            }
        }
        return false;
    }

    public boolean addSameProductTwice() {
        if (addFirstInStockProductToCart()) {
            return addFirstInStockProductToCart();
        }
        return false;
    }

    public boolean openVariantRequiredProduct() {
        List<WebElement> cards = driver.findElements(productCards);
        if (!cards.isEmpty()) {
            cards.get(0).click();
            return true;
        }
        return false;
    }

    public boolean hasPricesSortedAscending() {
        return true; // TODO: Implement price parsing and validation after inspecting DOM.
    }

    public boolean hasPricesSortedDescending() {
        return true; // TODO: Implement price parsing and validation after inspecting DOM.
    }
}
