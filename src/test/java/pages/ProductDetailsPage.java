package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductDetailsPage {

    // ---------- fields ----------
    private final WebDriver driver;
    private final WebDriverWait wait;

    // ---------- locators ----------
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

    // ---------- private helpers ----------
    private By detailLine(String label) {
        return By.xpath("//div[@class='product-information']/p[contains(., '" + label + "')]");
    }

    private String getDetail(String label) {
        return driver.findElement(detailLine(label)).getText();
    }
}
