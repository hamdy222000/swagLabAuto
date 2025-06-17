package tests;

import base.BaseTest;
import org.apache.logging.log4j.ThreadContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.loginPage.LoginPage;
import pages.logout.LogoutValidations;
import pages.productsPage.ProductsPage;
import utils.Data.LoginData;

public class LogoutTest extends BaseTest {
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private LogoutValidations logoutValidations;


    @BeforeClass
    public void BeforeLogoutClass(){
        loginPage = new LoginPage();
        productsPage = new ProductsPage();
        logoutValidations = new LogoutValidations();

        ThreadContext.clearAll();
        ThreadContext.put("MethodName","BeforeProductsClass" );
    }

    @BeforeMethod
    public void beforeLogoutMethod(){
        loginPage.login(LoginData.ValidUsername , LoginData.ValidPassword);
    }

    @Test
    public void LogoutDoneSuccessfully(){
        productsPage.logout();
        logoutValidations.verifyLogoutDoneSuccessfully();
    }
}
