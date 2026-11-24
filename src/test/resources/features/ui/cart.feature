@ui
Feature: Shopping cart
  As a shopper
  I want to add items to the cart
  So that I can easily check out

  Background:
    Given the user is on the products page

  @smoke
  Scenario: Add one product to the cart
    When the user adds "Blue Top" to the cart
    And the user opens the cart
    Then the cart has 1 row
    And the cart shows "Blue Top" with quantity 1

  @regression
  Scenario: Add two different products to the cart
    When the user adds "Blue Top" to the cart
    And the user adds "Stylish Dress" to the cart
    And the user opens the cart
    Then the cart has 2 rows
    And the cart shows "Blue Top" with quantity 1
    And the cart shows "Stylish Dress" with quantity 1

  @regression
  Scenario: Add the same product twice
    When the user adds "Blue Top" to the cart
    And the user adds "Blue Top" to the cart
    And the user opens the cart
    Then the cart has 1 row
    And the cart shows "Blue Top" with quantity 2

  @regression
  Scenario: Row total is price times quantity
    When the user opens the product details using "Blue Top"
    And the user adds 3 of it to the cart
    And the user opens the cart
    Then the cart shows "Blue Top" with quantity 3
    And the row total for "Blue Top" equals price times quantity

  @regression
  Scenario: Remove the only product from the cart
    Given the user adds "Blue Top" to the cart
    And the user opens the cart
    When the user removes the "Blue Top" from the cart
    Then the cart shows the message "Cart is empty!"
