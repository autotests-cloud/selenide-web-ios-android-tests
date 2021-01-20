package cloud.autotests.tests;

import io.qameta.allure.Step;
import org.junit.jupiter.api.*;

import static cloud.autotests.helpers.AttachmentsHelper.*;
import static cloud.autotests.helpers.BrowserstackHelper.getBSPublicLink;
import static cloud.autotests.helpers.DriverHelper.*;
import static cloud.autotests.helpers.EnvironmentHelper.*;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;


@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TestBase {

    public static String
            DEFAULT_LOGIN = "Alex",
            DEFAULT_PASSWORD = "12345";

    @BeforeAll
    @Step("Tests setup")
    public static void beforeAll() {
        configureSelenide();
    }

    @AfterEach
    @Step("Attachments")
    public void addAttachments(){
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
