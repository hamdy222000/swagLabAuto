package pages.informationPage;

import utils.driverManager.NewDriver;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import utils.Data.InformationData;
import utils.Logs;
import utils.Waits;

public class InformationPage {
    WebDriver driver;
    Waits wait;

    //===Locators===//
    final By firstNameLocator = By.id("first-name");
    final By lastNameLocator = By.id("last-name");
    final By postalCodeLocator = By.id("postal-code");
    final By continueBtnLocator = By.id("continue");
    final By cancelBtnLocator = By.id("cancel");
    final By errorMessageLocator = RelativeLocator.with(By.tagName("h3"))
            .below(By.id("postal-code"))
            .near(By.id("postal-code"));


    //===Constructor===//
    public InformationPage(){
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

    InformationPage redirectInformationPage(){
        String expectedUrl = InformationData.InformationUrl;
        if(!driver.getCurrentUrl().equals(expectedUrl)){
            driver.get(expectedUrl);
        }
        return this;
    }


    InformationPage enterField(By locator , String fieldName , String inputData){
        wait.elementToBeVisible(locator,true);
        attachMessage("enter " + fieldName + " : " + inputData);
        WebElement element = find(locator);
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        element.sendKeys(inputData);
        return this;
    }

    InformationPage enterFirstName(String firstName){
        return enterField(firstNameLocator , "firstName" , firstName);
    }

    InformationPage enterLastName(String lastName){
        return enterField(lastNameLocator , "lastName" , lastName);
    }
    InformationPage enterPostalCode(String postalCode){
        return enterField(postalCodeLocator , "postalCode" , postalCode);
    }

     InformationPage clickBtn(By locator , String btnName){
        attachMessage("click on " + btnName +" button.");
        wait.elementToBeClickable(locator,true);
        find(locator).click();
        return this;
    }


    String getErrorMessage(){
        wait.elementToBeVisible(errorMessageLocator);
        return driver.findElement(errorMessageLocator).getText();
    }

    //===Test cases code===//
    public InformationPage enterInformation(String firstName , String lastName , String postalCode){
        return redirectInformationPage()
                .enterFirstName(firstName)
                .enterLastName(lastName)
                .enterPostalCode(postalCode);
    }


    public InformationPage clickContinueBtn(){
        return clickBtn(continueBtnLocator , "continue");
    }

    public InformationPage clickCancelBtn(){
        return clickBtn(cancelBtnLocator , "cancel");
    }

}
