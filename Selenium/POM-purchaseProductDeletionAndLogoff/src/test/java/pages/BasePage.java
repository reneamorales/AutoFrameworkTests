package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

WebDriver driver;
//Returns a PageBase instance with controller and PageFactory to list each of its web elements.
public BasePage(WebDriver driver){
    this.driver= driver;
    PageFactory.initElements(driver, this);
}

//Check if element is Displayed.
    protected  Boolean isElementDisplayed(WebElement element){
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.visibilityOf(element));

    return  element.isDisplayed();
}
    //Check if the list is empty.
    protected Boolean isListEmpty(List list) {
       return list.size() == 0;
    }
    public Login getLogin(){
        return new Login(driver);
    }

    //Return the Login instance with the set of access credentials and press the Login button.
    public HomePage login() {
        Login login = getLogin();

        login.setUsername("standard_user");
        login.setPassword("secret_sauce");
        return login.clickOnLoginBtn(); //You do not need the 'homePage' variable
    }
}
