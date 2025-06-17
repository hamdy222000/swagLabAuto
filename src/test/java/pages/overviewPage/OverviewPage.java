package pages.overviewPage;

import utils.driverManager.NewDriver;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Data.OverviewData;
import utils.Logs;
import utils.Waits;

public class OverviewPage {

    WebDriver driver;
    Waits wait;

    //===Locators===//
    final By itemTitleLocator = By.cssSelector(".inventory_item_name");
    final By itemPriceLocator = By.cssSelector(".inventory_item_price");
    final By totalPriceLocator = By.cssSelector(".summary_subtotal_label");
    final By finishBtnLocator = By.id("finish");
    final By cancelBtnLocator = By.id("cancel");

    //===Constructor===//
    public OverviewPage(){
        driver = NewDriver.getDriver();
        wait = new Waits();
    }

    //===Helper methods===//
    WebElement find(By locator){
        wait.elementToBePresent(locator,true);
        return driver.findElement(locator);
    }

    void attachMessage(String message){
        Allure.step(message);
        Logs.info(message);
    }

    public OverviewPage redirectOverviewPage(){
        String expectedUrl = OverviewData.OverviewUrl;
        if(!driver.getCurrentUrl().equals(expectedUrl)){
            driver.get(expectedUrl);
        }
        return this;
    }

    OverviewPage clickBtn(By locator , String btnName){
        attachMessage("click on " + btnName +" button.");
        wait.elementToBeClickable(locator,true);
        find(locator).click();
        return this;
    }

    double getTotalPrice(){
        return Double.parseDouble(find(totalPriceLocator).getText().split("\\$")[1]);
    }

    //===Test cases code===//
    public OverviewPage clickFinishBtn(){
        return clickBtn(finishBtnLocator , "finish");
    }

    public OverviewPage clickCancelBtn(){
        return clickBtn(cancelBtnLocator , "cancel");
    }


}
