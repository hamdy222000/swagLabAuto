package tests;

import base.BaseTest;
import org.apache.logging.log4j.ThreadContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.cartPage.CartPage;
import pages.informationPage.InformationPage;
import pages.informationPage.InformationValidations;
import pages.loginPage.LoginPage;
import pages.productsPage.ProductsPage;
import utils.Data.InformationData;
import utils.Data.LoginData;
import utils.Data.products.Product;

public class InformationTest extends BaseTest {

    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private InformationPage informationPage;
    private InformationValidations informationValidations;

    @BeforeClass
    public void BeforeInformationClass(){
        loginPage = new LoginPage();
        productsPage = new ProductsPage();
        cartPage = new CartPage();
        informationPage = new InformationPage();
        informationValidations = new InformationValidations(informationPage);

        ThreadContext.clearAll();
        ThreadContext.put("MethodName","BeforeProductsClass" );

        loginPage.login(LoginData.ValidUsername , LoginData.ValidPassword);
        productsPage.resetApp().clickAddToCartBtn(Product.Number_Three.getIndex()).clickCartIcon();
        cartPage.clickCheckoutBtn();
    }


    @Test
    public void checkoutUsingValidInfo(){
        informationPage.enterInformation(InformationData.ValidFirstName , InformationData.ValidLastName , InformationData.ValidPostalCode)
                .clickContinueBtn();
        informationValidations.verifyContinueToOverView();
    }

    @Test
    public void writeSpecialCharToFields(){
        informationPage.enterInformation(InformationData.SpCharFirstName , InformationData.SpCharLastName , InformationData.SpCharPostalCode)
                .clickContinueBtn();
        informationValidations.verifyInvalidInfo();
    }

    @Test
    public void writeCharToPostalCodeField(){
        informationPage.enterInformation(InformationData.ValidFirstName , InformationData.ValidLastName , InformationData.CharPostalCode)
                .clickContinueBtn();
        informationValidations.verifyInvalidInfo();
    }

    @Test
    public void writeNoToNameFields(){
        informationPage.enterInformation(InformationData.NoFirstName , InformationData.NoLastName , InformationData.ValidPostalCode)
                .clickContinueBtn();
        informationValidations.verifyInvalidInfo();
    }

    @Test
    public void EmptyFirstNameField(){
        informationPage.enterInformation("" , InformationData.ValidLastName , InformationData.ValidPostalCode)
                .clickContinueBtn();
        informationValidations.verifyEmptyFirstName();
    }

    @Test
    public void EmptyLastNameField(){
        informationPage.enterInformation(InformationData.ValidFirstName , "" , InformationData.ValidPostalCode)
                .clickContinueBtn();
        informationValidations.verifyEmptyLastName();
    }

    @Test
    public void EmptyPostalCodeField(){
        informationPage.enterInformation(InformationData.ValidFirstName , InformationData.ValidLastName , "")
                .clickContinueBtn();
        informationValidations.verifyEmptyPostalCode();
    }

    @Test
    public void BackToYourCart(){
        informationPage.enterInformation(InformationData.ValidFirstName , InformationData.ValidLastName , InformationData.ValidPostalCode)
                .clickCancelBtn();
        informationValidations.verifyBackToCart();
    }


}
