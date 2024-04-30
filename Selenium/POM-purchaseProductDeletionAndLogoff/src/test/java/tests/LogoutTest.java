package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;

public class LogoutTest extends BaseTest{
    HomePage homePage;
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void test1(){
        Login login1= getLoginPage();
        login1.login();

        NavbarPage navbarPage = getNavbarPage();
        navbarPage.clickOnMenuBtn();
        navbarPage.clickOnItemLogOut();
        softAssert.assertEquals(navbarPage.getHeadingPage(), "Swag Labs");
    }

}
