package com.seoudi.pages.common;

import com.seoudi.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

/**
 * Sorting interactions on PLP.
 */
public class PLPSortComponent extends BasePage {

    private final By sortDropdown = By.cssSelector("select[name*='sort'], .sort-select"); // TODO: Update locator after inspecting the live site.

    public String getSelectedOption() {
        Select select = new Select(waitForVisibility(sortDropdown));
        return select.getFirstSelectedOption().getText();
    }

    public void selectSortOptionByVisibleText(String option) {
        Select select = new Select(waitForVisibility(sortDropdown));
        select.selectByVisibleText(option);
    }
}
