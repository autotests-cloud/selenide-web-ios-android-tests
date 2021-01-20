package cloud.autotests.tests._other_patterns.page_objects.steps.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static cloud.autotests.helpers.DriverHelper.byTestId;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class LoginPage extends BasePage {
    private final SelenideElement
            authorizationForm = $(byTestId("Authorization form")),
            loginInput = $(byTestId("Login input")),
            passwordInput = $(byTestId("Password input")),
            rememberMeCheckbox = $(byTestId("Remember me checkbox")),
            loginButton = $(byTestId("Login button"));

    @Step("Open Login page")
    public LoginPage openPage() {
        open("https://autotests.cloud");

        return this;
    }

    @Step("Set value {login} to Login input")
    public LoginPage setLogin(String login) {
        loginInput.setValue(login);

        return this;
    }

    @Step("Set value {password} to Password input")
    public LoginPage setPassword(String password) {
        passwordInput.setValue(password);

        return this;
    }

    @Step("Click Remember me checkbox")
    public LoginPage clickRememberMeCheckbox() {
        rememberMeCheckbox.click();

        return this;
    }

    @Step("Click submit form")
    public void clickSubmitButton() {
        loginButton.click();
    }

    @Step("Authorization form should exist")
    public LoginPage verifyAuthorizationFormExists() {
        authorizationForm.shouldBe(visible);

        return this;
    }
}
