Feature: Login Functionality
  As a user of SauceDemo
  I want to login with different credentials
  So that I can access the application

  Background:
    Given I am in sauce demo web page

  @smoke @login
  Scenario: Successful login with valid credentials
    Given I set the user name text box with "standard_user"
    And I set the password text box with "secret_sauce"
    When I click on the login button
    Then The home page should be displayed

  @negative @login
  Scenario Outline: Login with invalid credentials
    Given I set the user name text box with "<username>"
    And I set the password text box with "<password>"
    When I click on the login button
    Then A error message that says "<error_message>" should be displayed

    Examples:
      | username        | password      | error_message                                                                     |
      | invalid_user    | secret_sauce  | Epic sadface: Username and password do not match any user in this service        |
      | standard_user   | wrong_pass    | Epic sadface: Username and password do not match any user in this service        |
      | locked_out_user | secret_sauce  | Epic sadface: Sorry, this user has been locked out.                             |
      |                 | secret_sauce  | Epic sadface: Username is required                                               |
      | standard_user   |               | Epic sadface: Password is required                                               |

  @datatable @login
  Scenario: Multiple user login validation
    Given I am in sauce demo web page
    When I test login with multiple users:
      | username                | password     | should_succeed |
      | standard_user          | secret_sauce | true           |
      | problem_user           | secret_sauce | true           |
      | performance_glitch_user| secret_sauce | true           |
      | error_user             | secret_sauce | true           |
    Then All valid users should be able to login