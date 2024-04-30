package tests;


import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;

public class PurchaseProductTest extends BaseTest{
    HomePage homePage;
    SoftAssert softAssert= new SoftAssert();
    @Test
    public void test1(){
        Login login= getLoginPage();
        login.login();
        homePage = new HomePage(driver);
        softAssert.assertEquals(homePage.getHeadingText(), "Products");
        homePage.randomElementSelection(1);
        homePage.clickOnCartButton();

    }

    @Test(dependsOnMethods = "test1")
    public void test2(){

        CartPage cartPage1= getCartPage();
        cartPage1.getHeadingText();
        cartPage1.checkout();

        YourInformationPage yourInfo = getYourInformationPage();

        softAssert.assertEquals(yourInfo.getHeadingText(), "Checkout: Your Information");
        yourInfo.enterYourInformation("René Alejandro", "Morales", "9100");
        yourInfo.continueToCheckout();
    }

    @Test(dependsOnMethods = "test2")
    public void test3(){
        Overview overview= getOverview();
        overview.clickOnFinishButton();
        softAssert.assertEquals(overview.getHeadingText(), "Thank you for your order!");
    }
}
