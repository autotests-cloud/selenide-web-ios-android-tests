package cloud.autotests.tests._kak_ne_nado_no_veselo.page_objects.steps;

import cloud.autotests.tests.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


@Feature("Selenide-appium web, iOS and Android tests")
@Story("Login tests")
@Tag("bad-practice")
@Tag("login")
class LoginTests extends TestBase {
    LoginPage loginPage = new LoginPage();

    @Test
    @Description("Bad practice - pag")
    @DisplayName("Successful login with web react-native app")
    void successfulLogin() {
        loginPage.openPage("");
        loginPage.verifyHeaderLabelHasText("Not authorized");

        loginPage.verifyAuthorizationFormExists();
        loginPage.setLoginInput(DEFAULT_LOGIN);
        loginPage.setPasswordInput(DEFAULT_PASSWORD);
        loginPage.clickRememberMeCheckbox();
        loginPage.clickLoginButton();

        loginPage.verifyAuthorizationFormNotExists();
        loginPage.verifyHeaderLabelHasText("Hello, Alex!");
        loginPage.verifyPrivateContentExists();
    }
}
