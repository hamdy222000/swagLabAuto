package tests;

import base.BaseTest;
import org.apache.logging.log4j.ThreadContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.cartPage.CartPage;
import pages.informationPage.InformationPage;
import pages.loginPage.LoginPage;
import pages.overviewPage.OverviewPage;
import pages.overviewPage.OverviewValidations;
import pages.productsPage.ProductsPage;
import utils.Data.InformationData;
import utils.Data.LoginData;
import utils.Data.products.Product;

public class OverviewTest extends BaseTest {

    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private InformationPage informationPage;
    private OverviewPage overviewPage;
    private OverviewValidations overviewValidations;
    private SoftAssert softAssert;

    @BeforeClass
    public void BeforeOverviewClass(){
        loginPage = new LoginPage();
        productsPage = new ProductsPage();
        cartPage = new CartPage();
        informationPage = new InformationPage();
        overviewPage = new OverviewPage();
        overviewValidations = new OverviewValidations(overviewPage);

        ThreadContext.clearAll();
        ThreadContext.put("MethodName","BeforeProductsClass" );

        loginPage.login(LoginData.ValidUsername , LoginData.ValidPassword);

    }

    @BeforeMethod
    public void beforeOverviewMethod(){
        softAssert = new SoftAssert();
        productsPage.resetApp().clickAddToCartBtn(Product.Number_Three.getIndex()).clickAddToCartBtn(Product.Number_Five.getIndex()).clickCartIcon();
        cartPage.clickCheckoutBtn();
        informationPage.enterInformation(InformationData.ValidFirstName , InformationData.ValidLastName , InformationData.ValidPostalCode)
                .clickContinueBtn();
    }

    @Test
    public void selectedItems(){
        overviewValidations.verifySelectedItems(cartPage.getSelectedItems() , softAssert);
    }

    @Test
    public void totalPrice(){
        overviewValidations.verifyTotalPrice();
    }

    @Test
    public void finishCheckOut(){
        overviewPage.clickFinishBtn();
        overviewValidations.verifyRedirectedToCompletePage();
    }

    @Test
    public void continueShopping(){
        overviewPage.clickCancelBtn();
        overviewValidations.verifyContinueToShopping();
    }

}
