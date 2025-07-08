# Swag Labs Test Automation

This project is a simple and maintainable Selenium-based UI test automation framework for the [Swag Labs](https://www.saucedemo.com) demo site.  
It is built using Java, Selenium WebDriver, and TestNG, and follows the Page Object Model (POM) design pattern.  
The framework supports parallel execution with ThreadLocal, external test data in JSON format, detailed logging via Log4j2, and advanced reporting using Allure.

---

## Features

- Page Object Model (POM) structure
- Parallel test execution using ThreadLocal WebDriver
- Logging using Log4j2
- External JSON files for test data and browser configuration
- Custom wait utility for element visibility, text, alerts, and URL
- Allure report integration
- Modular and scalable base class
- RetryAnalyzer for retrying flaky tests automatically
- Automatic attachment of logs and screenshots to Allure reports on test failure
- GitHub Actions integration for automated CI/CD on every push and pull request


---

## Technologies Used

- Java 23
- Maven
- Selenium WebDriver 4.33
- TestNG 7.8
- Allure Report (v2.24)
- Log4j2 (v2.24.3)
- JsonPath (for JSON handling)
- Commons IO (for file operations)

---

## How to Run the Tests

1. Clone the repository: 
```bash
git clone https://github.com/hamdy222000/swagLabAuto
```

2. Import the project into your IDE as a Maven project.

3. Make sure Java 23 and Maven are installed on your system.

4. Run the tests using Maven:
   
```bash
mvn clean test 
```
Or run a specific TestNG suite (e.g., regression.xml):

```bash
mvn clean test "-Dsurefire.suiteXmlFiles=regression.xml"
```

5. Generate and view the Allure report:
```bash
 allure serve testOut/allure-results
```
   Make sure Allure CLI is installed. [Installation guide](https://docs.qameta.io/allure/)

   
---

## Test Coverage

- Login: valid & invalid credentials  
- Products Page:
  - Product visibility  
  - Filter functionality  
  - Add/remove products  
  - Reset app state  
- Cart Page:
  - Cart content validation  
  - Product visibility in cart  
  - Removing products from cart  
- Checkout Information Page:
  - Form field validations (names, postal codes, etc.)  
- Overview & Completion Pages:
  - Order summary validation  
  - Confirmation message and finalization  
- Logout:
  - Logout process  
  - Validation of redirection  
- End-to-End Flow:
  - Full purchase journey validation (login → buy → confirmation → logout )


---

## CI/CD with GitHub Actions

This project uses **GitHub Actions** to run UI tests automatically on every `push` or `pull request` to the `main` branch.

###  Workflow Features:
- Runs tests using `mvn clean test -Dheadless=true`
- Uses Java 23 on Ubuntu latest runner
- Uploads **Allure Report results** as build artifacts
- Validates build status before allowing merge (if branch protection is enabled)

###  Workflow Summary:
```yaml
name: Run Swag_Labs Tests with Allure Report

on:
  push:
    branches: [ "main" ]
  pull_request:
    branches: [ "main" ]

jobs:
  ui-tests:
    name: Run Swag_Labs Tests
    runs-on: ubuntu-latest

    steps:
      - name: Checkout Repo
        uses: actions/checkout@v4

      - name: Set up Java 23
        uses: actions/setup-java@v4
        with:
          distribution: 'temurin'
          java-version: '23'

      - name: Run Swag_Labs Tests
        run:  mvn clean test -Dheadless=true

      - name: Archive Allure Results
        if: always()
        uses: actions/upload-artifact@v4
        with:
          name: allure-results
          path: testOut/allure-results
```

---

## Author
**Mahmoud Hamdy**  
ISTQB® Certified Tester – CTFL

