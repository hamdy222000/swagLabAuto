package utils;

import utils.driverManager.NewDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class Scroll {
    public static void toElement(By locator) {
        WebElement element = NewDriver.getDriver().findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) NewDriver.getDriver();
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    }
}

