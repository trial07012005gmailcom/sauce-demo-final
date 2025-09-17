package stepDefinitions;

import Utilities.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.LoginPage;

public class LoginSteps {
    LoginPage loginPage = new LoginPage(DriverManager.getDriver());

    @Given("I type {string} into the username field")
    public void typeUsername(String userName) {
        loginPage.setUserNameTextBox(userName);
    }

    @And("I type {string} into the password field")
    public void typePassword(String password) {
        loginPage.setPasswordTextBox(password);
    }

    @When("I press the login button")
    public void pressLoginButton() {
        loginPage.clickOnLoginButton();
    }

    @Then("An error message stating {string} should appear")
    public void assertErrorMessage(String message) {
        Assertions.assertTrue(loginPage.errorMessageIsDisplayed(message));
    }
}