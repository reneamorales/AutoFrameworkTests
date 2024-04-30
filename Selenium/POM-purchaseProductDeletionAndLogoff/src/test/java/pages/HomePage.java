package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Collections;
import java.util.List;

public class HomePage extends BasePage{
    @FindBy(css= ".title")
    private WebElement title;
    @FindBy(className= "btn_inventory")
    public List<WebElement> items;

    @FindBy(className = "shopping_cart_link")
    public WebElement buttonCart;

    public String getHeadingText(){
        this.isElementDisplayed(this.title);
        return this.title.getText();
    }
    //We use the Collections class and shuffle by selecting the number of elements to receive.
    public void randomElementSelection(int numberOfItems){
        Collections.shuffle(items);

        for (int i = 0; i < numberOfItems; i++) {
            WebElement itemSelected = items.get(i);
            System.out.println("item agregado:" + itemSelected.getAttribute("id"));
            itemSelected.click();
        }
    }
    public void clickOnCartButton(){
        this.buttonCart.click();

    }

    public HomePage(WebDriver driver){
        super(driver);
    }

}
