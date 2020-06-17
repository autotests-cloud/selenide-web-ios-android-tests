package cloud.autotests.helpers;

import cloud.autotests.drivers.CustomMobileDriver;
import cloud.autotests.drivers.CustomWebDriver;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import static cloud.autotests.helpers.EnvironmentHelper.*;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.logging.LogType.BROWSER;


public class DriverHelper {

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

    public static By byTestId(String testId) {
        if(isWeb) {
            return by("data-testid",  testId);
        } else if (isAndroid) {
            return MobileBy.xpath("//*[@content-desc='" + testId + "']");
        } else if (isIos) {
            return MobileBy.id(testId);
        } else { // todo isDesktop
            return by("some-desktop-attribute-name",  testId);
        }
    }

    public static String getSessionId(){
        return ((RemoteWebDriver) getWebDriver()).getSessionId().toString().replace("selenoid","");
    }

    public static String getConsoleLogs() {
        return String.join("\n", Selenide.getWebDriverLogs(BROWSER));
    }

//    public static String getNetworkLogs() {
//        todo https://ru.selenide.org/2019/12/18/advent-calendar-network-logs-with-proxy/
//    }

}