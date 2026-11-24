package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.CartPage;
import pages.HeaderComponent;
import pages.ProductDetailsPage;
import pages.ProductsPage;
import utils.DriverFactory;


public class CartSteps {

    private CartPage cartPage;

    @When("the user adds {string} to the cart")
    public void theUserAddsToTheCart(String productName) {
        new ProductsPage(DriverFactory.getDriver()).addToCart(productName);
    }

    @When("the user opens the cart")
    public void theUserOpensTheCart() {
        new HeaderComponent(DriverFactory.getDriver()).openCart();
        cartPage = new CartPage(DriverFactory.getDriver());
    }

    @Then("the cart has {int} row(s)")
    public void theCartHasRows(int expectedRows) {
        Assertions.assertEquals(expectedRows, cartPage.getRowCount());
    }

    @Then("the cart shows {string} with quantity {int}")
    public void theCartShowsWithQuantity(String productName, int expectedQuantity) {
        Assertions.assertEquals(expectedQuantity, cartPage.getQuantity(productName));
    }

    @Then("the row total for {string} equals price times quantity")
    public void theRowTotalEqualsPriceTimesQuantity(String productName) {
        int price = cartPage.getPrice(productName);
        int quantity = cartPage.getQuantity(productName);
        int expectedTotal = price * quantity;
        Assertions.assertEquals(expectedTotal, cartPage.getTotal(productName));
    }

    @When("the user removes the {string} from the cart")
    public void theUserRemovesFromTheCart(String productName) {
        cartPage.removeProduct(productName);// call cartPage.removeProduct
    }

    @Then("the cart shows the message {string}")
    public void theCartShowsTheMessage(String expectedMessage) {
        String actualMessage = cartPage.getEmptyCartMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage),
                "Expected message to contain '" + expectedMessage + "' but it was '" + actualMessage + "'");
    }
    @When("the user adds {int} of it to the cart")
    public void theUserAddsOfItToTheCart(int quantity) {
        new ProductDetailsPage(DriverFactory.getDriver()).addToCart(quantity);
    }
}