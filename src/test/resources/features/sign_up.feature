@hiwell
Feature: Therapy area selection during sign-up on Hiwell mobile application

  Description: This feature validates that a new user can successfully sign up,
  select a preferred language, choose a therapy area, and provide personal
  details such as age, gender, and main problems to proceed to the next step
  of the onboarding process in the Hiwell mobile app.

  Note: Further validation steps after the form screen (e.g., profile creation)
  will be covered in future test cases.


  Scenario Outline: TC-SU-001 - Successful navigation and therapy area selection

    Given The Hiwell application is launched
    When The user taps on the 'Sign Up' button
    And The user selects the "<language_option>" therapy language
    Then The user should be greeted with "Welcome" message
    When The user chooses the "Mental Sağlık" therapy area
    And The user taps on the "Başlayın" button to proceed to the form
    And The user selects the "<therapy_option>" option
  #  And The user clicks the "<dropdown_text>" dropdown
    And The user clicks the "Yaşınız" dropdown
    And The user selects the age "27" from the picker
    And The user confirms the selection by tapping the "TAMAM" button
    And The user selects the "<gender_option>" option
    And The user selects the "<problem_one>" "<problem_two>" "<problem_three>" problem
    And The user clicks the "Devam Edin" button
    And The user closes the application




    Examples:
      | language_option    | therapy_option          | gender_option | problem_one           | problem_two        | problem_three      | dropdown_text |
      | Türkçe             | Bireysel Terapi         | Kadın         | Kaygı ve endişe       | Kötü alışkanlıklar | Stres              | Yaşınız       |
      | Français           | Thérapie Individuelle   | Femme         | Anxiété et inquiétude | Mauvaises habitudes| Stress             | Votre âge     |
