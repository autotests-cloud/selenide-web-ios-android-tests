package cloud.autotests.tests._other_patterns.super_difficult_react_component.extentions;

import com.codeborne.selenide.SelenideElement;


public interface ExtendedSelenideElement extends SelenideElement {
    ExtendedSelenideElement fillAuthorizationForm(String login, String password);
}
