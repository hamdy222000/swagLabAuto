package pages.productsPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utils.Data.CartData;
import utils.Data.products.Product;
import utils.Data.products.ProductsData;
import utils.Waits;

import java.util.List;

public class ProductsValidations {
    ProductsPage productsPage;
    WebDriver driver;
    Waits wait ;

    //===Constructor===//
    public ProductsValidations(ProductsPage productsPage){
        this.productsPage = productsPage;
        this.driver = productsPage.driver;
        this.wait = productsPage.wait;
    }


    //==== Internal Assertions Helpers ====//
    WebElement findProductBtn(String productTitle){
        By btnLocator = By.xpath("//*[text()='"+productTitle+"']/following::button[1]");
        wait.elementToBeVisible(btnLocator,true);
        return driver.findElement(btnLocator);
    }

    ProductsValidations nameOrder(boolean isAscending , SoftAssert softAssert){
        wait.elementsToBePresent(productsPage.titleLocator , true);
        List<WebElement> elementList = driver.findElements(productsPage.titleLocator);
        String currentText;
        String lastText = elementList.get(Product.Number_One.getIndex()).getText();
        for (WebElement element : elementList){
            currentText = element.getText();
            if((isAscending && currentText.compareToIgnoreCase(lastText) < 0) || (!isAscending && currentText.compareToIgnoreCase(lastText) > 0)){
                String assertionMessage = "Wrong order: '" + currentText + "' came after '" + lastText + "'";
                if(softAssert == null){
                    Assert.fail(assertionMessage);
                }
                else {
                    softAssert.fail(assertionMessage);
                }

            }
            else {
                lastText = currentText;
            }
        }
        return this;
    }

    ProductsValidations priceOrder(boolean isAscending , SoftAssert softAssert){
        wait.elementsToBePresent(productsPage.priceLocator , true);
        List<WebElement> elementList = driver.findElements(productsPage.priceLocator);
        double currentPrice;
        double lastPrice = Double.parseDouble(elementList.get(Product.Number_One.getIndex()).getText().split("\\$")[1]);
        for (WebElement element : elementList){
            currentPrice = Double.parseDouble(element.getText().split("\\$")[1]);
            if((isAscending && currentPrice < lastPrice) || (!isAscending && currentPrice > lastPrice)){
                String assertionMessage = "Wrong order: product with price '" + currentPrice + "$' came after product with price '" + lastPrice + "$'.";
                if(softAssert == null){
                    Assert.fail(assertionMessage);
                }
                else {
                    softAssert.fail(assertionMessage);
                }
            }
            else {
                lastPrice = currentPrice;
            }
        }
        return this;
    }

    //====Validations====//
    public ProductsValidations verifyFilterAtoZ(){
        nameOrder(true , null);
        return this;
    }

    public ProductsValidations verifyFilterAtoZ(SoftAssert softAssert){
        nameOrder(true , softAssert);
        return this;
    }

    public ProductsValidations verifyFilterZtoA(){
        nameOrder(false , null);
        return this;
    }

    public ProductsValidations verifyFilterZtoA(SoftAssert softAssert){
        nameOrder(false , softAssert);
        return this;
    }


    public ProductsValidations verifyFilterLowToHigh(){
        priceOrder(true , null);
        return this;
    }

    public ProductsValidations verifyFilterLowToHigh(SoftAssert softAssert){
        priceOrder(true , softAssert);
        return this;
    }

    public ProductsValidations verifyFilterHighToLow(){
        priceOrder(false , null);
        return this;
    }

    public ProductsValidations verifyFilterHighToLow(SoftAssert softAssert){
        priceOrder(false , softAssert);
        return this;
    }

    public ProductsValidations verifyAddToCartBtn(int productIndex , SoftAssert softAssert){
        softAssert.assertEquals(productsPage.find(productsPage.productBtnLocator , productIndex).getText() ,
                ProductsData.RemoveBtnTxt ,
                "button text of product '" + productsPage.getProductTitle(productIndex) + "' did not change from '" + ProductsData.AddBtnTxt + "'.") ;

        wait.elementToBeVisible(productsPage.noOfProductsAddedIconLocator , true);
        if ((productsPage.expectedNoOfProductsAddedToCart-1) >= 1){
            String lastTxt = Integer.toString(productsPage.expectedNoOfProductsAddedToCart-1);
            wait.elementTextChange(productsPage.productBtnLocator , lastTxt);
        }
        int actualNoOfProductsAddedToCart = Integer.parseInt(driver.findElement(productsPage.noOfProductsAddedIconLocator).getText());
        softAssert.assertEquals( actualNoOfProductsAddedToCart ,
                productsPage.expectedNoOfProductsAddedToCart ,
                "The number of products added was not as expected." );

        return this;
    }

    public ProductsValidations verifyRemoveBtn(int productIndex , SoftAssert softAssert){
        softAssert.assertEquals(productsPage.find(productsPage.productBtnLocator , productIndex).getText() ,
                ProductsData.AddBtnTxt ,
                "button text of product '" + productsPage.getProductTitle(productIndex) + "' did not change from '" + ProductsData.RemoveBtnTxt + "'.") ;

        if ((productsPage.expectedNoOfProductsAddedToCart+1) > 1){
            wait.elementToBeVisible(productsPage.noOfProductsAddedIconLocator , true);
            String lastTxt = Integer.toString(productsPage.expectedNoOfProductsAddedToCart+1);
            wait.elementTextChange(productsPage.productBtnLocator , lastTxt);
            int actualNoOfProductsAddedToCart = Integer.parseInt(driver.findElement(productsPage.noOfProductsAddedIconLocator).getText());
            softAssert.assertEquals( actualNoOfProductsAddedToCart ,
                    productsPage.expectedNoOfProductsAddedToCart ,
                    "The number of products added was not as expected." );
        }
        else {
            if(!wait.elementToBeAbsent(productsPage.noOfProductsAddedIconLocator)){
                softAssert.fail("Number of products added to cart icon still exists after remove all products from cart.");
            }
        }
        return this;

    }

    public ProductsValidations verifyProductsRemoved( List<String> productsTitle, SoftAssert softAssert) {
        for (String title : productsTitle) {
            WebElement productBtn = findProductBtn(title);
            softAssert.assertEquals(productBtn.getText(), ProductsData.AddBtnTxt,
                    "button text of product '" + title + "' did not change from '" + ProductsData.RemoveBtnTxt + "'.");

        }
        return this;
    }



    public ProductsValidations verifyResetApp(){
        productsPage.attachMessage("Verify reset app.");
        SoftAssert softAssert = new SoftAssert();
        verifyFilterAtoZ(softAssert);
        List<WebElement> elementList = driver.findElements(productsPage.productBtnLocator);
        for (WebElement element : elementList){
            softAssert.assertEquals(element.getText() , ProductsData.AddBtnTxt , "Expected button text mismatch after reset.");
        }
        if(!wait.elementToBeAbsent(productsPage.noOfProductsAddedIconLocator)){
            softAssert.fail("Number of products added to cart icon still exists after reset.");
        }
        softAssert.assertAll();
        return this;
    }


    public ProductsValidations verifyViewCart(){
        wait.urlChangeFrom(ProductsData.ProductsUrl);
        Assert.assertEquals(driver.getCurrentUrl() , CartData.CartUrl);
        return this;
    }


}

