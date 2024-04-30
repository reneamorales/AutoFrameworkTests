package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CartPage;
import pages.HomePage;
import pages.Login;

public class RemoveItemsTest extends BaseTest{

    HomePage homePage;
    SoftAssert softAssert= new SoftAssert();

    @Test
    public void removeItems(){
        Login login= getLoginPage();
        login.login();

        homePage = new HomePage(driver);
        softAssert.assertEquals(homePage.getHeadingText(), "Products");
        homePage.randomElementSelection(3);
        homePage.clickOnCartButton();


        CartPage cartPage1 = getCartPage();
        System.out.println(cartPage1.getHeadingText());
        cartPage1.deleteItems(4);

        softAssert.assertEquals(cartPage1.getListLenght(), 0);


    }
}
