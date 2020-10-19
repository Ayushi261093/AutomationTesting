
Feature: Web FE Automation

  Scenario Outline: Test Case For Demo Online Shop
    Given I launch chrome driver and maximize window
    Then I navigate to demo "<url>"
    And I click on laptop bar and select laptop one and add to cart
    And I click on laptop bar and select laptop two and add to cart
    Then I navigate to cart and delete laptop two
    And I click on place order
    And I fill all web form fields
    |name     |Ayushi|
    |country  |India |
    |city     |Basti |
    |credit card |1234567890123456|
    |month    |Oct   |
    |year     |2021  |
    Then I click on purchase button
    Then I log purchase id and amt
    And I validate purchase amount is correct
    Then I click on ok button
    

    Examples: 
      | url                                  | laptop1     |laptop2  |
      |https://www.demoblaze.com/index.html  |Sony vaio i5 |Dell i7 8gb|
      