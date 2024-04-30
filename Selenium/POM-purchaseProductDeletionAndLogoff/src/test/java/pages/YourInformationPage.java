package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class YourInformationPage extends BasePage{

    @FindBy(css = "#header_container > div.header_secondary_container > span")
    public WebElement title;

    @FindBy(id = "first-name")
    public WebElement firstName;

    @FindBy(id = "last-name")
    public WebElement lastName;

    @FindBy(id= "postal-code")
    public WebElement postalCode;

    @FindBy(id = "continue")
    public WebElement continueButton;

    public String getHeadingText(){
        this.isElementDisplayed(this.title);
        return this.title.getText();
    }

    public void enterYourInformation(String text1, String text2,String text3){
        this.firstName.sendKeys(text1);
        this.lastName.sendKeys(text2);
        this.postalCode.sendKeys(text3);
    }

    public void continueToCheckout() {
        this.continueButton.click();
    }
    public YourInformationPage(WebDriver driver) {
        super(driver);
    }
}

