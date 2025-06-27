package tests;

import base.BaseTest;
import org.apache.logging.log4j.ThreadContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.cartPage.CartPage;
import pages.cartPage.CartValidations;
import pages.completePage.CompletePage;
import pages.completePage.CompleteValidations;
import pages.informationPage.InformationPage;
import pages.informationPage.InformationValidations;
import pages.loginPage.LoginPage;
import pages.loginPage.LoginValidations;
import pages.logout.LogoutValidations;
import pages.overviewPage.OverviewPage;
import pages.overviewPage.OverviewValidations;
import pages.productsPage.ProductsPage;
import pages.productsPage.ProductsValidations;
import utils.Data.InformationData;
import utils.Data.LoginData;
import utils.Data.products.Product;

public class EndToEndTest extends BaseTest {
    private LoginPage loginPage;
    private LoginValidations loginValidations;

    private ProductsPage productsPage;
    private ProductsValidations productsValidations;

    private CartPage cartPage;
    private CartValidations cartValidations;

    private InformationPage informationPage;
    private InformationValidations informationValidations;

    private OverviewPage overviewPage;
    private OverviewValidations overviewValidations;

    private CompletePage completePage;
    private CompleteValidations completeValidations;

    private LogoutValidations logoutValidations;

    private SoftAssert softAssert;

    @BeforeClass
    public void BeforeEndToEndClass(){
        loginPage = new LoginPage();
        loginValidations = new LoginValidations(loginPage);

        productsPage = new ProductsPage();
        productsValidations = new ProductsValidations(productsPage);

        cartPage = new CartPage();
        cartValidations = new CartValidations(cartPage);

        informationPage = new InformationPage();
        informationValidations = new InformationValidations(informationPage);

        overviewPage = new OverviewPage();
        overviewValidations = new OverviewValidations(overviewPage);

        completePage = new CompletePage();
        completeValidations = new CompleteValidations(completePage);

        logoutValidations = new LogoutValidations();

        softAssert = new SoftAssert();

        ThreadContext.clearAll();
        ThreadContext.put("MethodName","BeforeProductsClass" );

    }


    @Test
    public void completeOneOrderE2E(){
        loginPage.login(LoginData.ValidUsername , LoginData.ValidPassword);
        loginValidations.verifyLoginDoneSuccessfully();
        productsPage.resetApp()
                .clickAddToCartBtn(Product.Number_One.getIndex())
                .clickAddToCartBtn(Product.Number_Four.getIndex());
        productsValidations.verifyAddToCartBtn(Product.Number_One.getIndex(), softAssert)
                .verifyAddToCartBtn(Product.Number_Four.getIndex(), softAssert);
        productsPage.clickCartIcon();
        productsValidations.verifyViewCart();
        cartValidations.verifySelectedItems(productsPage.getSelectedItems());
        cartPage.clickCheckoutBtn();
        cartValidations.verifyCheckout();
        informationPage.enterInformation(InformationData.ValidFirstName , InformationData.ValidLastName , InformationData.ValidPostalCode)
                .clickContinueBtn();
        informationValidations.verifyContinueToOverView();
        overviewValidations.verifySelectedItems(cartPage.getSelectedItems(), softAssert)
                        .verifyTotalPrice(softAssert);
        overviewPage.clickFinishBtn();
        overviewValidations.verifyRedirectedToCompletePage();
        completeValidations.verifyCompleteSuccessfully(softAssert);
        completePage.clickBackHomeBtn();
        completeValidations.verifyBackHome();
        productsValidations.verifyResetApp();
        productsPage.logout();
        logoutValidations.verifyLogoutDoneSuccessfully(softAssert);

        softAssert.assertAll();

    }

}
