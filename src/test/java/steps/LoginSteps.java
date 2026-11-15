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

    @When("the user logs in with a wrong password")
    public void theUserLogsInWithAWrongPassword() {
        loginPage.login(ConfigReader.get("testEmail"), ConfigReader.get("testPassword") + "x");
    }

    @Then("an error message is shown")
    public void anErrorMessageIsShown() {

        String actual = loginPage.getErrorMessage();
        String expected = "Your email or password is incorrect!";
        Assertions.assertEquals(expected, actual, "Wrong error message");
    }
}