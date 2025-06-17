package tests;

import base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.loginPage.LoginPage;
import pages.loginPage.LoginValidations;
import utils.Data.LoginData;

public class loginTest extends BaseTest {

    private LoginPage loginPage;
    private LoginValidations loginValidations;

    @BeforeClass
    public void beforeLoginClass(){
        loginPage = new LoginPage();
        loginValidations = new LoginValidations(loginPage);
    }

    @Test
    public void loginUsingValidUser(){
        loginPage.login(LoginData.ValidUsername,LoginData.ValidPassword);
        loginValidations.verifyLoginDoneSuccessfully();
    }


    @Test
    public void loginUsingInvalidUsername(){
        loginPage.login(LoginData.InvalidUsername,LoginData.ValidPassword);
        loginValidations.verifyLoginUsingInvalidUsername();

    }

    @Test
    public void loginUsingInvalidPassword(){
        loginPage.login(LoginData.ValidUsername,LoginData.InvalidPassword);
        loginValidations.verifyLoginUsingInvalidPassword();
    }

    @Test
    public void loginUsingInvalidUsernameAndPassword(){
        loginPage.login(LoginData.InvalidUsername,LoginData.InvalidPassword);
        loginValidations.verifyLoginUsingInvalidUser();
    }

    @Test
    public void loginUsingBlankUsername(){
        loginPage.login("",LoginData.ValidPassword);
        loginValidations.verifyBlankUsernameNotAllow();
    }

    @Test
    public void loginUsingBlankPassword(){
        loginPage.login(LoginData.ValidUsername,"");
        loginValidations.verifyBlankPasswordNotAllow();
    }


}
