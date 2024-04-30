package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login extends BasePage{

    @FindBy(id = "user-name")
    private WebElement username;
    @FindBy(id = "password")
    private WebElement password;
    @FindBy(id = "login-button")
    private WebElement loginBtn;

    public void setUsername(String userName) {
        this.username.sendKeys(userName);
    }
    public void setPassword(String password) {
        this.password.sendKeys(password);
    }
    public HomePage clickOnLoginBtn() {
        this.loginBtn.submit();
        return new HomePage(this.driver);
    }
    public Login(WebDriver driver) {
        super(driver);
    }
    public Login(WebDriver driver, String url){
        super(driver);
        this.driver.get(url);
    }
}
