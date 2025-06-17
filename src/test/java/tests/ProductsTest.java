package tests;

import base.BaseTest;
import org.apache.logging.log4j.ThreadContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.loginPage.LoginPage;
import pages.productsPage.ProductsPage;
import pages.productsPage.ProductsValidations;
import utils.Data.LoginData;
import utils.Data.products.Filter;
import utils.Data.products.Product;


public class ProductsTest extends BaseTest {

    private LoginPage loginPage;
    private ProductsPage productsPage;
    private ProductsValidations productsValidations;
    private SoftAssert softAssert;

    @BeforeClass
    public void BeforeProductsClass(){
        loginPage = new LoginPage();
        productsPage = new ProductsPage();
        productsValidations = new ProductsValidations(productsPage);
        ThreadContext.clearAll();
        ThreadContext.put("MethodName","BeforeProductsClass" );
        loginPage.login(LoginData.ValidUsername , LoginData.ValidPassword);
    }

    @BeforeMethod
    public void BeforeProductsMethod(){
        softAssert = new SoftAssert();
    }

    @Test
    public void filterProductsAtoZ(){
        productsPage.resetApp()
                .selectFilter(Filter.Z_TO_A.getValue())
                .selectFilter(Filter.A_TO_Z.getValue());
        productsValidations.verifyFilterAtoZ();
    }

    @Test
    public void filterProductsZtoA(){
        productsPage.resetApp()
                .selectFilter(Filter.Z_TO_A.getValue());
        productsValidations.verifyFilterZtoA();
    }

    @Test
    public void filterProductsLowToHigh(){
        productsPage.resetApp()
                .selectFilter(Filter.LOW_TO_HIGH.getValue());
        productsValidations.verifyFilterLowToHigh();
    }

    @Test
    public void filterProductsHighToLow(){
        productsPage.resetApp()
                .selectFilter(Filter.HIGH_TO_LOW.getValue());
        productsValidations.verifyFilterHighToLow();
    }

    @Test
    public void resetProductsPage(){
        productsPage.resetApp()
                .clickAddToCartBtn(Product.Number_Two.getIndex())
                .clickAddToCartBtn(Product.Number_Five.getIndex())
                .resetApp();
        productsValidations.verifyResetApp();
    }


    @Test
    public void addProductsToCart(){
        productsPage.resetApp()
                .clickAddToCartBtn(Product.Number_One.getIndex())
                .clickAddToCartBtn(Product.Number_Three.getIndex())
                .clickAddToCartBtn(Product.Number_Six.getIndex());
        productsValidations.verifyAddToCartBtn(Product.Number_One.getIndex() , softAssert)
                    .verifyAddToCartBtn(Product.Number_Three.getIndex() , softAssert)
                    .verifyAddToCartBtn(Product.Number_Six.getIndex() , softAssert);
        softAssert.assertAll();

    }


    @Test
    public void removeProductsFromCart(){
        productsPage.resetApp()
                .clickAddToCartBtn(Product.Number_Two.getIndex())
                .clickAddToCartBtn(Product.Number_Four.getIndex())
                .clickAddToCartBtn(Product.Number_Six.getIndex())
                .clickRemoveBtn(Product.Number_Four.getIndex())
                .clickRemoveBtn(Product.Number_Six.getIndex());
        productsValidations.verifyRemoveBtn(Product.Number_Four.getIndex() , softAssert)
                .verifyRemoveBtn(Product.Number_Six.getIndex() , softAssert);
        softAssert.assertAll();

    }


    @Test
    public void viewYourCart(){
        productsPage.resetApp()
                .clickAddToCartBtn(Product.Number_Three.getIndex())
                .clickAddToCartBtn(Product.Number_Four.getIndex())
                .clickCartIcon();
        productsValidations.verifyViewCart();
    }





}
