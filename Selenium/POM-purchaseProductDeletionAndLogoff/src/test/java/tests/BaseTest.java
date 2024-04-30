package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import pages.*;

public class BaseTest {
    WebDriver driver = null;
    Login login;
    HomePage homePage;
    CartPage cartPage;
    Overview overview;

    NavbarPage navbarPage;
    @BeforeTest
    public void beforeTest(){
        String driverPath = "C:\\Users\\renem\\Documents\\Resource-chromeDriver\\chromedriver-win64\\chromedriver.exe";
        System.setProperty("driver.chrome.driver", driverPath);
        driver = new ChromeDriver();
    }
    @Ignore
    @AfterTest
    public void afterTest(){
       // driver.close();
    }

    public Login getLoginPage(){
        return new Login(driver, "https://www.saucedemo.com/");
    }

    public CartPage getCartPage(){
        return new CartPage(driver);
    }
    public YourInformationPage getYourInformationPage(){
        return new YourInformationPage(driver);
    }
    public Overview getOverview(){
        return new Overview(driver);
    }
    public NavbarPage getNavbarPage(){
        return new NavbarPage(driver);
    }

}
