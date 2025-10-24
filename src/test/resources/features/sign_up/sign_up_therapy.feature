@hiwell
Feature: Sign Up - Therapy Service

  @therapy @turkish
  Scenario: TC-SU-001 - Therapy sign-up in Turkish (E2E)
    Given The Hiwell application is launched
    When The user taps on the 'Sign Up' button
    And The user selects the "Türkçe" language
    When The user chooses the "Mental Sağlık" therapy area
    And The user taps on the "Başlayın" button to proceed to the form
    And The user selects the "Bireysel Terapi" option
    When The user proceeds through the form and completes the sign-up
    Then The user should see the "Therapist Match" confirmation


  @therapy @french
  Scenario: TC-SU-002 - Therapy sign-up in French  (E2E)
    Given The Hiwell application is launched
    When The user taps on the 'Sign Up' button
    And The user selects the "Français" language
    And The user taps on the "Commencer" button to proceed to the form
    And The user selects the "Thérapie Individuelle" option
    When The user proceeds through the form and completes the sign-up
    Then The user should see the "Therapist Match" confirmation

