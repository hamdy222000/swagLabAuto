package tests;

import base.BaseTest;
import org.apache.logging.log4j.ThreadContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.cartPage.CartPage;
import pages.cartPage.CartValidations;
import pages.loginPage.LoginPage;
import pages.productsPage.ProductsPage;
import pages.productsPage.ProductsValidations;
import utils.Data.LoginData;
import utils.Data.products.Product;

public class CartTest extends BaseTest {

    private LoginPage loginPage;
    private ProductsPage productsPage;
    private ProductsValidations productsValidations;
    private CartPage cartPage;
    private CartValidations cartValidations;
    private SoftAssert softAssert;

    @BeforeClass
    public void BeforeProductsClass(){
        loginPage = new LoginPage();
        productsPage = new ProductsPage();
        productsValidations = new ProductsValidations(productsPage);
        cartPage = new CartPage();
        cartValidations = new CartValidations(cartPage);

        ThreadContext.clearAll();
        ThreadContext.put("MethodName","BeforeProductsClass" );
        loginPage.login(LoginData.ValidUsername , LoginData.ValidPassword);
        productsPage.resetApp().clickCartIcon();
    }

    @BeforeMethod
    public void BeforeProductsMethod(){
        softAssert = new SoftAssert();
        cartPage.redirectCartPage();
    }

    @Test
    public void continueShopping(){
        cartPage.clickContinueShoppingBtn();
        cartValidations.verifyContinueShopping();
    }

    @Test
    public void checkout(){
        cartPage.clickCheckoutBtn();
        cartValidations.verifyCheckout();
    }

    @Test
    public void addItemsToCart(){
        cartPage.clickContinueShoppingBtn();
        productsPage.resetApp()
                .clickAddToCartBtn(Product.Number_One.getIndex())
                .clickAddToCartBtn(Product.Number_Four.getIndex())
                .clickAddToCartBtn(Product.Number_Six.getIndex())
                .clickCartIcon();
        cartValidations.verifySelectedItems(productsPage.getSelectedItems());
    }

    @Test
    public void removeItemsFromCart(){
        cartPage.clickContinueShoppingBtn();
        productsPage.resetApp()
                .clickAddToCartBtn(Product.Number_One.getIndex())
                .clickAddToCartBtn(Product.Number_Four.getIndex())
                .clickAddToCartBtn(Product.Number_Six.getIndex())
                .clickCartIcon();
        cartPage.clickRemoveBtns(0,2);
        cartValidations.verifyRemoveItems(cartPage.getRemovedItems(),softAssert);
        cartPage.clickContinueShoppingBtn();
        productsValidations.verifyProductsRemoved(cartPage.getRemovedItems(),softAssert);
        softAssert.assertAll();
    }



}
