package utils.driverManager;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.edge.EdgeOptions;
import utils.Data.DriverData;

import java.util.Map;

public class EdgeFactory extends AbstractDriver<EdgeOptions>{


    @Override
    public  EdgeOptions driverOptions() {
        EdgeOptions edgeOptions = new EdgeOptions();

        if(DriverData.IsHeadless){
            edgeOptions.addArguments("--headless=new");                // uses the same new Chromium-based headless mode as Chrome
        }
        edgeOptions.addArguments("--start-maximized");                 // Start browser maximized
        edgeOptions.addArguments("--disable-notifications");           // Disable browser notifications
        edgeOptions.addArguments("--disable-extensions");              // Disable all extensions
        edgeOptions.addArguments("--no-sandbox");                      // Required for some CI environments
        edgeOptions.addArguments("--disable-dev-shm-usage");           // Fix shared memory issues (Linux/Docker)
        edgeOptions.addArguments("--disable-blink-features=AutomationControlled"); // Hide "browser is being controlled" message
        edgeOptions.setExperimentalOption("prefs", Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false
        ));                                                             // Hide save password alert

        edgeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); // Disable automation banner
        edgeOptions.setExperimentalOption("useAutomationExtension", false);                      // Disable automation extension

        edgeOptions.setAcceptInsecureCerts(true);                      // Accept insecure SSL certificates
        edgeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);      // Wait for full page load (use EAGER/NONE to speed up)


        return edgeOptions;
    }
}
