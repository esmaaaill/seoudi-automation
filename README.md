# Seoudi Supermarket UI Test Automation

Full end-to-end UI automation framework for Seoudi Market / Seoudi Supermarket, authored in Java 17 with Selenium 4 and TestNG. The framework is built for scalability, Page Object Model consistency, and parallel execution for a six-engineer QA team. Engineer إسماعيل owns the login and catalog-plp features implemented here.

## Tech Stack
- Java 17
- Maven
- Selenium WebDriver 4
- TestNG 7
- WebDriverManager (Chrome)
- Page Object Model

## Project Layout
```
seoudi-automation/
  pom.xml
  testng.xml
  README.md
  TEST_PLAN.md
  TEST_CASES_ISMAIL.md
  .gitignore
  src/
    main/java/com/seoudi/core          # Driver, configuration, BasePage helpers
    main/java/com/seoudi/pages         # Page Objects and shared components
    test/java/com/seoudi/tests         # Test classes by feature
    test/resources                     # testng.xml + config.properties
```

## Getting Started
1. **Prerequisites**
   - JDK 17
   - Maven 3.9+
   - Internet access (for WebDriverManager to download ChromeDriver) and Google Chrome installed.

2. **Clone & Install**
   ```bash
   git clone <repo-url>
   cd seoudi-automation
   mvn -q -DskipTests compile
   ```

3. **Configure**
   - Update `src/test/resources/config.properties` for `baseUrl`, `storeSelectionUrl`, `browser` (chrome), and optionally credentials (`validEmail`, `validPassword`) for TC-01-01.

## Running Tests
- Default suite with parallel methods:
  ```bash
  mvn test
  ```
- Custom suite file:
  ```bash
  mvn -Dsurefire.suiteXmlFiles=src/test/resources/testng.xml test
  ```

## Parallel Execution
- `DriverFactory` uses `ThreadLocal<WebDriver>` and TestNG suite sets `parallel="methods"` with `thread-count="4"`.

## Reports
- TestNG HTML report is generated under `target/surefire-reports/index.html` after execution.

## Team Collaboration Notes
- Follow POM conventions: page methods describe user actions; avoid assertions inside page objects.
- Keep locators tidy; update `TODO` tags once verified on the live site.
- Add new features under `com.seoudi.pages` and `com.seoudi.tests` with matching package folders.
- Shared helpers belong in `com.seoudi.core` or `com.seoudi.pages.common`.
- Prefer headless Chrome for CI; adjust ChromeOptions in `DriverFactory` if full browser is required.

## Ownership
- Engineer إسماعيل: Login (Feature 1), Add to Cart from PLP (Feature 14), Filters (Feature 9), Sorting (Feature 10), Pagination (Feature 15).
- Framework is ready for the remaining engineers to extend with additional pages/tests.
