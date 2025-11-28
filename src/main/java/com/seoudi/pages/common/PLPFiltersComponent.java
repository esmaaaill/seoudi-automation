package com.seoudi.pages.common;

import com.seoudi.core.BasePage;
import org.openqa.selenium.By;

/**
 * Filter panel interactions on Product Listing Pages.
 */
public class PLPFiltersComponent extends BasePage {

    private final By filterToggle = By.cssSelector("button[aria-controls*='filters'], .filters-toggle"); // TODO: Update locator after inspecting the live site.
    private final By clearFilterButton = By.cssSelector("button.clear-filters, a.clear-all"); // TODO: Update locator after inspecting the live site.
    private final By appliedFilterChips = By.cssSelector(".filter-chip"); // TODO: Update locator after inspecting the live site.

    public void openFilters() {
        if (!isDisplayed(appliedFilterChips)) {
            click(filterToggle);
        }
    }

    public void closeFilters() {
        if (isDisplayed(filterToggle)) {
            click(filterToggle);
        }
    }

    public void selectFilterOption(String optionText) {
        By optionLocator = By.xpath("//label[contains(., '" + optionText + "')]//input"); // TODO: Update locator after inspecting the live site.
        click(optionLocator);
    }

    public void clearFilters() {
        if (isDisplayed(clearFilterButton)) {
            click(clearFilterButton);
        }
    }

    public boolean hasAppliedFilters() {
        return isElementPresent(appliedFilterChips);
    }
}
