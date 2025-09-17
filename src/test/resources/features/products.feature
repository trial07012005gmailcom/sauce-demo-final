Feature: Product Management
  As a logged-in user
  I want to manage products and shopping cart
  So that I can make purchases

  Background:
    Given I am in sauce demo web page
    And I set the user name text box with "standard_user"
    And I set the password text box with "secret_sauce"
    And I click on the login button
    And The home page should be displayed

  @smoke @products
  Scenario: View product details
    When I open details for "Sauce Labs Backpack"
    Then The details button should show "Add to cart"

  @cart @products
  Scenario: Add and remove products from cart
    Given I ensure the cart is empty
    When I add the "Sauce Labs Backpack" to the cart
    Then I click on the cart icon
    And I ensure the cart is not empty
    When I open details for "Sauce Labs Backpack"
    Then The details button should show "Remove"

  @sorting @products
  Scenario Outline: Sort products by different criteria
    When I sort products by "<sort_option>"
    Then The first product should be "<expected_product>"

    Examples:
      | sort_option | expected_product           |
      | lohi        | Sauce Labs Onesie          |
      | hilo        | Sauce Labs Fleece Jacket   |
      | az          | Sauce Labs Backpack        |
      | za          | Test.allTheThings() T-Shirt |

  @checkout @products
  Scenario: Attempt checkout with empty cart
    Given I ensure the cart is empty
    When I click on the cart icon
    And I click on the checkout button
    Then I should remain on the cart page