package com.seoudi.tests.plp;

import com.seoudi.pages.catalog.ProductListingPage;
import com.seoudi.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Feature 10 — SORTING (UI Only)
 */
public class SortingUITests extends BaseTest {

    private ProductListingPage productListingPage;

   @BeforeMethod(alwaysRun = true)
    public void loadPlp() {
        ensureDriverAvailable();
        productListingPage = new ProductListingPage().open();
        Assert.assertTrue(productListingPage.isLoaded(), "PLP should load before sorting checks.");
    }

    @Test(description = "TC-10-01: Default sort")
    public void testDefaultSort() {
        String defaultSort = productListingPage.sorter().getSelectedOption();
        Assert.assertNotNull(defaultSort, "Default sort option should be selected.");
    }

    @Test(description = "TC-10-02: Sort by price low to high")
    public void testSortByPriceLowToHigh() {
        productListingPage.sorter().selectSortOptionByVisibleText("Price: Low to High");
        Assert.assertTrue(productListingPage.hasPricesSortedAscending(), "Prices should be sorted from low to high.");
    }

    @Test(description = "TC-10-03: Sort by price high to low")
    public void testSortByPriceHighToLow() {
        productListingPage.sorter().selectSortOptionByVisibleText("Price: High to Low");
        Assert.assertTrue(productListingPage.hasPricesSortedDescending(), "Prices should be sorted from high to low.");
    }

    @Test(description = "TC-10-04: Additional sort option")
    public void testAdditionalSortOption() {
        productListingPage.sorter().selectSortOptionByVisibleText("Newest");
        Assert.assertNotNull(productListingPage.sorter().getSelectedOption(), "Additional sort option should be selectable.");
    }
}
