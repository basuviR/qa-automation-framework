package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;
import utils.DriverFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductsPage {

    // ---------- fields ----------
    private final WebDriver driver;
    private final WebDriverWait wait;

    // ---------- locators ----------
    private final By searchBox     =By.id("search_product");
    private final By searchButton  = By.id("submit_search");
    private final By heading       =By.cssSelector(".features_items h2.title");
    private final By productNames  = By.cssSelector(".productinfo p");

    // ---------- constructor ----------
    public ProductsPage(WebDriver driver) {
        this.driver=driver;
        wait=new WebDriverWait(driver,Duration.ofSeconds(10));
    }

    // ---------- actions ----------
    public void open() {
        driver.get(ConfigReader.get("baseURL")+"/products");
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
    }

    public void searchFor(String word) {

        WebElement box=driver.findElement(searchBox);
        box.clear();
        box.sendKeys(word);
        clickWhenReady(searchButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(heading));
    }

    public void openProductDetails(String productName) {
        clickWhenReady(viewProductLinkFor(productName));

    }

    // ---------- getters ----------
    public String getHeadingText() {
         return wait.until(ExpectedConditions.visibilityOfElementLocated(heading)).getText();
    }

    public List<String> getProductNames() {

        List<WebElement> elements=driver.findElements(productNames);
        List<String>names=new ArrayList<>();
        for(WebElement element:elements){
            names.add(element.getText());
        }
        return names;
    }

    // ---------- private helpers ----------
    private By viewProductLinkFor(String productName) {

        return By.xpath(" //div[contains(@class,'product-image-wrapper')][.//p[text()='"+ productName +"']]//a[contains(text(),'View Product')]");
    }

    private void clickWhenReady(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", element);
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
}
