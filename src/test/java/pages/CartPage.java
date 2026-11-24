package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {

    // fields
    private final WebDriver driver;
    private final WebDriverWait wait;

    // locators
    private final By cartTable = By.xpath("//table[@id='cart_info_table']");
    private final By cartRows = By.xpath("//table[@id='cart_info_table']//tbody/tr");
    private final String rowXpath = "//table[@id='cart_info_table']//tbody/tr[.//a[text()='%s']]";
    private final By emptyCartMessage = By.xpath("//span[@id='empty_cart']");

    // constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // getters
    public int getRowCount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartTable));
        return driver.findElements(cartRows).size();
    }

    public int getPrice(String productName) {
        By price = By.xpath(String.format(rowXpath, productName)
                + "//td[contains(@class,'cart_price')]");
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(price)).getText();
        return toNumber(text);
    }

    public int getQuantity(String productName) {
        By quantity=By.xpath(String.format(rowXpath,productName)+"//td[contains(@class,'cart_quantity')]");
        String text=wait.until(ExpectedConditions.visibilityOfElementLocated(quantity)).getText();
        return toNumber(text);
    }

    public int getTotal(String productName) {
        By total=By.xpath(String.format(rowXpath,productName)+"//td[contains(@class,'cart_total')]");
        String text=wait.until(ExpectedConditions.visibilityOfElementLocated(total)).getText();
        return toNumber(text);
    }

    public String getEmptyCartMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emptyCartMessage)).getText();
    }

    // actions
    public void removeProduct(String productName) {
        By row = By.xpath(String.format(rowXpath, productName));
        By deleteLink = By.xpath(String.format(rowXpath, productName)
                + "//td[contains(@class,'cart_delete')]//a");

        wait.until(ExpectedConditions.elementToBeClickable(deleteLink)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(row));
    }

    private int toNumber(String text) {
        return Integer.parseInt(text.replaceAll("[^0-9]", ""));
    }
}