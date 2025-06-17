package pages.loginPage;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utils.Data.LoginData;
import utils.Data.products.ProductsData;
import utils.Waits;

public class LoginValidations {

    LoginPage loginPage;
    WebDriver driver;
    Waits wait ;

    //===Constructor===//
    public LoginValidations(LoginPage loginPage){
        this.loginPage = loginPage;
        this.driver = loginPage.driver;
        this.wait = loginPage.wait;
    }


    //==== Internal Assertions Helpers ====//
    LoginValidations verifyErrorMessage( String expectedErrorMessage , SoftAssert softAssert){
        wait.elementToBePresent(loginPage.errorMessageLocator , true);
        String actualErrorMessage = driver.findElement(loginPage.errorMessageLocator).getText();
        if (softAssert == null){
            Assert.assertEquals(actualErrorMessage , expectedErrorMessage);
        }
        else {
            softAssert.assertEquals(actualErrorMessage , expectedErrorMessage);
        }
        return this;
    }

    //====Validations====//

    public LoginValidations verifyLoginDoneSuccessfully() {
        wait.urlChangeFrom(LoginData.LoginUrl);
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = ProductsData.ProductsUrl;
        String message = "Failed to navigate to the products page.";
        Assert.assertEquals(actualUrl, expectedUrl, message);
        return this;
    }


    public LoginValidations verifyLoginUsingInvalidUsername(){
        return verifyErrorMessage(LoginData.InvalidUserErrorMessage , null);
    }

    public LoginValidations verifyLoginUsingInvalidUsername(SoftAssert softAssert){
        return verifyErrorMessage(LoginData.InvalidUserErrorMessage , softAssert);
    }


    public LoginValidations verifyLoginUsingInvalidPassword( ){
        return verifyErrorMessage(LoginData.InvalidUserErrorMessage , null);
    }

    public LoginValidations verifyLoginUsingInvalidPassword(SoftAssert softAssert){
        return verifyErrorMessage(LoginData.InvalidUserErrorMessage , softAssert);
    }

    public LoginValidations verifyLoginUsingInvalidUser(){
        return verifyErrorMessage(LoginData.InvalidUserErrorMessage , null);
    }

    public LoginValidations verifyLoginUsingInvalidUser(SoftAssert softAssert){
        return verifyErrorMessage(LoginData.InvalidUserErrorMessage , softAssert);
    }


    public LoginValidations verifyBlankUsernameNotAllow(){
        return verifyErrorMessage(LoginData.BlankUsernameErrorMessage , null);
    }

    public LoginValidations verifyBlankUsernameNotAllow(SoftAssert softAssert){
        return verifyErrorMessage(LoginData.BlankUsernameErrorMessage , softAssert);
    }

    public LoginValidations verifyBlankPasswordNotAllow(){
        return verifyErrorMessage(LoginData.BlankPasswordErrorMessage , null);
    }

    public LoginValidations verifyBlankPasswordNotAllow(SoftAssert softAssert){
        return verifyErrorMessage(LoginData.BlankPasswordErrorMessage , softAssert);
    }

}
