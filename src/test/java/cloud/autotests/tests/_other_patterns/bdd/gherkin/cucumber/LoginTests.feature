Feature: Selenide-appium web, iOS and Android tests

  Scenario: Successful login with web react-native app
    Given Go to login page
    When Fill the authorization form
    Then Verify successful authorization