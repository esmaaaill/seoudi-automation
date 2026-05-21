# Seoudi Market UI Automation Framework

A production-style Java UI test automation project that showcases clean architecture, maintainable OOP design, and quality engineering practices for enterprise teams.

## Tech Stack
- **Language:** Java 17
- **Build Tool:** Maven
- **UI Automation:** Selenium WebDriver 4
- **Test Frameworks:** TestNG (UI suites), JUnit 5 (unit tests)
- **Test Doubles:** Mockito
- **Driver Management:** WebDriverManager


## Architecture Overview
This repository follows a layered test automation architecture inspired by clean-code and SOLID principles:

- **Core Layer (`com.seoudi.core`)**
    - WebDriver lifecycle (`DriverFactory`)
    - Configuration management (`ConfigReader`)
    - Shared driver interaction abstractions (`BasePage`)
- **Page Layer (`com.seoudi.pages`)**
    - Page Object Model classes grouped by feature (auth/catalog/common)
    - Reusable components for filter/sort/pagination behavior
- **Test Layer (`com.seoudi.tests`)**
    - Test classes organized by business capabilities
    - Isolated test setup/teardown via `BaseTest`

Design principles demonstrated:
- Single Responsibility and separation of concerns
- Reusable components over duplicated locators/interactions
- Fail-fast configuration validation
- Parallel-safe WebDriver usage via `ThreadLocal`

## Project Structure
```text
src/
├─ main/
│  └─ java/com/seoudi/
│     ├─ core/
│     └─ pages/
│        ├─ auth/
│        ├─ catalog/
│        └─ common/
└─ test/
   ├─ java/com/seoudi/
   │  ├─ core/
   │  └─ tests/
   └─ resources/
```

## Setup & Installation
1. **Prerequisites**
   - JDK 17+
   - Maven 3.9+
   - Chrome and/or Firefox

2. **Clone and Build**
   ```bash
   git clone <your-repo-url>
   cd Automation-Testing
   mvn clean compile
   ```

3. **Configure Runtime Values**
   - Edit `src/test/resources/config.properties`
   - Optional overrides via environment variables:
      - `BROWSER`
      - `HEADLESS`
      - `FIREFOX_BINARY`
      - `GECKO_DRIVER_PATH`

## Running Tests
- **Run all tests (TestNG + JUnit):**
  ```bash
  mvn test
  ```
- **Run only JUnit unit tests:**
  ```bash
  mvn -Dtest="*ResolverTest" test
  ```
- **Run only TestNG UI suite:**
  ```bash
  mvn -Dtest="*Tests" test
  ```

## API Endpoints
This project currently targets **UI automation** only and does not expose backend APIs.

## QA Artifacts
- `TEST_PLAN.md` — scope, strategy, and execution approach.
- `TEST_CASES_ISMAIL.md` — mapped functional scenarios.
- `src/test/resources/testng.xml` — parallelized regression suite definition.

## Why this project is portfolio-ready
- Demonstrates enterprise Java test architecture and code organization.
- Uses modern Java and explicit design patterns (POM + component abstraction).
- Includes both automation suites and unit-level quality gates.
- Designed for team scaling and long-term maintainability.
