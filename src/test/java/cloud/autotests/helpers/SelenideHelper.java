package cloud.autotests.helpers;

import cloud.autotests.drivers.CustomMobileDriver;
import cloud.autotests.drivers.CustomWebDriver;
import com.codeborne.selenide.Configuration;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

import static cloud.autotests.helpers.EnvironmentHelper.*;
import static com.codeborne.selenide.Selectors.*;


public class SelenideHelper {

    // Documentation: https://selenide.org/documentation.html
    public static void configureSelenide() {
        if (isWeb) {
            Configuration.browser = CustomWebDriver.class.getName();
            Configuration.baseUrl = webUrl;
        } else if (isAndroid || isIos) {
            Configuration.browser = CustomMobileDriver.class.getName();
            Configuration.startMaximized = false;
            Configuration.browserSize = null;
        }
        Configuration.timeout = 10000;
    }

    public static By byT(String dataTestid) {
        if(isWeb) {
            return by("data-testid",  dataTestid);
        } else if (isAndroid) {
            return byXpath("//*[@content-desc='" + dataTestid + "']");
        } else if (isIos) {
            return MobileBy.id(dataTestid);
        } else {
            return null; // todo isDesktop
        }
    }
}