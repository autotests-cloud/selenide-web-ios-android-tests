package cloud.autotests.tests.android;

import cloud.autotests.tests.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static cloud.autotests.helpers.SelenideHelper.byT;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


@Story("Login tests")
@Tag("android")
@Tag("login")
class LoginTests extends TestBase {
    @Test
    @Description("Successful login Android react-native app")
    @DisplayName("Successful login")
    void successfulLogin() {
        open();

        $(byT("Header label")).shouldHave(text("Not authorized"));
        $(byT("Authorization form")).shouldBe(visible);
        $(byT("Login input")).setValue(DEFAULT_LOGIN);
        $(byT("Password input")).setValue(DEFAULT_PASSWORD);
//        $(byT("Language select")).selectOption("English");
        $(byT("Remember me checkbox")).click();
        $(byT("Login button")).click();

        $(byT("Authorization form")).should(disappear);
        $(byT("Header label")).shouldHave(text("Hello, Alex!"));
        $$(byT("Private content"))
                .shouldHaveSize(2)
                .shouldHave(texts("Here is your private content #1", "and private content #2"));
    }
}