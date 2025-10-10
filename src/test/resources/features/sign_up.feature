@hiwell
Feature: User Account Creation and Internationalization

  Description: As a potential QA Engineer, I verify that new users can successfully navigate the initial flow
  and ensure the Sign Up functionality is ready for full testing across major supported languages.

  Scenario Outline: TC-SU-001 - Successful navigation and therapy area selection

    Given The Hiwell application is launched
    When The user taps on the 'Sign Up' button
    And The user selects the "<language_option>" therapy language
    Then The user should be greeted with "Welcome" message
    When The user chooses the "Mental Sağlık" therapy area
    And The user taps on the "Başlayın" button to proceed to the form
    And The user selects the "Bireysel Terapi" option
    And The user clicks the "Yaşınız" dropdown
    And The user selects the age "37" from the picker
    And The user confirms the selection by tapping the "TAMAM" button
    And The user selects the "<gender_option>" option
    And The user selects the "<problem_one>" "<problem_two>" "<problem_three>" problem
    And The user clicks the "Devam Edin" button




    Examples:
      | language_option    | gender_option | problem_one           | problem_two        | problem_three|
      | Türkçe             | Kadın         | Kaygı ve endişe       | Öfke Sorunları     |   Stres      |
      | Français           | Femme         | Anxiété et inquiétude | Mauvaises habitudes|   Stress     |

