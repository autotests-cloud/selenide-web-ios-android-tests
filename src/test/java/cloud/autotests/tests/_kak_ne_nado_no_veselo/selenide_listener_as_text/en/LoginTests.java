package cloud.autotests.tests._kak_ne_nado_no_veselo.selenide_listener_as_text.en;

import cloud.autotests.tests.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static cloud.autotests.helpers.DriverHelper.byTestId;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static com.codeborne.selenide.logevents.SelenideLogger.removeListener;
import static io.qameta.allure.Allure.step;


@Feature("Selenide-appium web, iOS and Android tests")
@Story("Login tests. Web")
@Tag("not-very-bad-practice")
@Tag("web")
@Tag("login")
class LoginTests extends TestBase {

    @BeforeEach
    void addListeners() {
        removeListener("AllureSelenide");
        addListener("AllureSelenideAsText", new AllureSelenideAsText().screenshots(true).savePageSource(true));
    }

    @Test
    @Description(
            "Added simple selenide listener - replaced $()-like output to readable text,<br/>" +
            "<b>$(\"[data-testid=\"Header label\"]\") should have(text 'Not authorized')</b> -> <br/>" +
            "\"Header label\" should have text \"Not authorized\"")
    @DisplayName("Successful login in Web app. AllureSelenideAsText listener (EN)")
    void successfulLogin() {
        step("Go to login page", ()-> {
            open("");
            $(byTestId("Header label")).shouldHave(text("Not authorized"));
        });

        step("Fill the authorization form", ()-> {
            $(byTestId("Authorization form")).shouldBe(visible);
            $(byTestId("Login input")).setValue(DEFAULT_LOGIN);
            $(byTestId("Password input")).setValue(DEFAULT_PASSWORD);
            $(byTestId("Remember me checkbox")).click();
            $(byTestId("Login button")).click();
        });

        step("Verify successful authorization", ()-> {
            $(byTestId("Authorization form")).shouldNot(exist);
            $(byTestId("Header label")).shouldHave(text("Hello, " + DEFAULT_LOGIN + "!"));
            $$(byTestId("Private content"))
                    .shouldHaveSize(2)
                    .shouldHave(texts("Here is your private content #1",
                            "and private content #2"));
        });
    }
}
