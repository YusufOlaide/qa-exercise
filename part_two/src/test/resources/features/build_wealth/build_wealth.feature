@tag
Feature: Build Wealth

@tag1
  Scenario: Calculate Wealth Value for Tope
    Given User is on the Build Wealth Page
    When User enters the required form parameters
    And User clicks on the calculate button
    Then Wealth Goal Projection is populated
