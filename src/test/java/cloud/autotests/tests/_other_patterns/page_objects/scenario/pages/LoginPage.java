package cloud.autotests.tests._other_patterns.page_objects.scenario.pages;

import io.qameta.allure.Step;

import static cloud.autotests.helpers.DriverHelper.byTestId;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class LoginPage {
    @Step("Go to login page")
    public void goToLoginPage() {
        open("https://autotests.cloud");
        $(byTestId("Header label")).shouldHave(text("Not authorized"));
    }

    @Step("Fill the authorization form")
    public void fillAuthorizationForm(String login, String password) {
        $(byTestId("Authorization form")).shouldBe(visible);
        $(byTestId("Login input")).setValue(login);
        $(byTestId("Password input")).setValue(password);
        $(byTestId("Remember me checkbox")).click();
        $(byTestId("Login button")).click();
    }
}
