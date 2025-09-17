package stepDefinitions;

import Utilities.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.HomePage;

public class Navigation {
    HomePage homePage = new HomePage(DriverManager.getDriver());

    @And("The home page should be displayed")
    public void verifyHomePageIsDisplayed(){
        Assertions.assertTrue(homePage.sauceDemoTitleIsDisplayed());
    }

    @Then("The product {string} should be displayed")
    public void verifyProductIsDisplayed(String product){
        Assertions.assertTrue(homePage.productIsDisplayed(product));
    }
    @And("I click on the cart icon")
    public void iClickOnTheCartIcon() {
        homePage.clickOnCartIcon();
    }

    @When("I sort products by {string}")
    public void iSortProductsBy(String value) {
        homePage.selectSortByValue(value);
    }

    @Then("The first product should be {string}")
    public void theFirstProductShouldBe(String expectedFirst) {
        boolean ok = homePage.waitFirstProductIs(expectedFirst);
        Assertions.assertTrue(ok);
    }

    @And("I add the {string} to the cart")
    public void addProductToCart(String productName){
        homePage.addProductToCart(productName);
    }





}