package pages.overviewPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utils.Data.CompleteData;
import utils.Data.OverviewData;
import utils.Data.products.ProductsData;
import utils.Waits;

import java.util.List;

public class OverviewValidations {

    OverviewPage overviewPage;
    WebDriver driver;
    Waits wait ;

    //===Constructor===//
    public OverviewValidations(OverviewPage overviewPage){
        this.overviewPage = overviewPage;
        this.driver = overviewPage.driver;
        this.wait = overviewPage.wait;
    }


    //==== Internal Assertions Helpers ====//
    OverviewValidations verifyUrlChange(String nextUrl , String pageName){
        wait.urlChangeFrom(OverviewData.OverviewUrl);
        Assert.assertEquals(driver.getCurrentUrl() , nextUrl , "not redirected to '" + pageName + "' page.");
        return this;
    }

    //====Validations====//
    public OverviewValidations verifySelectedItems(List<String> selectedItems ,  SoftAssert softAssert ){
        overviewPage.attachMessage("Verify that the Overview page displays the same items that were added to the cart.");
        wait.elementsToBeVisible(overviewPage.itemTitleLocator , true);
        List<WebElement> elementList = driver.findElements(overviewPage.itemTitleLocator);
        Assert.assertEquals(elementList.size(),selectedItems.size(),
                "number of products not as expected , ");
        for (String item : selectedItems){
            boolean found = false;
            for (WebElement element : elementList){
                if(item.equals(element.getText())){
                    found = true;
                    break;
                }
            }
            if(!found) {
                softAssert.fail("item '" + item + "' is not found in Overview page.");
            }
        }
        return this;
    }

    public OverviewValidations verifyTotalPrice(){
        return verifyTotalPrice(null);
    }

    public OverviewValidations verifyTotalPrice(SoftAssert softAssert){
        overviewPage.attachMessage("Verify that the total price displayed equals the sum of the individual item prices.");
        List<WebElement> itemPriceList = driver.findElements(overviewPage.itemPriceLocator);
        double actualTotalPrice = overviewPage.getTotalPrice();
        double expectedTotalPrice = 0;
        for (WebElement itemPrice : itemPriceList){
            expectedTotalPrice += Double.parseDouble(itemPrice.getText().split("\\$")[1]);
        }
        String message = "Total price is not as expected, ";
        if(softAssert == null){
            Assert.assertEquals(actualTotalPrice , expectedTotalPrice , message);
        }
        else {
            softAssert.assertEquals(actualTotalPrice , expectedTotalPrice , message);
        }
        return this;
    }


    public OverviewValidations verifyContinueToShopping(){
        return verifyUrlChange(ProductsData.ProductsUrl , "Products");
    }

    public OverviewValidations verifyRedirectedToCompletePage(){
        return verifyUrlChange(CompleteData.CompleteUrl , "Complete");
    }

}
