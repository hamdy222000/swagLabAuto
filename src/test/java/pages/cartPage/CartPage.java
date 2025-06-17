package pages.cartPage;

import utils.driverManager.NewDriver;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Data.CartData;
import utils.Logs;
import utils.Waits;

import java.util.ArrayList;
import java.util.List;

public class CartPage {

    WebDriver driver;
    Waits wait;

    //===Locators===//
    final By titleLocator = By.cssSelector(".inventory_item_name ");
    final By itemBtnLocator = By.cssSelector(".item_pricebar button");
    final By continueShoppingLocator = By.id("continue-shopping");
    final By checkoutLocator = By.id("checkout");



    List<String> removedItems = new ArrayList<>();
    List<String> selectedItems = new ArrayList<>();

    //===Constructor===//
    public CartPage(){
        driver = NewDriver.getDriver();
        wait = new Waits();
    }

    //===Helper methods===//
    String getItemTitle(int productIndex){
        WebElement titleWebElement =  find(titleLocator , productIndex);
        return (titleWebElement == null) ? null : titleWebElement.getText();
    }

    public List<String> getRemovedItems(){
        return removedItems;
    }
    public List<String> getSelectedItems(){
        return selectedItems;
    }

    WebElement find(By locator){
        wait.elementToBePresent(locator,true);
        return driver.findElement(locator);
    }

    WebElement find(By locator , int index){
        wait.elementsToBePresent(locator,true);
        if (index+1 > driver.findElements(locator).size()){
            return null;
        }
        return driver.findElements(locator).get(index);
    }

    void attachMessage(String message){
        Allure.step(message);
        Logs.info(message);
    }

    WebElement findItemRemoveBtn(String productTitle){
        By btnLocator = By.xpath("//*[text()='"+productTitle+"']/following::button[1]");
        wait.elementToBeVisible(btnLocator,true);
        return driver.findElement(btnLocator);
    }

    //===Test cases code===//
    public CartPage clickCheckoutBtn(){
        selectedItems.clear();
        wait.elementsToBeVisible(titleLocator);
        List<WebElement> elementList = driver.findElements(titleLocator);
        for (WebElement element : elementList){
            selectedItems.add(element.getText());
        }
        attachMessage("click on checkout button.");
        wait.elementToBeClickable(checkoutLocator);
        find(checkoutLocator).click();
        return this;
    }

    public CartPage clickContinueShoppingBtn(){
        attachMessage("click on 'Continue Shopping' button.");
        wait.elementToBeClickable(continueShoppingLocator);
        find(continueShoppingLocator).click();
        return this;
    }

    public CartPage clickRemoveBtns(int... itemIndexes){
        List<String> itemsTitleList = new ArrayList<>();
        for(int index : itemIndexes){
            String itemTitle = getItemTitle(index);
            attachMessage("click on Remove button of item '" + itemTitle + "'.");
            itemsTitleList.add(itemTitle);
            removedItems.add(itemTitle);
        }
        for (String item : itemsTitleList){
            wait.elementsToBeClickable(itemBtnLocator);
            findItemRemoveBtn(item).click();
        }
        return this;
    }



    public CartPage redirectCartPage(){
        if(driver.getCurrentUrl() != CartData.CartUrl){
            driver.get(CartData.CartUrl);
        }
        return this;
    }


}
