package utils.driverManager;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import utils.Data.DriverData;

public class FirefoxFactory extends AbstractDriver<FirefoxOptions>{


    @Override
    public  FirefoxOptions driverOptions() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("dom.webnotifications.enabled", false); // Disable web notifications

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(profile);

        if(DriverData.IsHeadless){
            firefoxOptions.addArguments("-headless");                    //  uses its official headless flag
        }
        firefoxOptions.addArguments("--width=1920");                     // Set window width
        firefoxOptions.addArguments("--height=1080");                    // Set window height
        firefoxOptions.addArguments("--disable-extensions");             // Disable all extensions
        firefoxOptions.addArguments("--no-sandbox");                     // Required for some CI environments
        firefoxOptions.addArguments("--disable-dev-shm-usage");          // Fix shared memory issues (Linux/Docker)

        firefoxOptions.setAcceptInsecureCerts(true);                     // Accept insecure SSL certificates
        firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);     // Wait for full page load


        return firefoxOptions;
    }
}
