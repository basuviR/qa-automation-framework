package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductDetailsPage {

    // ---------- fields ----------
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By quantityBox = By.xpath("//input[@id='quantity']");
    private final By addToCartButton =
            By.xpath("//div[contains(@class,'product-information')]//button[contains(.,'Add to cart')]");

   private final By name = By.cssSelector(".product-information h2");
    private final By price = By.cssSelector(".product-information span span");

    // ---------- constructor ----------
    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ---------- getters ----------
    public String getName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(name)).getText();
    }

    public String getPrice() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(price)).getText();
    }

    public String getCategory() {
        return getDetail("Category");
    }

    public String getAvailability() {
        return getDetail("Availability");
    }

    public String getCondition() {
        return getDetail("Condition");
    }

    public String getBrand() {
        return getDetail("Brand");
    }


   // ---- add to the actions ----
    public void addToCart(int quantity) {
        // 1. set the quantity
        WebElement box = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityBox));
        box.clear();
        box.sendKeys(String.valueOf(quantity));

        // 2. click Add to cart
           wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();

        // 3. close the popup
        new CartPopupComponent(driver).continueShopping();

    }

    // ---------- private helpers ----------
    private By detailLine(String label) {
        return By.xpath("//div[@class='product-information']/p[contains(., '" + label + "')]");
    }

    private String getDetail(String label) {
        return driver.findElement(detailLine(label)).getText();
    }
}
