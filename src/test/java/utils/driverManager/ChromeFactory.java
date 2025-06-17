package utils.driverManager;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.Data.DriverData;

public class ChromeFactory extends AbstractDriver<ChromeOptions>{


    @Override
    public  ChromeOptions driverOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();


        if(DriverData.IsHeadless){
            chromeOptions.addArguments("--headless=new");                // uses the new Chromium-based headless mode
        }
        chromeOptions.addArguments("--start-maximized");                 // Start browser maximized
        chromeOptions.addArguments("--disable-notifications");           // Disable browser notifications
        chromeOptions.addArguments("--disable-extensions");              // Disable Chrome extensions
        chromeOptions.addArguments("--no-sandbox");                      // Required for some CI environments
        chromeOptions.addArguments("--disable-dev-shm-usage");           // Fix shared memory issues (Linux/Docker)
        chromeOptions.addArguments("--disable-blink-features=AutomationControlled"); // Hide "Chrome is being controlled" message

        chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); // Disable automation banner
        chromeOptions.setExperimentalOption("useAutomationExtension", false);                      // Disable automation extension

        chromeOptions.setAcceptInsecureCerts(true);                      // Accept insecure SSL certificates
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);      // Wait for full page load (use EAGER/NONE to speed up)


        return chromeOptions;
    }
}
