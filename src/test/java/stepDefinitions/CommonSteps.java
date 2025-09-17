package stepDefinitions;

import Utilities.DriverManager;
import io.cucumber.java.en.Given;

public class CommonSteps {

    @Given("I am on the Sauce Demo website")
    public void goToSauceDemoPage(){
        DriverManager.getDriver().get("https://www.saucedemo.com");
        DriverManager.getDriver().manage().window().maximize();
    }
}