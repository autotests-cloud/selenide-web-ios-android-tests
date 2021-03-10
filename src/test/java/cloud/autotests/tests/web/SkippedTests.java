package cloud.autotests.tests.web;

import cloud.autotests.tests.TestBase;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Disabled;
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
@Tag("web")
@Tag("login")
class SkippedTests extends TestBase {

    @Test
    @Disabled
    @DisplayName("Skipped login test #1")
    void skippedTest1() {
        step("Go to login page", ()-> {
            open("http://app.autotests.cloud");
            $(byTestId("Header label")).shouldHave(text("Not authorized"));
        });
    }


    @Test
    @Disabled
    @DisplayName("Skipped login test #2")
    void skippedTest2() {
        step("Go to login page", ()-> {
            open("http://app.autotests.cloud");
            $(byTestId("Header label")).shouldHave(text("Not authorized"));
        });
    }


    @Test
    @Disabled
    @DisplayName("Skipped login test #3")
    void skippedTest3() {
        step("Go to login page", ()-> {
            open("http://app.autotests.cloud");
            $(byTestId("Header label")).shouldHave(text("Not authorized"));
        });
    }


    @Test
    @Disabled
    @DisplayName("Skipped login test #4")
    void skippedTest4() {
        step("Go to login page", ()-> {
            open("http://app.autotests.cloud");
            $(byTestId("Header label")).shouldHave(text("Not authorized"));
        });
    }

}
