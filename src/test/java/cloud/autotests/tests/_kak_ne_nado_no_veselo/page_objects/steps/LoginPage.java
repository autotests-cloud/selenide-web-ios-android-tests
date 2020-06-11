package cloud.autotests.tests._kak_ne_nado_no_veselo.page_objects.steps;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static cloud.autotests.helpers.DriverHelper.byTestId;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


class LoginPage {
    private SelenideElement
            headerLabel = $(byTestId("Header label")),
            authorizationForm = $(byTestId("Authorization form")),
            loginInput = $(byTestId("Login input")),
            passwordInput = $(byTestId("Password input")),
            rememberMeCheckbox = $(byTestId("Remember me checkbox")),
            loginButton = $(byTestId("Login button"));
    private ElementsCollection privateContentList = $$(byTestId("Private content"));


    @Step("Open page {url}")
    void openPage(String url) {
        open(url);
    }

    @Step("Set value {login} to Login input")
    void setLoginInput(String login) {
        loginInput.setValue(login);
    }

    @Step("Set value {password} to Password input")
    void setPasswordInput(String password) {
        passwordInput.setValue(password);
    }

    @Step("Click Remember me checkbox")
    void clickRememberMeCheckbox() {
        rememberMeCheckbox.click();
    }

    @Step("Click submit form")
    void clickLoginButton() {
        loginButton.click();
    }

    @Step("Verify header label has text {text}")
    void verifyHeaderLabelHasText(String text) {
        headerLabel.shouldHave(text(text));
    }

    @Step("Authorization form should exist")
    void verifyAuthorizationFormExists() {
        authorizationForm.shouldBe(visible);
    }

    @Step("Authorization form should not exist")
    void verifyAuthorizationFormNotExists() {
        authorizationForm.shouldNot(exist);
    }

    @Step("Verify successful authorization")
    void verifyPrivateContentExists() {
        privateContentList
                .shouldHaveSize(2)
                .shouldHave(texts("Here is your private content #1", "and private content #2"));
    }
}
