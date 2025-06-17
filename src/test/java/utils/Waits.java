package utils;

import utils.driverManager.NewDriver;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import java.time.Duration;
import java.util.List;

public class Waits {
    private WebDriverWait wait;

    // ===== Constructors =====
    public Waits() {
        this.wait = new WebDriverWait(NewDriver.getDriver(), Duration.ofSeconds(1));
    }

    public Waits(int seconds) {
        this.wait = new WebDriverWait(NewDriver.getDriver(), Duration.ofSeconds(seconds));
    }

    // ===== Helper Method =====
    private boolean handleTimeout(String message, boolean skipTest) {
        Logs.info(message);
        Allure.step(message);
        if (skipTest) throw new SkipException(message);
        return false;
    }

    // ===== Clickable one element =====
    public boolean elementToBeClickable(By locator) {
        return elementToBeClickable(locator, false);
    }

    public boolean elementToBeClickable(By locator, boolean skipTest) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            return true;
        } catch (TimeoutException e) {
            return handleTimeout("Element not clickable: " + locator, skipTest);
        }
    }

    // ===== Clickable list of elements =====
    public boolean elementsToBeClickable(By locator) {
        return elementsToBeClickable(locator, false);
    }

    public boolean elementsToBeClickable(By locator, boolean skipTest) {
        if (!elementsToBeVisible(locator, true)) return false;
        List<WebElement> elementList = NewDriver.getDriver().findElements(locator);
        for (WebElement element : elementList) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(element));
            } catch (TimeoutException e) {
                return handleTimeout("Elements not clickable: " + locator, skipTest);
            }
        }
        return true;
    }

    // ===== Visibility one element  =====
    public boolean elementToBeVisible(By locator) {
        return elementToBeVisible(locator, false);
    }

    public boolean elementToBeVisible(By locator, boolean skipTest) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return handleTimeout("Element not visible: " + locator, skipTest);
        }
    }

    // ===== Visibility list of elements =====
    public boolean elementsToBeVisible(By locator) {
        return elementsToBeVisible(locator, false);
    }

    public boolean elementsToBeVisible(By locator, boolean skipTest) {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
            return true;
        } catch (TimeoutException e) {
            return handleTimeout("Elements not visible: " + locator, skipTest);
        }
    }

    // ===== Invisibility one element =====
    public boolean elementToBeInvisible(By locator) {
        return elementToBeInvisible(locator, false);
    }

    public boolean elementToBeInvisible(By locator, boolean skipTest) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return handleTimeout("Element not invisible: " + locator, skipTest);
        }
    }

    // ===== Invisibility list of elements =====
    public boolean elementsToBeInvisible(By locator) {
        return elementsToBeInvisible(locator, false);
    }

    public boolean elementsToBeInvisible(By locator, boolean skipTest) {
        if (!elementsToBePresent(locator, true)) return false;
        try {
            List<WebElement> elementList = NewDriver.getDriver().findElements(locator);
            wait.until(ExpectedConditions.invisibilityOfAllElements(elementList));
            return true;
        } catch (TimeoutException e) {
            return handleTimeout("Elements not invisible: " + locator, skipTest);
        }
    }

    // ===== Present one element =====
    public boolean elementToBePresent(By locator) {
        return elementToBePresent(locator, false);
    }

    public boolean elementToBePresent(By locator, boolean skipTest) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return handleTimeout("Element not present: " + locator, skipTest);
        }
    }

    // ===== Present list of elements =====
    public boolean elementsToBePresent(By locator) {
        return elementsToBePresent(locator, false);
    }

    public boolean elementsToBePresent(By locator, boolean skipTest) {
        try {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
            return true;
        } catch (TimeoutException e) {
            return handleTimeout("Elements not present: " + locator, skipTest);
        }
    }

    // ===== absent one element =====
    public boolean elementToBeAbsent( By locator ) {
        return elementToBeAbsent( locator  , false);
    }

    public boolean elementToBeAbsent(  By locator ,  boolean skipTest) {
        try {
            wait.until(driver -> {
                try {
                    return driver.findElements(locator).isEmpty();
                } catch (StaleElementReferenceException e) {
                    return false;
                }
            });
            return true;
        } catch (TimeoutException e) {
            return handleTimeout("element of locator '" + locator + "' still exists .", skipTest);
        }
    }

    // ===== absent list of elements =====
    public boolean elementsToBeAbsent( By locator ) {
        return elementToBeAbsent( locator  , false);
    }

    public boolean elementsToBeAbsent(  By locator ,  boolean skipTest) {
        return elementToBeAbsent(locator , skipTest);
    }

    // ===== element contain Text =====
    public boolean elementContainText(By locator, String text) {
        return elementContainText(locator, text, false);
    }

    public boolean elementContainText(By locator, String text, boolean skipTest) {
        try {
            wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
            return true;
        } catch (TimeoutException e) {
            return handleTimeout("Text not found in element: " + locator + " | Expected: " + text, skipTest);
        }
    }

    // ===== Alert =====
    public boolean handleAlert() {
        return handleAlert(false);
    }

    public boolean handleAlert(boolean skipTest) {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            NewDriver.getDriver().switchTo().alert().accept();
            return true;
        } catch (TimeoutException e) {
            return handleTimeout("Alert not present.", skipTest);
        }
    }

    // ===== URL Change =====
    public boolean urlChangeFrom(String currentUrl) {
        return urlChangeFrom(currentUrl, false);
    }

    public boolean urlChangeFrom(String currentUrl, boolean skipTest) {
        try {
            wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentUrl)));
            return true;
        } catch (TimeoutException e) {
            return handleTimeout("URL did not change from: " + currentUrl, skipTest);
        }
    }


    // ===== element text Change =====
    public boolean elementTextChange(By locator , String lastTxt) {
        return elementTextChange( locator , lastTxt , false);
    }

    public boolean elementTextChange(By locator, String lastTxt , boolean skipTest) {
        try {
            wait.until(driver -> {
                try {
                    String currentText = driver.findElement(locator).getText();
                    return !currentText.equals(lastTxt);
                } catch (StaleElementReferenceException e) {
                    return false;
                }
            });
            return true;
        } catch (TimeoutException e) {
            return handleTimeout("text of element of locator '" + locator + "' did not change from : '" + lastTxt + "' ", skipTest);
        }
    }

    // ===== element text of list Change =====
    public boolean elementTextChange(By locator , int elementIndex , String lastTxt) {
        return elementTextChange(locator , elementIndex , lastTxt , false);
    }

    public boolean elementTextChange(By locator , int elementIndex , String lastTxt , boolean skipTest) {
        try {
            wait.until(driver -> {
                try {
                    String currentText = driver.findElements(locator).get(elementIndex).getText();
                    return !currentText.equals(lastTxt);
                } catch (StaleElementReferenceException e) {
                    return false;
                }
            });
            return true;
        } catch (TimeoutException e) {
            return handleTimeout("text of element(" + (elementIndex+1) +") of locator '" + locator + "' did not change from : '" + lastTxt + "' ", skipTest);
        }
    }


}
