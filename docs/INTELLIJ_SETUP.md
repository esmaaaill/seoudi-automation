# IntelliJ IDEA setup for Seoudi UI automation

Follow these steps to get Firefox-based execution, Cucumber + TestNG tooling, and local reports without the flaky configuration errors.

## 1) Install the right plugins
- **TestNG**: File → Settings → Plugins → Marketplace → install *TestNG-J* (or JetBrains TestNG support).
- **Gherkin & Cucumber for Java**: install both plugins so feature files highlight correctly and Cucumber glue can be generated.
- Restart IntelliJ after installing.

## 2) Provide a real Firefox binary
The container image may not ship with Firefox; point the framework to a local installation so GeckoDriver can launch it.
- Install Firefox locally (Mozilla download or your OS package manager).
- Note the executable path, e.g. `/usr/bin/firefox` on Linux, `/Applications/Firefox.app/Contents/MacOS/firefox` on macOS, or `C:\Program Files\Mozilla Firefox\firefox.exe` on Windows.
- Set one of the following:
  - Edit `src/test/resources/config.properties` and set `firefoxBinary=<full path>` and keep `browser=firefox`.
  - Or export an environment variable before running Maven: `export FIREFOX_BINARY="<full path>"`.
  - If GeckoDriver is blocked from downloading, also set `GECKO_DRIVER_PATH` to your local `geckodriver` binary.

The framework reads `BROWSER`, `HEADLESS`, `FIREFOX_BINARY`, and `GECKO_DRIVER_PATH` environment variables first, so you can switch browsers without editing files.

## 3) Run tests from IntelliJ
- Import the project as a Maven project (it targets Java 17).
- Right-click `testng.xml` → "Run" to execute the suite, or use Maven tool window → Lifecycle → `test`.
- Ensure **Run with Maven** uses the same environment variables if you set them in step 2.

## 4) Reports
- **TestNG HTML report**: generated at `target/surefire-reports/index.html` after each run.
- **Cucumber report (if you add feature files + glue)**: add the Cucumber JVM TestNG runner with `plugin = {"pretty", "html:target/cucumber-report.html", "json:target/cucumber.json"}` and the files will appear under `target/`.
- Open the HTML files in your browser to review pass/fail details.

## 5) Troubleshooting quick wins
- Browser fails to start → verify `FIREFOX_BINARY` points to a real executable and that `geckodriver` is reachable on your PATH or via `GECKO_DRIVER_PATH`.
- Tests skip because base URL cannot load → confirm internet connectivity and that `baseUrl` in `config.properties` is reachable.
- To debug visually, set `headless=false` (or `HEADLESS=false`) so the Firefox window opens during the run.
