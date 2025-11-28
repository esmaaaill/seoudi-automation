# Test Cases — Engineer إسماعيل

## Legend
- **Method**: Java test method mapping (package `com.seoudi.tests`).
- **Precondition**: Initial setup required before executing steps.

### Feature 1 — LOGIN
| TC ID | Title | Steps | Expected | Method |
| --- | --- | --- | --- | --- |
| TC-01-01 | Login with valid email & password | 1. Open site<br>2. Header → Login<br>3. Enter valid email/password<br>4. Enable Remember Me<br>5. Submit | User is authenticated and redirected or remains logged-in without error | `LoginTests.testLoginWithValidCredentials` |
| TC-01-02 | Login with wrong password | 1. Open site<br>2. Header → Login<br>3. Enter valid email + wrong password<br>4. Submit | Error banner appears; user stays on login | `LoginTests.testLoginWithWrongPassword` |
| TC-01-03 | Login with non-registered email | 1. Open site<br>2. Header → Login<br>3. Enter unknown email + password<br>4. Submit | Error message shown; access denied | `LoginTests.testLoginWithNonRegisteredEmail` |
| TC-01-04 | Login empty fields validation | 1. Open site<br>2. Header → Login<br>3. Leave fields empty<br>4. Submit | Validation triggers; login form stays visible | `LoginTests.testLoginEmptyFieldsValidation` |
| TC-01-05 | Remember me checkbox behavior | 1. Open site<br>2. Header → Login<br>3. Tick Remember Me<br>4. Submit credentials | Checkbox remains selected; session persists per app rules | `LoginTests.testRememberMeCheckbox` |

### Feature 14 — ADD TO CART FROM PLP
| TC ID | Title | Steps | Expected | Method |
| --- | --- | --- | --- | --- |
| TC-14-01 | Add item from PLP | 1. Open PLP<br>2. Add first in-stock item | Item added to cart (UI confirmation) | `AddToCartFromPLPTests.testAddItemFromPlp` |
| TC-14-02 | Add the same item twice | 1. Open PLP<br>2. Add an item<br>3. Add again | Quantity increases or duplicate allowed per rules | `AddToCartFromPLPTests.testAddSameItemTwice` |
| TC-14-03 | Add out-of-stock item | 1. Open PLP<br>2. Attempt add on first card | Out-of-stock item cannot be added; in-stock item succeeds | `AddToCartFromPLPTests.testAddOutOfStockItem` |
| TC-14-04 | Variant-required product → opens PDP | 1. Open PLP<br>2. Click product needing variant<br>3. Choose variant if shown<br>4. Add to cart | PDP opens for variant selection; add-to-cart available | `AddToCartFromPLPTests.testVariantRequiredProductOpensPdp` |

### Feature 9 — FILTERS (UI Only)
| TC ID | Title | Steps | Expected | Method |
| --- | --- | --- | --- | --- |
| TC-09-01 | Filter panel open/close | 1. Open PLP<br>2. Expand filter panel<br>3. Collapse panel | Panel toggles visibility | `FiltersUITests.testFilterPanelOpenClose` |
| TC-09-02 | Selecting a filter option | 1. Open PLP<br>2. Expand filter panel<br>3. Select option (e.g., Brand) | Filter option is selected and chip appears | `FiltersUITests.testSelectingFilterOption` |
| TC-09-03 | Clearing filter | 1. Open PLP<br>2. Select a filter<br>3. Clear filters | No filters applied; chips removed | `FiltersUITests.testClearingFilter` |
| TC-09-04 | Multiple filters | 1. Open PLP<br>2. Select multiple filters | All filters show as applied | `FiltersUITests.testMultipleFilters` |

### Feature 10 — SORTING (UI Only)
| TC ID | Title | Steps | Expected | Method |
| --- | --- | --- | --- | --- |
| TC-10-01 | Default sort | 1. Open PLP | Default sort option visible/selected | `SortingUITests.testDefaultSort` |
| TC-10-02 | Sort by price low to high | 1. Open PLP<br>2. Choose low-to-high sort | Products arranged ascending by price | `SortingUITests.testSortByPriceLowToHigh` |
| TC-10-03 | Sort by price high to low | 1. Open PLP<br>2. Choose high-to-low sort | Products arranged descending by price | `SortingUITests.testSortByPriceHighToLow` |
| TC-10-04 | Additional sort option | 1. Open PLP<br>2. Choose another sort (Newest) | List updates with selected criteria | `SortingUITests.testAdditionalSortOption` |

### Feature 15 — PAGINATION / INFINITE SCROLL
| TC ID | Title | Steps | Expected | Method |
| --- | --- | --- | --- | --- |
| TC-15-01 | Go to next page / scroll to load more | 1. Open PLP<br>2. Trigger next page/scroll | Next set of products loads | `PaginationTests.testGoToNextPage` |
| TC-15-02 | Go to specific page | 1. Open PLP<br>2. Jump to page 2 | Page 2 products load | `PaginationTests.testGoToSpecificPage` |
| TC-15-03 | Boundary behavior (first/last page) | 1. Open PLP<br>2. Navigate to first page<br>3. Navigate to next/last | First/last navigation controls disabled when appropriate | `PaginationTests.testBoundaryBehavior` |
