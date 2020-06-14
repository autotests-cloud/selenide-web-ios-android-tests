package cloud.autotests.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static cloud.autotests.helpers.AttachmentsHelper.*;
import static cloud.autotests.helpers.BrowserstackHelper.getBSPublicLink;
import static cloud.autotests.helpers.DriverHelper.*;
import static cloud.autotests.helpers.EnvironmentHelper.*;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;


@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TestBase {

    public static String
            DEFAULT_LOGIN = "Alex",
            DEFAULT_PASSWORD = "12345";

    @BeforeAll
    public static void beforeAll() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        configureSelenide();
    }

    @AfterEach
    public void afterEach(){
        String sessionId = getSessionId();

        attachScreenshot("Last screenshot");
        attachPageSource();
//        attachNetwork(); // todo
        if (isWeb) attachAsText("Browser console logs", getConsoleLogs());
        if (isIos || isAndroid) attachAsText("Browserstack build link", getBSPublicLink(sessionId));

        closeWebDriver();

        if (isVideoOn) attachVideo(sessionId); // in browserstack video url generates after driver close
    }
}



