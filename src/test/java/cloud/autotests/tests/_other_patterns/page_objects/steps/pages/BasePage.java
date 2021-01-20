package cloud.autotests.tests._other_patterns.page_objects.steps.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static cloud.autotests.helpers.DriverHelper.byTestId;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


class BasePage {
    private SelenideElement headerLabel = $(byTestId("Header label"));


    @Step("Verify header label has text {text}")
    public BasePage verifyHeaderLabelHasText(String text) {
        headerLabel.shouldHave(text(text));

        return this;
    }
}
