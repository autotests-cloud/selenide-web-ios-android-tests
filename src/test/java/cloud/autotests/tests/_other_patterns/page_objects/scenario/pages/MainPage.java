package cloud.autotests.tests._other_patterns.page_objects.scenario.pages;

import io.qameta.allure.Step;

import static cloud.autotests.helpers.DriverHelper.byTestId;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class MainPage {
    @Step("Verify successful authorization")
    public void verifyIsLoggedInAs(String login) {
        $(byTestId("Authorization form")).shouldNot(exist);
        $(byTestId("Header label")).shouldHave(text("Hello, " + login + "!"));
        $$(byTestId("Private content"))
                .shouldHaveSize(2)
                .shouldHave(texts("Here is your private content #1",
                        "and private content #2"));
    }
}