Feature: Home page

  Background: Signed-in user
    Given I am on the Sauce Demo website
    And I type "standard_user" into the username field
    And I type "secret_sauce" into the password field
    When I press the login button

  Scenario Outline: All products are visible on the home page
    Then the home page should be visible
    And the product "<product>" should be visible
    Examples:
      | product                 |
      | Sauce Labs Backpack     |
      | Sauce Labs Bike Light   |
      | Sauce Labs Bolt T-Shirt |

  Scenario: Remove button removes the item from the cart
    Then the home page should be visible
    And I add "Sauce Labs Backpack" to my cart
    When I open the cart
    Then the product "Sauce Labs Backpack" should be visible
    When I click Remove for "Sauce Labs Backpack"
    Then "Sauce Labs Backpack" should no longer be in the cart

  Scenario: Filter sorts products by price (high to low)
    Then the home page should be visible
    When I apply the "Price (high to low)" filter
    Then products should be sorted by price in descending order
    And the first product should have the highest price
    And the last product should have the lowest price

  Scenario: Filter sorts products by name (Z to A)
    Then the home page should be visible
    When I apply the "Name (Z to A)" filter
    Then products should be sorted by name in descending order
    And the first product name should be alphabetically after the last
    And the last product name should be alphabetically before the first