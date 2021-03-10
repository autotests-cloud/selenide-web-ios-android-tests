package cloud.autotests.tests.web;

import cloud.autotests.tests.TestBase;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static cloud.autotests.helpers.DriverHelper.byTestId;
import static cloud.autotests.tests.TestData.DEFAULT_LOGIN;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;


@Feature("Selenide-appium web, iOS and Android tests")
@Story("Login tests. Web")
@Tag("web")
@Tag("login")
class WrongTests extends TestBase {

    @Test
    @DisplayName("Negative login in Web app with wrong url")
    void negativeLoginWithWrongUrlTest() {
        step("Go to login page", ()-> {
            open("asfgeserefe");
            $(byTestId("Header label")).shouldHave(text("Not authorized"));
        });

    }

}
