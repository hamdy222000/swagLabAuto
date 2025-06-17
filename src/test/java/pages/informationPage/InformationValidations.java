package pages.informationPage;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.loginPage.LoginPage;
import utils.Data.CartData;
import utils.Data.InformationData;
import utils.Data.OverviewData;
import utils.Waits;

public class InformationValidations {

    InformationPage informationPage;
    WebDriver driver;
    Waits wait ;

    //===Constructor===//
    public InformationValidations(InformationPage informationPage){
        this.informationPage = informationPage;
        this.driver = informationPage.driver;
        this.wait = informationPage.wait;
    }


    //==== Internal Assertions Helpers ====//
    InformationValidations verifyUrlChange(String nextUrl , String pageName){
        wait.urlChangeFrom(InformationData.InformationUrl);
        Assert.assertEquals(driver.getCurrentUrl() , nextUrl , "not redirected to '" + pageName + "' page.");
        return this;
    }

    InformationValidations verifyErrorMessage(String expectedMessage){
        if(!wait.urlChangeFrom(InformationData.InformationUrl)) {
            Assert.assertEquals(informationPage.getErrorMessage(), expectedMessage, "text of error message is not as expected, ");
        }
        else {
            Assert.fail("redirected to : " + driver.getCurrentUrl());
        }
        return this;
    }


    //====Validations====//
    public InformationValidations verifyContinueToOverView(){
        return verifyUrlChange(OverviewData.OverviewUrl , "Overview");
    }

    public InformationValidations verifyBackToCart(){
        return verifyUrlChange(CartData.CartUrl , "Cart");
    }

    public InformationValidations verifyEmptyFirstName(){
        return verifyErrorMessage(InformationData.BlankFirstNameErrorMessage);
    }

    public InformationValidations verifyEmptyLastName(){
        return verifyErrorMessage(InformationData.BlankLastNameErrorMessage);
    }

    public InformationValidations verifyEmptyPostalCode(){
        return verifyErrorMessage(InformationData.BlankPostalCodeErrorMessage);
    }

    public InformationValidations verifyInvalidInfo(){
        if(wait.urlChangeFrom(InformationData.InformationUrl)) Assert.fail("Url change to : " + driver.getCurrentUrl());
        return this;
    }


}
