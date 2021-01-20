package cloud.autotests.tests._other_patterns.super_difficult_react_component.extentions;

import com.codeborne.selenide.commands.Commands;
import com.codeborne.selenide.impl.ElementFinder;
import org.openqa.selenium.By;

import static com.codeborne.selenide.WebDriverRunner.driver;


public class SelenideExtentions {
    public static ExtendedSelenideElement $(By by) {
        Commands.getInstance().add("fillAuthorizationForm", new FillAuthorizationForm());

        return ElementFinder.wrap(driver(), ExtendedSelenideElement.class, null, by, 0);
    }
}
