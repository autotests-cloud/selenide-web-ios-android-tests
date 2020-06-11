package cloud.autotests.tests._kak_ne_nado_no_veselo.page_objects.scenario;

import io.qameta.allure.Step;

import static cloud.autotests.helpers.DriverHelper.byTestId;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


class LoginPage {
    @Step("Go to login page")
    void goToLoginPage() {
        open("");
        $(byTestId("Header label")).shouldHave(text("Not authorized"));
    }

    @Step("Fill the authorization form")
    void fillAuthorizationForm(String login, String password) {
        $(byTestId("Authorization form")).shouldBe(visible);
        $(byTestId("Login input")).setValue(login);
        $(byTestId("Password input")).setValue(password);
        $(byTestId("Remember me checkbox")).click();
        $(byTestId("Login button")).click();
    }

    @Step("Verify successful authorization")
    void verifyIsLoggedInAs(String login) {
        $(byTestId("Authorization form")).shouldNot(exist);
        $(byTestId("Header label")).shouldHave(text(login));
        $$(byTestId("Private content"))
                .shouldHaveSize(2)
                .shouldHave(texts("Here is your private content #1", "and private content #2"));
    }
}
