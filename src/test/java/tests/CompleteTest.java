package tests;

import base.BaseTest;
import org.apache.logging.log4j.ThreadContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.cartPage.CartPage;
import pages.completePage.CompletePage;
import pages.completePage.CompleteValidations;
import pages.informationPage.InformationPage;
import pages.loginPage.LoginPage;
import pages.overviewPage.OverviewPage;
import pages.productsPage.ProductsPage;
import pages.productsPage.ProductsValidations;
import utils.Data.InformationData;
import utils.Data.LoginData;
import utils.Data.products.Product;

public class CompleteTest extends BaseTest {

    private LoginPage loginPage;
    private ProductsPage productsPage;
    private ProductsValidations productsValidations;
    private CartPage cartPage;
    private InformationPage informationPage;
    private OverviewPage overviewPage;
    private CompletePage completePage;
    private CompleteValidations completeValidations;
    private SoftAssert softAssert;

    @BeforeClass
    public void BeforeCompleteClass(){
        loginPage = new LoginPage();
        productsPage = new ProductsPage();
        productsValidations = new ProductsValidations(productsPage);
        cartPage = new CartPage();
        informationPage = new InformationPage();
        overviewPage = new OverviewPage();
        completePage = new CompletePage();
        completeValidations = new CompleteValidations(completePage);

        ThreadContext.clearAll();
        ThreadContext.put("MethodName","BeforeProductsClass" );

        loginPage.login(LoginData.ValidUsername , LoginData.ValidPassword);

    }

    @BeforeMethod
    public void beforeOverviewMethod(){
        softAssert = new SoftAssert();

    }

    @Test
    public void completeCheckoutSuccessfully(){
        productsPage.resetApp().clickAddToCartBtn(Product.Number_Two.getIndex()).clickCartIcon();
        cartPage.clickCheckoutBtn();
        informationPage.enterInformation(InformationData.ValidFirstName , InformationData.ValidLastName , InformationData.ValidPostalCode)
                .clickContinueBtn();
        overviewPage.clickFinishBtn();
        completeValidations.verifyCompleteSuccessfully(softAssert);
        softAssert.assertAll();
    }

    @Test
    public void completeWithoutSelectItems(){
        productsPage.resetApp().clickCartIcon();
        cartPage.clickCheckoutBtn();
        informationPage.enterInformation(InformationData.ValidFirstName , InformationData.ValidLastName , InformationData.ValidPostalCode)
                .clickContinueBtn();
        overviewPage.clickFinishBtn();
        completeValidations.verifyCompleteWithoutSelectProduct(softAssert);
        softAssert.assertAll();
    }

    @Test
    public void backHome(){
        productsPage.resetApp().clickAddToCartBtn(Product.Number_Two.getIndex()).clickCartIcon();
        cartPage.clickCheckoutBtn();
        informationPage.enterInformation(InformationData.ValidFirstName , InformationData.ValidLastName , InformationData.ValidPostalCode)
                .clickContinueBtn();
        overviewPage.clickFinishBtn();
        completePage.clickBackHomeBtn();
        completeValidations.verifyBackHome();
        productsValidations.verifyResetApp();
    }
}
