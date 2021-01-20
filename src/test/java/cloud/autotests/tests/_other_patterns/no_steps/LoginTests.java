package cloud.autotests.tests._other_patterns.no_steps;

import cloud.autotests.tests.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static cloud.autotests.helpers.DriverHelper.byTestId;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;


@Feature("Selenide-appium web, iOS and Android tests")
@Story("Login tests. Web")
@Tag("bad-practice")
@Tag("web")
@Tag("login")
class LoginTests extends TestBase {
    @Test
    @Description("Bad practice - big prostynja without steps")
    @DisplayName("Successful login in Web app. Testid-strategy. No steps")
    void successfulLogin() {
        open("https://autotests.cloud");
        $(byTestId("Header label")).shouldHave(text("Not authorized"));

        $(byTestId("Authorization form")).shouldBe(visible);
        $(byTestId("Login input")).setValue(DEFAULT_LOGIN);
        $(byTestId("Password input")).setValue(DEFAULT_PASSWORD);
        $(byTestId("Remember me checkbox")).click();
        $(byTestId("Login button")).click();

        $(byTestId("Authorization form")).shouldNot(exist);
        $(byTestId("Header label")).shouldHave(text("Hello, Alex!"));
        $$(byTestId("Private content"))
                .shouldHaveSize(2)
                .shouldHave(texts("Here is your private content #1",
                        "and private content #2"));
    }
}
