package pages.cartPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utils.Data.CartData;
import utils.Data.InformationData;
import utils.Data.products.ProductsData;
import utils.Waits;

import java.util.List;

public class CartValidations {

    CartPage cartPage;
    WebDriver driver;
    Waits wait ;

    //===Constructor===//
    public CartValidations(CartPage cartPage){
        this.cartPage = cartPage;
        this.driver = cartPage.driver;
        this.wait = cartPage.wait;
    }


    //==== Internal Assertions Helpers ====//
     CartValidations verifyUrl(String nextUrl , String nextPageName){
        if (!wait.urlChangeFrom(CartData.CartUrl)){
            Assert.fail("url not change from : " + CartData.CartUrl);
        }
        Assert.assertEquals(driver.getCurrentUrl() , nextUrl ,
                "not directed to '" + nextPageName + "' page , ");
        return this;
    }

    By getItemTitleLocator(String productTitle){
        return By.xpath("//*[text()='"+productTitle+"']");
    }


    //====Validations====//
    public CartValidations verifySelectedItems(List<String> selectedItems){
        cartPage.attachMessage("Verify that the Cart page displays the same items that were added to the cart from Products page.");
        wait.elementsToBeVisible(cartPage.titleLocator , true);
        List<WebElement> elementList = driver.findElements(cartPage.titleLocator);
        Assert.assertEquals(elementList.size(),selectedItems.size(),
                "number of products added to cart not as expected , ");
        SoftAssert softAssert = new SoftAssert();
        for (String item : selectedItems){
            boolean found = false;
            for (WebElement element : elementList){
                if(item.equals(element.getText())){
                    found = true;
                    break;
                }
            }
            if(!found) {
                softAssert.fail("item '" + item + "' is not found in the cart.");
            }
        }
        softAssert.assertAll();
        return this;
    }

    public CartValidations verifyRemoveItems(List<String> removedItemsList,SoftAssert softAssert){
        for (String itemTitle : removedItemsList){
            if(!wait.elementToBeAbsent(getItemTitleLocator(itemTitle))){
                softAssert.fail("item '"+ itemTitle +"' not removed");
            }
        }
        return this;
    }


    public CartValidations verifyContinueShopping(){
        return verifyUrl(ProductsData.ProductsUrl, "information");
    }

    public CartValidations verifyCheckout(){
        return verifyUrl(InformationData.InformationUrl , "products");
    }



}
