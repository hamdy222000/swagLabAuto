package utils.driverManager;

import org.openqa.selenium.WebDriver;

public class NewDriver {

    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();


    public static void setDriver(){
        driverThreadLocal.set(SelectDriver.driverType());
    }

    public static WebDriver getDriver(){
        return driverThreadLocal.get();
    }


    public static void quitDriver(){
        if (getDriver() != null) {
            getDriver().quit();
            driverThreadLocal.remove();
        }
    }

}
