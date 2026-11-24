package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPopupComponent {


    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By cartPopup = By.xpath("//div[contains(@class,'modal-content')]");
    private final By continueShoppingButton =
            By.xpath("//div[contains(@class,'modal-content')]//button[contains(@class,'close-modal')]");

    public CartPopupComponent(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void continueShopping() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(cartPopup));

        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton)).click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(cartPopup));
    }
}
