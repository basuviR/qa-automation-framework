package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By emailInput = By.cssSelector("[data-qa='login-email']");
    private final By passwordInput = By.cssSelector("[data-qa='login-password']");
    private final By loginButton = By.cssSelector("[data-qa='login-button']");
    private final By errorMessage = By.xpath("//p[contains(text(),'incorrect!')]");
    private final By loggedInAs = By.xpath("//a[contains(.,'Logged in as')]");
    private final By logoutLink=By.cssSelector("a[href='/logout']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get(ConfigReader.get("baseURL") + "/login");
    }

    public void login(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public String getLoggedInText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loggedInAs)).getText();
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }
    public String getEmailValidationMessage() {
              return driver.findElement(emailInput).getDomProperty("validationMessage");
    }

    public void logout(){
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }

    public boolean isLoginFormDisplayed(){
     try{
         wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
         return true;
     }
     catch(TimeoutException e){
         return false;
     }

    }
}
