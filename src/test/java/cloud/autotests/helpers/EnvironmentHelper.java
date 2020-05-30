package cloud.autotests.helpers;

import static cloud.autotests.utils.FileUtils.getResourcesPath;
import static java.lang.Boolean.parseBoolean;


public class EnvironmentHelper {
// COMMON CONFIG
    public final static String
        platform = System.getProperty("platform", "web"),
//        platform = System.getProperty("platform", "android"),
//        platform = System.getProperty("platform", "ios"),
        language = System.getProperty("language", "en");
//        language = System.getProperty("language", "sp");

// CI CONFIG
    public static final String
        buildNumber = System.getProperty("build_number", "0"),
        jobBaseName = System.getProperty("job_base_name", "local");

// WEB CONFIG
    public static final boolean isWeb = platform.equals("web");
    public static final String
        webUrl = System.getProperty("web_url", getResourcesPath()),
//        webUrl = "https://" + System.getProperty("web_url", "autotests.cloud"),
//        webUrl = "https://" + System.getProperty("web_url", "test.autotests.cloud"),
//        webUrl = "https://" + System.getProperty("web_url", "staging.autotests.cloud"),
//        webUrl = "https://login:password@" + System.getProperty("web_url", "staging.autotests.cloud"),
        apiUrl = "https://" + System.getProperty("api_url", "api.autotests.cloud"),
        browser = System.getProperty("browser", "chrome"),
//        browser = System.getProperty("browser", "firefox"),
//        browser = System.getProperty("browser", "opera"),
        screenResolution = System.getProperty("screen_resolution", "1360x768"),
        webMobileDevice = System.getProperty("web_mobile_device", "");
//        webMobileDevice = System.getProperty("web_mobile_device", "iPhone X");
    public static final boolean
        isWebMobile = (webMobileDevice + "").length() > 0,
        isHeadless = parseBoolean(System.getProperty("headless", "false")),
        isRemoteDriver = parseBoolean(System.getProperty("remote_driver", "false")),
        isVideoOn = parseBoolean(System.getProperty("video", "false"));
    public static final String
        remoteDriverUrl = "http://" + System.getProperty("remote_driver_url", "localhost") + ":4444",
        videoHost = "http://" + System.getProperty("video_host", "localhost");

// Mobile config for devices in browserstack
    public static final String
        bsLogin = System.getProperty("bs_login", ""),
        bsPassword = System.getProperty("bs_password", "");

// ANDROID CONFIG
    public static final boolean isAndroid = platform.equals("android");
    public static final String
        androidDevice = System.getProperty("mobile_device", "Google Pixel 3"),
        androidVersion = System.getProperty("mobile_version", "9.0"),
        androidBrowserstackApp = System.getProperty("bs_app", "bs://b752233ee406fe9af5165061843feabc0...");

// IOS CONFIG
    public static final boolean isIos = platform.equals("ios");
    public static final String
        iosDevice = System.getProperty("mobile_device", "iPhone 11 Pro Max"),
        iosVersion = System.getProperty("mobile_version", "13.2"),
        iosBrowserstackApp = System.getProperty("bs_app", "bs://7dccae106ba5955af20c92a0ceb9...");


    public static String getEnvironment() {
        String environment =
            "\nplatform: " + platform +
            "\nlanguage: " + language +
            "\nisVideoOn: " + isVideoOn +
            (isVideoOn && isWeb ? "\nvideoHost: " + videoHost : "") +
            "\nbuildNumber: " + buildNumber +
            "\njobBaseName: " + jobBaseName;

        switch (platform) {
            case "web":
                environment +=
                    "\nurl: " + webUrl +
                    "\napiUrl: " + apiUrl +
                    "\nbrowser: " + browser +
                    "\nisWebMobile: " + isWebMobile +
                    (isWebMobile ?
                        "\nwebMobileDevice: " + webMobileDevice :
                        "\nscreenResolution: " + screenResolution) +
                    "\nisRemoteDriver: " + isRemoteDriver +
                    "\nremoteDriver: " + remoteDriverUrl;
                break;

            case "android":
                environment +=
                    "\nandroidDevice: " + androidDevice +
                    "\nandroidVersion: " + androidVersion +
                    "\nandroidBrowserstackApp: " + androidBrowserstackApp;
                break;

            case "ios":
                environment +=
                    "\niosDevice: " + iosDevice +
                    "\niosVersion: " + iosVersion +
                    "\niosBrowserstackApp: " + iosBrowserstackApp;
                break;
        }

        return environment;
    }
}
