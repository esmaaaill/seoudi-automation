package com.seoudi.tests.plp;

import com.seoudi.pages.catalog.ProductListingPage;
import com.seoudi.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Feature 15 — PAGINATION / INFINITE SCROLL
 */
public class PaginationTests extends BaseTest {

    private ProductListingPage productListingPage;

    @BeforeMethod(alwaysRun = true)
    public void loadPlp() {
        ensureDriverAvailable();
        productListingPage = new ProductListingPage().open();
        Assert.assertTrue(productListingPage.isLoaded(), "PLP should load before pagination checks.");
    }

    @Test(description = "TC-15-01: Go to next page / scroll to load more")
    public void testGoToNextPage() {
        productListingPage.pagination().goToNextPage();
        Assert.assertTrue(productListingPage.isLoaded(), "Next page should display products.");
    }

    @Test(description = "TC-15-02: Go to specific page")
    public void testGoToSpecificPage() {
        productListingPage.pagination().goToPage(2);
        Assert.assertTrue(productListingPage.isLoaded(), "Specific page should load products.");
    }

    @Test(description = "TC-15-03: Boundary behavior (first/last page)")
    public void testBoundaryBehavior() {
        productListingPage.pagination().goToPage(1);
        boolean isPrevDisabled = productListingPage.pagination().isPreviousDisabled();
        productListingPage.pagination().goToNextPage();
        boolean isNextDisabled = productListingPage.pagination().isNextDisabled();
        Assert.assertTrue(isPrevDisabled || isNextDisabled, "At boundaries, navigation should be disabled appropriately.");
    }
}
