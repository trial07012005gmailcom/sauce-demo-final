package stepDefinitions;

import Utilities.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.HomePage;
import pages.ProductDetailsPage;

public class ProductDetails {
    private final HomePage home = new HomePage(DriverManager.getDriver());
    private final ProductDetailsPage details = new ProductDetailsPage(DriverManager.getDriver());

    @And("I open details for {string}")
    public void iOpenDetailsFor(String productName) {
        home.openProductDetails(productName);
    }

    @When("I add the product from details")
    public void iAddTheProductFromDetails() {
        details.clickCartButton();                  // Add to cart -> debería pasar a "Remove"
        Assertions.assertTrue(details.isRemoveState(),
                "Expected 'Remove' state after adding from details, but got: " + details.getButtonText());
    }

    @Then("The details button should show {string}")
    public void theDetailsButtonShouldShow(String expectedText) {
        Assertions.assertEquals(expectedText, details.getButtonText(),
                "Unexpected button state on details page.");
    }
}