package utils.driverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.Data.DriverData;


public class SelectDriver {

    public static WebDriver driverType(){
        switch (DriverData.DriverName.toLowerCase()){
            case "chrome" : return new ChromeDriver( new ChromeFactory().driverOptions());
            case "firefox" : return new FirefoxDriver(new FirefoxFactory().driverOptions());
            default : return new EdgeDriver(new EdgeFactory().driverOptions());
        }
    }





}
