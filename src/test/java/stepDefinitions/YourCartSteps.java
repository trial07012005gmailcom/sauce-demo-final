package stepDefinitions;

import Utilities.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import pages.YourCartPage;

public class YourCartSteps {

    YourCartPage yourCartPage = new YourCartPage(DriverManager.getDriver());


    @When("I proceed to checkout")
    public void clickOnCheckoutButton(){
        yourCartPage.clickOnCheckoutButton();
    }
}