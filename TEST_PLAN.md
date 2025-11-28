# Test Plan — Seoudi Supermarket UI Automation

## Scope
- UI regression and smoke automation for Seoudi Supermarket web storefront.
- Features covered (Engineer إسماعيل): Login (Feature 1), PLP add-to-cart (Feature 14), Filters UI (Feature 9), Sorting UI (Feature 10), Pagination/Infinite Scroll (Feature 15).
- Browser: Google Chrome (headless by default, configurable via `config.properties`).

## Out of Scope
- Native mobile apps.
- Payment gateway validations.
- Backend services and APIs.
- Email/SMS verification flows.
- Performance or load testing.

## Environments
- Prod URL: `https://seoudisupermarket.com/en`
- Store selection entry: `https://seoudisupermarket.com/en/select-store...`
- Test data: configured via `config.properties` (credentials optional for TC-01-01).

## Risks & Mitigations
- **Dynamic UI / locator drift**: Track TODO locators, validate after releases; centralize locators in POM classes.
- **Third-party dependencies (Chrome, WebDriver downloads)**: Use WebDriverManager; cache drivers in CI; allow headless fallback.
- **Network instability**: BaseTest wraps navigation; tests skip gracefully if site unreachable.
- **Test data unavailability**: Valid credential test skips when data missing.

## Test Approach
- **Design Pattern**: Page Object Model; component objects for shared widgets (header, filters, sort, pagination).
- **Test Runner**: TestNG with `parallel="methods"`, thread-count configurable in `testng.xml`.
- **Execution**: `mvn test` triggers Surefire with `src/test/resources/testng.xml`.
- **Reporting**: Default TestNG HTML report under `target/surefire-reports/index.html`.
- **Assertions**: Test-level assertions only; page methods return state/booleans.
- **Data**: Config-driven. Credentials are optional and stored outside VCS when sensitive.
- **CI/CD**: Maven-friendly structure; add to pipeline with `mvn test` and artifact collection.
