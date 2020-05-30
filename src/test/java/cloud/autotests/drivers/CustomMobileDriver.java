package cloud.autotests.drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import static cloud.autotests.helpers.BrowserstackHelper.getBrowserstackUrl;
import static cloud.autotests.helpers.EnvironmentHelper.*;

public class CustomMobileDriver implements WebDriverProvider {

    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
        if (isAndroid) {
            return getAndroidDriver();
        } else if (isIos) {
            return getIosDriver();
        } else {
            return null; // not todo getWindowsPhoneDriver();
        }
    }

    private DesiredCapabilities commonCapabilities() { // todo update for local drivers, now only browserstack
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("project", "autotests.cloud");
        capabilities.setCapability("build", jobBaseName);
        capabilities.setCapability("name", "Tests - " + platform + " - " + buildNumber);
        capabilities.setCapability("autoGrantPermissions", "true");
//        capabilities.setCapability("browserstack.debug", "true");
//        capabilities.setCapability("browserstack.networkLogs", "true");
//        capabilities.setCapability("browserstack.gpsLocation", "51.51656, -0.1477");
//        capabilities.setCapability("browserstack.geoLocation", "GB");

        return capabilities;
    }

    public AndroidDriver getAndroidDriver() {
        DesiredCapabilities capabilities = commonCapabilities();
        capabilities.setCapability("device", androidDevice);
        capabilities.setCapability("os_version", androidVersion);
        capabilities.setCapability("app", androidBrowserstackApp);

        return new AndroidDriver(getBrowserstackUrl(), capabilities);
    }

    public IOSDriver getIosDriver() {
        DesiredCapabilities capabilities = commonCapabilities();
        capabilities.setCapability("device", iosDevice);
        capabilities.setCapability("os_version", iosVersion);
        capabilities.setCapability("autoAcceptAlerts", true);
        capabilities.setCapability("app", iosBrowserstackApp);

        return new IOSDriver(getBrowserstackUrl(), capabilities);
    }

}