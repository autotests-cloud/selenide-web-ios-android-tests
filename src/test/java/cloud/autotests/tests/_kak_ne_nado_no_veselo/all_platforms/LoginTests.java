package cloud.autotests.tests._kak_ne_nado_no_veselo.all_platforms;

import cloud.autotests.tests.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static cloud.autotests.helpers.DriverHelper.byTestId;
import static cloud.autotests.helpers.EnvironmentHelper.isWeb;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;


@Feature("Selenide-appium web, iOS and Android tests")
@Story("Login tests")
@Tag("android") @Tag("ios") @Tag("web")
@Tag("bad-practice")
@Tag("login")
class LoginTests extends TestBase {
    @Test
    @Description("Bad practice - one test for all platforms")
    @DisplayName("Successful login with web/iOS/Android react-native app")
    void successfulLogin() {
        step("Go to login page", () -> {
            if(isWeb) {
                open("");
            } else {
                open();
            }
            $(byTestId("Header label")).shouldHave(text("Not authorized"));
        });

        step("Fill the authorization form", () -> {
            $(byTestId("Authorization form")).shouldBe(visible);
            $(byTestId("Login input")).setValue(DEFAULT_LOGIN);
            $(byTestId("Password input")).setValue(DEFAULT_PASSWORD);
            $(byTestId("Remember me checkbox")).click();
            $(byTestId("Login button")).click();
        });

        step("Verify successful authorization", () -> {
            $(byTestId("Authorization form")).shouldNot(exist);
            $(byTestId("Header label")).shouldHave(text("Hello, " + DEFAULT_LOGIN + "!"));
            $$(byTestId("Private content"))
                    .shouldHaveSize(2)
                    .shouldHave(texts("Here is your private content #1",
                            "and private content #2"));
        });
    }
}
