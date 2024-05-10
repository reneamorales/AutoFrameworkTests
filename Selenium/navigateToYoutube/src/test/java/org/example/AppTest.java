package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.SQLOutput;
import java.util.List;
public class AppTest{

    private WebDriver driver;
    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\renem\\Documents\\Resource-chromeDriver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testSearchTestNG() {
        driver.get("https://www.youtube.com");
        WebElement searchBox = driver.findElement(By.xpath("//input[@id='search']"));
        searchBox.sendKeys("TestNG");
        WebElement searchButton = driver.findElement(By.cssSelector("#search-icon-legacy"));
        searchButton.click();

        List<WebElement> searchResults = driver.findElements(By.cssSelector("#video-title > yt-formatted-string"));
        for (WebElement result : searchResults) {
            Assert.assertTrue(result.getText().contains("TestNG") || result.getText().contains("TESTNG"));
        }
    }

    @Test
    public void testSearchSelenium() {
        driver.get("https://www.youtube.com");
        WebElement searchBox = driver.findElement(By.xpath("//input[@id='search']"));
        searchBox.sendKeys("Selenium");
        WebElement searchButton = driver.findElement(By.cssSelector("#search-icon-legacy"));
        searchButton.click();

        List<WebElement> searchResults = driver.findElements(By.cssSelector("#video-title > yt-formatted-string"));
        for (WebElement result : searchResults) {
            Assert.assertTrue(result.getText().contains("Selenium"));
        }
    }
    @Test
    public void testSearchResultsAreDifferent() {
        // Search for TestNG
        driver.get("https://www.youtube.com");
        WebElement searchBox = driver.findElement(By.xpath("//input[@id='search']"));
        searchBox.sendKeys("TestNG");
        WebElement searchButton = driver.findElement(By.cssSelector("#search-icon-legacy"));
        searchButton.click();
        List<WebElement> testNGResults = driver.findElements(By.className("search-result"));

        // Search for Selenium
        driver.get("https://www.youtube.com");
        searchBox = driver.findElement(By.xpath("//input[@id='search']"));
        searchBox.sendKeys("Selenium");
        searchButton = driver.findElement(By.cssSelector("#search-icon-legacy"));
        searchButton.click();
        List<WebElement> seleniumResults = driver.findElements(By.className("search-result"));

        // Compare results
        for (int i = 0; i < testNGResults.size(); i++) {
            for (int j = 0; j < seleniumResults.size(); j++) {
                Assert.assertEquals(testNGResults.get(i), seleniumResults.get(j));
            }
        }

        Assert.assertEquals(testNGResults.size(), seleniumResults.size());
    }

    @Test
    public void testLoginAppearsAfterSearch() {
        driver.get("https://www.youtube.com");
        WebElement searchBox = driver.findElement(By.xpath("//input[@id='search']"));
        searchBox.sendKeys("Log In");
        searchBox.click();

        WebElement loginForm = driver.findElement(By.id("login-form"));
        Assert.assertTrue(loginForm.isDisplayed());
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
