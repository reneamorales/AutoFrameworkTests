package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Overview extends BasePage{

    @FindBy(id= "finish")
    public WebElement finishButton;

    @FindBy(css = "#checkout_complete_container > h2")
    public WebElement confirmationTitle;

    public void clickOnFinishButton(){
        this.finishButton.click();
    }
    public String getHeadingText(){
        this.isElementDisplayed(this.confirmationTitle);
        return this.confirmationTitle.getText();
    }

    public Overview(WebDriver driver) {
        super(driver);
    }
}
