package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.ProductDetailsPage;
import pages.ProductsPage;
import utils.DriverFactory;

import java.util.List;

public class ProductSteps {

    private ProductsPage productsPage;
    private String openedProductName;
    private ProductDetailsPage detailsPage;

    @Given("the user is on the products page")
    public void theUserIsOnTheProductsPage() {
        productsPage = new ProductsPage(DriverFactory.getDriver());
        productsPage.open();
    }

    @When("the user searches for {string}")
    public void theUserSearchesFor(String word) {
        productsPage.searchFor(word);
    }

    @Then("the {string} heading is shown")
    public void theHeadingIsShown(String expected) {
        String actual = productsPage.getHeadingText();
        Assertions.assertTrue(expected.equalsIgnoreCase(actual), "Expected heading '" + expected + "but was '" + actual + "'");
    }

    @Then("at least one product is shown")
    public void atLeastOneProductIsShown() {
        List<String> names = productsPage.getProductNames();
        Assertions.assertFalse(names.isEmpty(), "No products are shown for this search");
    }

    @Then("every product name in the results contains {string}")
    public void everyProductNameInTheResultsContains(String word) {
        List<String> names = productsPage.getProductNames();
        for (String name : names) {
            Assertions.assertTrue(name.toLowerCase().contains(word.toLowerCase()), "Product '" + name + "' does not contain the '" + word + "'");
        }
    }

    @Then("no products are shown")
    public void noProductsAreShown() {
        List<String> names = productsPage.getProductNames();
        Assertions.assertTrue(names.isEmpty(), "Expected no products,but found: " + names);
    }

    @When("the user opens the product details using {string}")
    public void theUserOpensTheProductDetailsUsing(String productName) {
        openedProductName = productName;
        productsPage.openProductDetails(productName);
        detailsPage = new ProductDetailsPage(DriverFactory.getDriver());
    }

    @Then("the user sees name, price, category, availability, condition and brand")
    public void theUserSeesNamePriceCategoryAvailabilityConditionAndBrand() {
         Assertions.assertAll("product details",
                 ()->Assertions.assertEquals(openedProductName,detailsPage.getName(),"Wrong Product opened"),
                 ()->assertHasValue(detailsPage.getCategory(),"Category"),
                 ()->Assertions.assertFalse(detailsPage.getPrice().isEmpty(),"Price is Empty"),
                 ()->assertHasValue(detailsPage.getAvailability(),"Availability"),
                 ()->assertHasValue(detailsPage.getCondition(),"Condition"),
                 ()->assertHasValue(detailsPage.getBrand(),"Brand")
               );
    }

    private void assertHasValue(String line, String label) {
        Assertions.assertTrue(line.startsWith(label +":"),label+ " line not found ,got : "+line);
        String value = line.substring(label.length() + 1).trim();
        Assertions.assertFalse(value.isEmpty() , label + " has no value");
        }
}
