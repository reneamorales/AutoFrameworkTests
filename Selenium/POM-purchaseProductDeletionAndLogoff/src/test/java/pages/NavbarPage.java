package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NavbarPage extends BasePage{

    @FindBy(id = "react-burger-menu-btn")
    public WebElement menuButton;
    @FindBy(id = "logout_sidebar_link")
    WebElement navItem;
    @FindBy(css = "#root > div > div.login_logo")
    public WebElement title;
    public void clickOnMenuBtn(){
        this.isElementDisplayed(this.menuButton);
        this.menuButton.click();
    }
    public void clickOnItemLogOut(){
        this.isElementDisplayed(this.navItem);
        this.navItem.click();
    }
    public String getHeadingPage(){
        this.isElementDisplayed(this.title);
        return this.title.getText();
    }
    public NavbarPage(WebDriver driver) {
        super(driver);
    }
}
