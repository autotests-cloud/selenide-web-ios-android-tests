package cloud.autotests.tests._kak_ne_nado_no_veselo.selenide_listener_as_text.ru;

import cloud.autotests.tests.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

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
        addListener("AllureSelenideAsText", new AllureSelenideAsRuText().screenshots(true).savePageSource(true));
    }

    @Test
    @Description(
            "Добавлен простой selenide listener - заменены $()-подобные выводы на читаемый текст,<br/>" +
            "<b>$(\"[data-testid=\"Надпись заголовка\"]\") should have(text 'Пожалуйста, авторизуйтесь')</b> -> <br/>" +
            "\"Надпись заголовка\" должен(а) иметь текст \"Пожалуйста, авторизуйтесь\"")
    @DisplayName("Successful login in Web app. AllureSelenideAsText listener (RU)")
    void successfulLogin() {
        step("Открытие страницы авторизации", ()-> {
            open("/#ru");
            $(byTestId("Надпись заголовка")).shouldHave(text("Пожалуйста, авторизуйтесь"));
        });

        step("Заполнение формы авторизации", ()-> {
            $(byTestId("Форма авторизации")).shouldBe(visible);
            $(byTestId("Поле ввода логина")).setValue(DEFAULT_LOGIN);
            $(byTestId("Поле ввода пароля")).setValue(DEFAULT_PASSWORD);
            $(byTestId("Чекбокс запомнить меня")).click();
            $(byTestId("Кнопка входа")).click();
        });

        step("Проверка успешной авторизации", ()-> {
            $(byTestId("Форма авторизации")).shouldNot(exist);
            $(byTestId("Надпись заголовка")).shouldHave(text("Привет, " + DEFAULT_LOGIN + "!"));
            $$(byTestId("Приватный контент"))
                    .shouldHaveSize(2)
                    .shouldHave(texts("Ваш приватный контент #1 ",
                            "и приватный контент #2"));
        });
    }
}
