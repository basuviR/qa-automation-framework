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
    When the user logs in  with <problem>
    Then an error message is shown
    Examples:
      | problem                |
      | wrong password         |
      | not unregistered email |
      | password in wrong case |