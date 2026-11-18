@ui
Feature: Products
  As a shopper
  I want to search for products and see their details
  So that I can find what I want to buy

  Background:
    Given the user is on the products page

  @smoke
  Scenario Outline: Search results match the search "<word>"
    When the user searches for "<word>"
    Then the "SEARCHED PRODUCTS" heading is shown
    And at least one product is shown
    And every product name in the results contains "<word>"
    Examples:
      | word  |
      | blue   |
      | jeans |
      | winter |

  @negative
  Scenario: Search word that does not match products name
    When the user searches for "xyz1245"
    Then no products are shown

  @regression
  Scenario: open the product detail
    When the user opens the product details using "Blue Top"
    Then the user sees name, price, category, availability, condition and brand
