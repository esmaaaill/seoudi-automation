# Local setup checklist

Follow this to run the suite locally after pulling the latest changes.

## 1) Install dependencies
- **Java 17** and **Maven 3.9+** available on your PATH.
- **Firefox** installed (the automation currently defaults to Firefox).
- Optional: **Chrome** if you plan to switch browsers via `BROWSER=chrome`.

## 2) Configure Firefox + GeckoDriver
- If Firefox is not on your PATH, point the framework to it with **one** of:
  - `FIREFOX_BINARY="<full path to firefox>"` environment variable, or
  - set `firefoxBinary=<full path>` inside `src/test/resources/config.properties`.
- If WebDriverManager cannot download GeckoDriver, set `GECKO_DRIVER_PATH` (or `geckoDriverPath` in `config.properties`) to your local `geckodriver` binary.
- Toggle headless mode via `HEADLESS=false` (or `headless=false` in `config.properties`) when you want to see the browser window.

## 3) Project configuration
- Keep `browser=firefox` in `config.properties` unless you explicitly want Chrome.
- Set `baseUrl` and `storeSelectionUrl` in `src/test/resources/config.properties` to the environments you will test.
- Optional: provide `validEmail` and `validPassword` if you intend to run login flows.

## 4) Run tests
- Command line: `mvn test` (or `mvn -Dsurefire.suiteXmlFiles=src/test/resources/testng.xml test` for a custom suite).
- IntelliJ: right-click `testng.xml` → **Run**. Ensure the Run Configuration inherits any environment variables you set above.

## 5) Reports
- **TestNG HTML**: `target/surefire-reports/index.html` after the run.
- **Cucumber** (if/when feature files are added): configure plugins such as `"html:target/cucumber-report.html"` and `"json:target/cucumber.json"`; open the generated HTML locally.

## 6) Quick troubleshooting
- Browser fails to start → confirm `FIREFOX_BINARY` points to a real executable and that GeckoDriver is reachable (PATH or `GECKO_DRIVER_PATH`).
- Tests hang on navigation → verify `baseUrl` is reachable from your network.
- Need visible debugging → set `HEADLESS=false` and re-run.
