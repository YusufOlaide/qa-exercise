@tag
Feature: Product Menu

  @tag1
  Scenario: Verify Build Wealth Option
    Given User is on Rise Capital home page
    And Product menu is present
    When User clicks on the product menu
    Then Build Wealth option is included in the submenu list
    And User clicks on the Build Wealth Option
    And User validates all texts in H1 tag present
 