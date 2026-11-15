@ui
Feature: Login module

  Background:
    Given the user is on the login page

  @smoke
  Scenario: Registered user logs in successfully
    When the user logs in with valid credentials
    Then the user sees their name in the header

  @negative
  Scenario: Wrong password shows an error
    When the user logs in with a wrong password
    Then an error message is shown