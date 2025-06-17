package pages.loginPage;

import utils.driverManager.NewDriver;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import utils.Data.LoginData;
import utils.Logs;
import utils.Waits;


public class LoginPage {

    WebDriver driver;
    Waits wait;

    //===Locators===//
     final By usernameField = By.id("user-name");
     final By passwordField = By.id("password");
     final By loginBtn = By.id("login-button");
     final By errorMessageLocator = RelativeLocator.with(By.tagName("h3"))
            .above(By.id("login-button"))
            .near(By.id("login-button"));



    //===Constructor===//
    public LoginPage(){

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


     LoginPage enterUsername(String username){
        wait.elementToBeVisible(usernameField,true);
        attachMessage("enter username : " + username);
        WebElement element = find(usernameField);
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        element.sendKeys(username);
        return this;
    }




     LoginPage enterPassword(String password){
        wait.elementToBeVisible(passwordField,true);
        attachMessage("enter password : " + password);
        WebElement element = find(passwordField);
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        element.sendKeys(password);
        return this;
    }


     LoginPage clickLogiBtn(){
        attachMessage("click on login button");
        wait.elementToBeClickable(loginBtn,true);
        find(loginBtn).click();
        return this;
    }

     LoginPage redirectLoginPage(){
        if(!driver.getCurrentUrl().equals(LoginData.LoginUrl)){
            driver.get(LoginData.LoginUrl);
        }
        return this;
    }

    //===Test cases code===//
    public LoginPage login(String username , String password){
        return redirectLoginPage()
                .enterUsername(username)
                .enterPassword(password)
                .clickLogiBtn();
    }


}


