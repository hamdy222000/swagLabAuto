package pages.completePage;

import utils.driverManager.NewDriver;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Data.CompleteData;
import utils.Logs;
import utils.Waits;

public class CompletePage {

    WebDriver driver;
    Waits wait;

    //===Locators===//
    final By completeImageLocator = By.cssSelector(".pony_express");
    final By completeMessageLocator = By.cssSelector(".complete-header");
    final By backHomeBtnLocator = By.id("back-to-products");


    //===Constructor===//
    public CompletePage(){
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

    public CompletePage redirectCompletePage(){
        String expectedUrl = CompleteData.CompleteUrl;
        if(!driver.getCurrentUrl().equals(expectedUrl)){
            driver.get(expectedUrl);
        }
        return this;
    }

    CompletePage clickBtn(By locator , String btnName){
        attachMessage("click on " + btnName +" button.");
        wait.elementToBeClickable(locator,true);
        find(locator).click();
        return this;
    }


    //===Test cases code===//
    public CompletePage clickBackHomeBtn(){
        return clickBtn(backHomeBtnLocator , "Back Home");
    }
}

