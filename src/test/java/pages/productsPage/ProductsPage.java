package pages.productsPage;

import utils.driverManager.NewDriver;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.Data.products.Filter;
import utils.Data.products.Product;
import utils.Data.products.ProductsData;
import utils.Logs;
import utils.Waits;

import java.util.ArrayList;
import java.util.List;


public class ProductsPage {

     WebDriver driver;
    Waits wait;
    //===Locators===//
     final By filterIconLocator = By.cssSelector(".select_container");
     final By dropDownListLocator = By.cssSelector(".product_sort_container");
     final By titleLocator = By.cssSelector(".inventory_item_name ");
     final By priceLocator = By.cssSelector(".inventory_item_price") ;
     final By productBtnLocator = By.cssSelector(".pricebar button");
     final By cartIconLocator = By.cssSelector(".shopping_cart_container a");
     final By noOfProductsAddedIconLocator = By.cssSelector(".shopping_cart_container a span");
     final By resetLocator = By.id("reset_sidebar_link");
    final By logoutLocator = By.id("logout_sidebar_link");
     final By menuBtnLocator = By.id("react-burger-menu-btn");


     int expectedNoOfProductsAddedToCart = 0;
     List<String> selectedItems = new ArrayList<>();


    //===Constructor===//
    public ProductsPage(){

        driver = NewDriver.getDriver();
        wait = new Waits();
    }

    //===Helper methods===//
    public String getProductTitle(int productIndex){
        return find(titleLocator , productIndex).getText();
    }


    public List<String> getSelectedItems() {
        return selectedItems;
    }

    WebElement find(By locator){
        wait.elementToBePresent(locator,true);
        return driver.findElement(locator);
    }

     WebElement find(By locator , int index){
        wait.elementsToBePresent(locator,true);
        return driver.findElements(locator).get(index);
    }


     ProductsPage redirectProductsPage(){
        if(!driver.getCurrentUrl().equals(ProductsData.ProductsUrl)){
            driver.get(ProductsData.ProductsUrl);
        }
        return this;
    }

    void attachMessage(String message){
        Allure.step(message);
        Logs.info(message);
    }

     ProductsPage clickProductButton(int productIndex, boolean isAdd) {
        String action = isAdd ? "add to cart" : "remove";
        String buttonText = isAdd ? ProductsData.AddBtnTxt : ProductsData.RemoveBtnTxt;
        int delta = isAdd ? 1 : -1;

        attachMessage("click on " + action + " button of product '" + getProductTitle(productIndex) + "'.");
        wait.elementsToBeClickable(productBtnLocator, true);
        find(productBtnLocator, productIndex).click();
        wait.elementTextChange( productBtnLocator , productIndex , buttonText);
        expectedNoOfProductsAddedToCart += delta;
         if(isAdd){
             selectedItems.add(getProductTitle(productIndex));
         }
         else {
             selectedItems.remove(getProductTitle(productIndex));

         }

        return this;
    }

    ProductsPage selectTypeOfFilter(int index , String filterMessage){
        attachMessage("select: " + filterMessage);
        wait.elementToBeVisible(dropDownListLocator,true);
        WebElement dropDownList = find(dropDownListLocator);
        new Select(dropDownList).selectByIndex(index);
        return this;
    }


    ProductsPage clickDropDownFilterList(){
        attachMessage("click on filter icon.");
        wait.elementToBeClickable(filterIconLocator , true);
        find(filterIconLocator).click();
        return this;
    }

    ProductsPage clickMenuBtn(){
        attachMessage("click on menu button.");
        wait.elementToBeClickable(menuBtnLocator,true);
        find(menuBtnLocator).click();
        return this;
    }

    ProductsPage selectOption(By locator , String optionName){
        attachMessage("select: '" + optionName + "'" );
        wait.elementToBeClickable(locator,true);
        find(locator).click();
        return this;
    }

    String getFilterMessage(int filterIndex){
        String filterMessage;
        if (filterIndex == Filter.A_TO_Z.getValue()) {
            filterMessage = Filter.A_TO_Z.getMessage();
        } else if (filterIndex == Filter.Z_TO_A.getValue()) {
            filterMessage = Filter.Z_TO_A.getMessage();
        } else if (filterIndex == Filter.LOW_TO_HIGH.getValue()) {
            filterMessage = Filter.LOW_TO_HIGH.getMessage();
        } else if (filterIndex == Filter.HIGH_TO_LOW.getValue()) {
            filterMessage = Filter.HIGH_TO_LOW.getMessage();
        } else {
            filterMessage = null;
        }
        return filterMessage;
    }

    //===Test cases code===//
    public ProductsPage selectFilter(int index ){
        String filterMessage = getFilterMessage(index);
        wait.elementsToBePresent(titleLocator);
        String lastTitle = getProductTitle( Product.Number_One.getIndex());
        clickDropDownFilterList()
                .selectTypeOfFilter(index , filterMessage);
        wait.elementTextChange(titleLocator , lastTitle);
        return this;
    }

    public ProductsPage clickAddToCartBtn(int productIndex){
        return clickProductButton(productIndex , true);
    }

    public ProductsPage clickRemoveBtn(int productIndex){
        return clickProductButton(productIndex , false);
    }


    public ProductsPage clickCartIcon(){
        attachMessage("click on cart icon." );
        wait.elementToBeClickable(cartIconLocator,true);
        find(cartIconLocator).click();
        return this;
    }

    public ProductsPage resetApp(){
        redirectProductsPage()
                .clickMenuBtn().selectOption(resetLocator , "Reset App State");
        driver.navigate().refresh();
        expectedNoOfProductsAddedToCart = 0;
        return this;
    }

    public ProductsPage logout(){
        return clickMenuBtn().selectOption(logoutLocator , "logout");
    }




}
