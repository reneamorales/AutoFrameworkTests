package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Iterator;
import java.util.List;

public class CartPage extends BasePage {
    @FindBy(css= ".title")
    private WebElement title;
    @FindBy(className= "cart_button")
    public List<WebElement> itemsToDelete;
    @FindBy(id = "checkout")
    public WebElement checkoutButton;
    //We obtain the title of the page verifying that it is displayed before.
    public String getHeadingText(){
        this.isElementDisplayed(this.title);
        return this.title.getText();
    }
    //We Iterate and accurately remove all elements using the Iterator interface of the Collections framework.
    public void deleteItems(int numberOfItemsToDelete) {
        Iterator<WebElement> it = itemsToDelete.iterator();
        while(it.hasNext()){
            it.next().click();
            it.remove();
        }
    }
    public int getListLenght(){
        this.isListEmpty(this.itemsToDelete);
        return this.itemsToDelete.size();
    }

    public void checkout(){
        this.checkoutButton.click();
    }

    public CartPage(WebDriver driver) {
        super(driver);
    }
}
