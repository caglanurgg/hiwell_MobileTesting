Feature: Login functionality
  Scenario: User logs in with valid credentials
    Given the user is on the login screen
    When the user enters valid credentials
    And taps the login button
    Then the user should be navigated to the homepage
