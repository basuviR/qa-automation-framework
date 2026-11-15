package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.LoginPage;
import utils.ConfigReader;
import utils.DriverFactory;

public class LoginSteps {

    private LoginPage loginPage;

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.open();
    }

    @When("the user logs in with valid credentials")
    public void theUserLogsInWithValidCredentials() {

        loginPage.login(ConfigReader.get("testEmail"), ConfigReader.get("testPassword"));
    }

    @Then("the user sees their name in the header")
    public void theUserSeesTheirNameInTheHeader() {
        String actual = loginPage.getLoggedInText();
        String expected = ConfigReader.get("firstName");
        Assertions.assertTrue(actual.contains(expected), "Expected header to contain '" + expected + "', but was: " + actual);
    }


    @When("the user logs in with wrong password")
    public void theUserLogsInWithWrongPassword() {
        loginPage.login(ConfigReader.get("testEmail"), ConfigReader.get("testPassword") + "x");
    }

    @When("the user logs in with unregistered email")
    public void theUserLogsInWithUnregisteredEmail() {
        loginPage.login("not.registered@example.com", ConfigReader.get("testPassword"));
    }

    @When("the user logs in with password in wrong case")
    public void theUserLogsInWithPasswordInWrongCase() {
        loginPage.login(ConfigReader.get("testEmail"), ConfigReader.get("testPassword").toUpperCase());
    }

    @Then("an error message is shown")
    public void anErrorMessageIsShown() {
        String actual = loginPage.getErrorMessage();
        String expected = "Your email or password is incorrect!";
        Assertions.assertEquals(expected, actual, "Wrong error message");
    }

    @When("the user logs in with an empty email")
    public void theUserLogsInWithAnEmptyEmail() {
        loginPage.login("", ConfigReader.get("testPassword"));
    }

    @Then("the email field asks to be filled in")
    public void theEmailFieldAsksToBeFilledIn() {
        String message = loginPage.getEmailValidationMessage();
        Assertions.assertFalse(message.isEmpty(), "Expected a validation message on the email field");
    }

    @When("the user logs out")
    public void theUserLogsOut() {
        loginPage.logout();
    }

    @Then("the login page is shown")
    public void theLoginPageIsShown() {
        Assertions.assertTrue(loginPage.isLoginFormDisplayed(), "Expected the login form after logout");

    }
}