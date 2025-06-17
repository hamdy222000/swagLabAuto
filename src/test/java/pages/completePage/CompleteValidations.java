package pages.completePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utils.Data.CompleteData;
import utils.Data.products.ProductsData;
import utils.Waits;

public class CompleteValidations {
    CompletePage completePage;
    WebDriver driver;
    Waits wait ;

    //===Constructor===//
    public CompleteValidations(CompletePage completePage){
        this.completePage = completePage;
        this.driver = completePage.driver;
        this.wait = completePage.wait;
    }


    //==== Internal Assertions Helpers ====//
    CompleteValidations verifyUrlChange(String nextUrl , String pageName){
        wait.urlChangeFrom(CompleteData.CompleteUrl);
        Assert.assertEquals(driver.getCurrentUrl() , nextUrl , "not redirected to '" + pageName + "' page.");
        return this;
    }

    //====Validations====//
    public CompleteValidations verifyCompleteSuccessfully(SoftAssert softAssert){
        completePage.attachMessage("Verify that the complete image is displayed");
        completePage.attachMessage("Verify that the complete message is displayed");
        By img = completePage.completeImageLocator;
        By message = completePage.completeMessageLocator;
        if(!wait.elementToBeVisible(img)){
            softAssert.fail("complete image is not appearance.");
        }
        else {
            String actualImgSrc = driver.findElement(img).getAttribute("src");
            softAssert.assertEquals(actualImgSrc , CompleteData.SrcImg , "src image is not as expected, ");
        }
        if(!wait.elementToBeVisible(message)){
            softAssert.fail("complete message is not appearance.");
        }
        else {
            String actualMessage = driver.findElement(message).getText();
            softAssert.assertEquals(actualMessage , CompleteData.CompleteSuccessfullyMessage , "complete message is not as expected, ");
        }
        return this;
    }

    public CompleteValidations verifyCompleteWithoutSelectProduct(SoftAssert softAssert){
        completePage.attachMessage("Verify that the complete successfully message is not displayed");
        By message = completePage.completeMessageLocator;
        if(wait.elementToBeVisible(message)){
            String actualMessage = driver.findElement(message).getText();
            softAssert.assertNotEquals(actualMessage , CompleteData.CompleteSuccessfullyMessage , "complete successfully message should not be displayed, ");
        }
        return this;
    }

    public CompleteValidations verifyBackHome(){
        return verifyUrlChange(ProductsData.ProductsUrl , "Products");
    }


    }
