package com.seoudi.pages.common;

import com.seoudi.core.BasePage;
import org.openqa.selenium.By;

/**
 * Pagination interactions.
 */
public class PaginationComponent extends BasePage {

    private final By nextButton = By.cssSelector("a[rel='next'], button.next"); // TODO: Update locator after inspecting the live site.
    private final By previousButton = By.cssSelector("a[rel='prev'], button.prev"); // TODO: Update locator after inspecting the live site.
    private final By pageNumberInput = By.cssSelector("input[name='page']"); // TODO: Update locator after inspecting the live site.

    public void goToNextPage() {
        if (isDisplayed(nextButton)) {
            click(nextButton);
        }
    }

    public void goToPreviousPage() {
        if (isDisplayed(previousButton)) {
            click(previousButton);
        }
    }

    public void goToPage(int pageNumber) {
        if (isDisplayed(pageNumberInput)) {
            type(pageNumberInput, String.valueOf(pageNumber));
            driver.findElement(pageNumberInput).submit();
        } else {
            By pageLink = By.xpath("//a[text()='" + pageNumber + "']"); // TODO: Update locator after inspecting the live site.
            click(pageLink);
        }
    }

    public boolean isNextDisabled() {
        return driver.findElement(nextButton).getAttribute("class").contains("disabled");
    }

    public boolean isPreviousDisabled() {
        return driver.findElement(previousButton).getAttribute("class").contains("disabled");
    }
}
