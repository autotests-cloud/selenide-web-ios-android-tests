package cloud.autotests.tests._kak_ne_nado_no_veselo.super_difficult_react_component;

import cloud.autotests.tests.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static cloud.autotests.helpers.DriverHelper.byTestId;
import static cloud.autotests.tests._kak_ne_nado_no_veselo.super_difficult_react_component.extentions.SelenideExtentions.$;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;


@Feature("Selenide-appium web, iOS and Android tests")
@Story("Login tests. Web")
@Tag("web")
@Tag("login")
class LoginTests extends TestBase {
    @Test
    @Description("Not very bad practice - we keep our script-like format and instead of actions extend SelenideElement")
    @DisplayName("Successful login in Web app. React-like components")
    void successfulLogin() {
        step("Go to login page", ()-> {
            open("");
            $(byTestId("Header label")).shouldHave(text("Not authorized"));
        });

        step("Fill the authorization form", ()-> { // Imagine, the form is a component
            $(byTestId("Authorization form")).fillAuthorizationForm(DEFAULT_LOGIN, DEFAULT_PASSWORD);
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


