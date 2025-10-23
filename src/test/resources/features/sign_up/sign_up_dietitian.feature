Feature: Sign Up - Dietitian Service

  @dietitian @turkish
  Scenario: TC-SU-003 - Dietitian sign-up in Turkish
    Given The Hiwell application is launched
    When The user taps on the 'Sign Up' button
    And The user selects the "Türkçe" language
    And The user chooses the "Diyetisyenlik" service area
    And The user fills required form fields
    Then The user should see the "Consultation Scheduled" screen
