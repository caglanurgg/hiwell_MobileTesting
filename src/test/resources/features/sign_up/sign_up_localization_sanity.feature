@languageSanity
# -----------------------------------------------------------------------------
# Feature: Localization sanity check for Therapy Sign Up
#
# Purpose:
#   This feature ensures that the Hiwell mobile app supports multiple languages
#   without functional or visual breakdowns during the Therapy Sign-Up flow.
#
#   Since full E2E coverage (form completion, user details, navigation)
#   is already validated in the Turkish 🇹🇷 and French 🇫🇷 scenarios,
#   this test focuses only on verifying that:
#     - The app launches successfully
#     - The language selection works correctly
#     - Localized texts (e.g., welcome message) load as expected
#     - The app does not crash or freeze during navigation
#
#   This approach reduces execution time and maintenance effort while keeping
#   localization stability under control across all supported languages.
# -----------------------------------------------------------------------------

Feature: Localization sanity check for Therapy Sign Up

  Scenario Outline: TC-SU-LS-001 - Verify language selection and basic text localization
    Given The Hiwell application is launched
    When The user taps on the 'Sign Up' button
    And The user selects the "<language_option>" therapy language
   # And The user selects the "<language_option>" language
    Then The welcome message should be displayed in "<expected_text>"
    And The therapy area selection screen should be visible
    And The app should not crash during the process
    And The user closes the app

    Examples:
      | language_option | expected_text       |
      | Español          | Bienvenido         |
      | Italiano         | Benvenuto          |
      | Ελληνικά         | Καλώς ήρθατε      |
      | Română           | Bun venit          |
      | Português        | Bem-vindo          |
      | Español (México) | Bienvenido         |
      | Català           | Benvingut          |
