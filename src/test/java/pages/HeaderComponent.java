package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

    public class HeaderComponent {

        // fields
        private final WebDriver driver;
        private final WebDriverWait wait;

        // locators
        private final By cartLink =
                By.xpath("//ul[contains(@class,'nav')]//a[@href='/view_cart']");

        // constructor
        public HeaderComponent(WebDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }

        // actions
        public void openCart() {
            wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();
        }
    }

