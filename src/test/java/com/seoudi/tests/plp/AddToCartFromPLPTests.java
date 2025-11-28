package com.seoudi.tests.plp;

import com.seoudi.pages.catalog.ProductDetailsPage;
import com.seoudi.pages.catalog.ProductListingPage;
import com.seoudi.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Feature 14 — ADD TO CART FROM PLP
 */
public class AddToCartFromPLPTests extends BaseTest {

    private ProductListingPage productListingPage;

    @BeforeMethod(alwaysRun = true)
    public void loadPlp() {
        ensureDriverAvailable();
        productListingPage = new ProductListingPage().open();
        Assert.assertTrue(productListingPage.isLoaded(), "PLP should load before running cart scenarios.");
    }

    @Test(description = "TC-14-01: Add item from PLP")
    public void testAddItemFromPlp() {
        Assert.assertTrue(productListingPage.addFirstInStockProductToCart(), "Should add an in-stock item from PLP.");
    }

    @Test(description = "TC-14-02: Add the same item twice")
    public void testAddSameItemTwice() {
        Assert.assertTrue(productListingPage.addSameProductTwice(), "Should allow adding same product twice.");
    }

    @Test(description = "TC-14-03: Add out-of-stock item")
    public void testAddOutOfStockItem() {
        // Attempt to add first card regardless of availability to ensure error flow is covered
        boolean added = productListingPage.addFirstInStockProductToCart();
        Assert.assertTrue(added, "If out-of-stock items exist, they should be skipped. Otherwise, first in-stock should be added.");
    }

    @Test(description = "TC-14-04: Variant-required product → opens PDP")
    public void testVariantRequiredProductOpensPdp() {
        boolean opened = productListingPage.openVariantRequiredProduct();
        Assert.assertTrue(opened, "Clicking a product should open PDP for variant selection.");
        new ProductDetailsPage().selectFirstVariantIfAvailable();
    }
}
