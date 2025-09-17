Feature: Navigation and Menu
  As a user
  I want to navigate through the application
  So that I can access different features

  Background:
    Given I am in sauce demo web page
    And I set the user name text box with "standard_user"
    And I set the password text box with "secret_sauce"
    And I click on the login button

  @menu @navigation
  Scenario: Hamburger menu options
    When I open the side menu
    Then The side menu options should be
      | All Items     |
      | About         |
      | Logout        |
      | Reset App State |

  @reset @navigation
  Scenario: Reset application state
    Given I add the "Sauce Labs Backpack" to the cart
    When I open the side menu
    And I reset app state from the side menu
    And I close the side menu
    Then The cart should be empty
    And The cart badge should not be visible

  @logout @navigation
  Scenario: Logout functionality
    When I open the side menu
    And I select "Logout" from the side menu
    Then I should be on the login page