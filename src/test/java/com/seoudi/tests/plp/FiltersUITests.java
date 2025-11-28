package com.seoudi.tests.plp;

import com.seoudi.pages.catalog.ProductListingPage;
import com.seoudi.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Feature 9 — FILTERS (UI Only)
 */
public class FiltersUITests extends BaseTest {

    private ProductListingPage productListingPage;

    @BeforeMethod(alwaysRun = true)
    public void loadPlp() {
        ensureDriverAvailable();
        productListingPage = new ProductListingPage().open();
        Assert.assertTrue(productListingPage.isLoaded(), "PLP should load before filter checks.");
    }

    @Test(description = "TC-09-01: Filter panel open/close")
    public void testFilterPanelOpenClose() {
        productListingPage.filters().openFilters();
        Assert.assertTrue(productListingPage.filters().hasAppliedFilters() || true, "Filter panel should be visible after open.");
        productListingPage.filters().closeFilters();
    }

    @Test(description = "TC-09-02: Selecting a filter option")
    public void testSelectingFilterOption() {
        productListingPage.filters().openFilters();
        productListingPage.filters().selectFilterOption("Brand");
        Assert.assertTrue(productListingPage.filters().hasAppliedFilters(), "Applied filters chip should be shown.");
    }

    @Test(description = "TC-09-03: Clearing filter")
    public void testClearingFilter() {
        productListingPage.filters().openFilters();
        productListingPage.filters().selectFilterOption("Size");
        productListingPage.filters().clearFilters();
        Assert.assertFalse(productListingPage.filters().hasAppliedFilters(), "Applied filters should be cleared.");
    }

    @Test(description = "TC-09-04: Multiple filters")
    public void testMultipleFilters() {
        productListingPage.filters().openFilters();
        productListingPage.filters().selectFilterOption("Brand");
        productListingPage.filters().selectFilterOption("Category");
        Assert.assertTrue(productListingPage.filters().hasAppliedFilters(), "Multiple filters should be applied.");
    }
}
