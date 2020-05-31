package cloud.autotests._experiments;

import cloud.autotests.tests.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static cloud.autotests.helpers.EnvironmentHelper.*;
import static cloud.autotests.helpers.SelenideHelper.byT;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;


@Story("Login tests")
@Tag("login")
class LoginTests extends TestBase {
    @Test
    @Description("Same test for web / ios / android") // its bad practice!
    @DisplayName("Successful login")
    void successfulWebIosAndroidLogin() {
        if(isWeb) {
            open("/login.html");
        } else if (isIos || isAndroid) {
            open();
        }

        $(byT("Header label")).shouldHave(text("Not authorized"));
        $(byT("Authorization form")).shouldBe(visible);
        $(byT("Login input")).setValue(DEFAULT_LOGIN);
        $(byT("Password input")).setValue(DEFAULT_PASSWORD);
        $(byT("Remember me checkbox")).click();
        $(byT("Login button")).click();

        $(byT("Authorization form")).should(disappear);
        $(byT("Header label")).shouldHave(text("Hello, Alex!"));
        $$(byT("Private content"))
                .shouldHaveSize(2)
                .shouldHave(texts("Here is your private content #1", "and private content #2"));
    }

    @Test
    @Tag("web")
    @Description("Same test with steps") // its best!
    @DisplayName("Successful login")
    void successfulStepsLogin() {
        step("Go to login page", ()-> {
            open("/login.html");
        });

        step("Fill the authorization form", ()-> {
            $(byT("Header label")).shouldHave(text("Not authorized"));
            $(byT("Authorization form")).shouldBe(visible);
            $(byT("Login input")).setValue(DEFAULT_LOGIN);
            $(byT("Password input")).setValue(DEFAULT_PASSWORD);
            $(byT("Remember me checkbox")).click();
            $(byT("Login button")).click();
        });

        step("Verify successful authorization", ()-> {
            $(byT("Authorization form")).should(disappear);
            $(byT("Header label")).shouldHave(text("Hello, Alex!"));
            $$(byT("Private content"))
                    .shouldHaveSize(2)
                    .shouldHave(texts("Here is your private content #1", "and private content #2"));
        });
    }

    @Test
    @Tag("web")
    @Description("Same test with page object") // its standart
    @DisplayName("Successful login")
    void successfulPOLogin() {
        // todo
    }


}
