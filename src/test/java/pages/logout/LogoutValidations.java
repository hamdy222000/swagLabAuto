package pages.logout;

import org.testng.asserts.SoftAssert;
import utils.driverManager.NewDriver;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.Data.LoginData;
import utils.Data.products.ProductsData;
import utils.Logs;
import utils.Waits;

public class LogoutValidations {
    WebDriver driver;
    Waits wait ;

    //===Constructor===//
    public LogoutValidations(){
        this.driver = NewDriver.getDriver();
        this.wait = new Waits();
    }

    //==== Internal Assertions Helpers ====//
    LogoutValidations verifyUrlChange(String nextUrl , String pageName){
        wait.urlChangeFrom(ProductsData.ProductsUrl);
        Assert.assertEquals(driver.getCurrentUrl() , nextUrl , "not redirected to '" + pageName + "' page.");
        return this;
    }

    void attachMessage(String message){
        Allure.step(message);
        Logs.info(message);
    }

    //====Validations====//
    public LogoutValidations verifyLogoutDoneSuccessfully(){
        return verifyLogoutDoneSuccessfully(null);
    }

    public LogoutValidations verifyLogoutDoneSuccessfully(SoftAssert softAssert){
        attachMessage("Verify redirected to login page.");
        verifyUrlChange(LoginData.LoginUrl , "Login");
        attachMessage("try navigate back after logout. ");
        driver.navigate().back();
        attachMessage("Verify that the user cannot back to his account before login again. ");
        if(wait.urlChangeFrom(LoginData.LoginUrl)){
            String message = "Expected user to be required to login again after logout, but found redirected to: " + driver.getCurrentUrl();
            if (softAssert == null) Assert.fail(message);
            else softAssert.fail(message);
        }
         return this;
    }


}
