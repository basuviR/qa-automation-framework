@ui
Feature: Login module

  Background:
    Given the user is on the login page

  @smoke
  Scenario: Registered user logs in successfully
    When the user logs in with valid credentials
    Then the user sees their name in the header

  @negative
  Scenario Outline: Login fails with <problem>
    When the user logs in with <problem>
    Then an error message is shown

    Examples:
      | problem                |
      | wrong password         |
      | unregistered email     |
      | password in wrong case |

  @negative
  Scenario: Login is blocked when the email is empty
    When the user logs in with an empty email
    Then the email field asks to be filled in

  @regression
  Scenario: Logged-in user logs out and returns to the login page
    When the user logs in with valid credentials
    And the user logs out
    Then the login page is shown
