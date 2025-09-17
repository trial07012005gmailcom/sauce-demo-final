package stepDefinitions;

import Utilities.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.HomePage;

import java.util.List;

public class HomeSteps {
    HomePage homePage = new HomePage(DriverManager.getDriver());

    @Then("the home page should be visible")
    public void verifyHomePageIsDisplayed(){
        Assertions.assertTrue(homePage.sauceDemoTitleIsDisplayed());
    }

    @Then("the product {string} should be visible")
    public void verifyProductIsDisplayed(String product){
        Assertions.assertTrue(homePage.productIsDisplayed(product));
    }

    @And("I add {string} to my cart")
    public void addProductToCart(String productName){
        homePage.addProductToCart(productName);

    }

    @When("I open the cart")
    public void clickOnCartIcon(){
        homePage.clickOnCartIcon();
    }

    @When("I click Remove for {string}")
    public void removeProduct(String product){
        homePage.removeProductToCart(product);
    }


    @Then("{string} should no longer be in the cart")
    public void verifyProductIsNotDisplayed(String product){
        Assertions.assertFalse(homePage.productIsDisplayed(product));
    }

    @When("I apply the {string} filter")
    public void iSelectTheFilterOption(String filterOption) {
        homePage.selectFilterOption(filterOption);
    }

    @Then("products should be sorted by price in descending order")
    public void productsShouldBeSortedByPriceInDescendingOrder() {
        List<Double> prices = homePage.getProductPrices();
        List<Double> sortedPrices = homePage.sortPricesDescending(prices);
        Assertions.assertEquals(sortedPrices, prices,
                "Los productos no están ordenados de mayor a menor precio");
    }

    @Then("the first product should have the highest price")
    public void theFirstProductShouldHaveTheHighestPrice() {
        List<Double> prices = homePage.getProductPrices();
        Assertions.assertFalse(prices.isEmpty(), "No hay precios de productos");
        Double highestPrice = homePage.getHighestPrice(prices);
        Assertions.assertEquals(highestPrice, prices.get(0),
                "El primer producto no tiene el precio más alto");
    }

    @Then("the last product should have the lowest price")
    public void theLastProductShouldHaveTheLowestPrice() {
        List<Double> prices = homePage.getProductPrices();
        Assertions.assertFalse(prices.isEmpty(), "No hay precios de productos");
        Double lowestPrice = homePage.getLowestPrice(prices);
        Assertions.assertEquals(lowestPrice, prices.get(prices.size() - 1),
                "El último producto no tiene el precio más bajo");
    }

    @Then("products should be sorted by name in descending order")
    public void productsShouldBeSortedByNameInDescendingOrder() {
        Assertions.assertTrue(homePage.areProductsSortedZA(),
                "Los productos no están ordenados Z→A");
    }

    @Then("the first product name should be alphabetically after the last")
    public void firstNameShouldBeAfterLast() {
        List<String> productNames = homePage.getProductNames();
        Assertions.assertFalse(productNames.isEmpty(), "No hay nombres de productos");
        String first = productNames.get(0);
        String last  = productNames.get(productNames.size() - 1);
        Assertions.assertTrue(first.compareToIgnoreCase(last) >= 0,
                "El primer nombre no es alfabéticamente posterior al último");
    }

    @Then("the last product name should be alphabetically before the first")
    public void lastNameShouldBeBeforeFirst() {
        List<String> productNames = homePage.getProductNames();
        Assertions.assertFalse(productNames.isEmpty(), "No hay nombres de productos");
        String first = productNames.get(0);
        String last  = productNames.get(productNames.size() - 1);
        Assertions.assertTrue(last.compareToIgnoreCase(first) <= 0,
                "El último nombre no es alfabéticamente anterior al primero");
    }
}