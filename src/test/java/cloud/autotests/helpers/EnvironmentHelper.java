package cloud.autotests.helpers;

import static java.lang.Boolean.parseBoolean;


public class EnvironmentHelper {

// PLATFORM CONFIG
    public final static String
        platform = System.getProperty("platform", "web");
//        platform = System.getProperty("platform", "android");
//        platform = System.getProperty("platform", "ios");

// WEB CONFIG
    public static final boolean isWeb = platform.equals("web");
    public static final String
        webUrl = "http://" + System.getProperty("web_url", "autotests.cloud"),
//        webUrl = "https://" + System.getProperty("web_url", "test.autotests.cloud"),
//        webUrl = "https://" + System.getProperty("web_url", "staging.autotests.cloud"),
//        webUrl = "https://login:password@" + System.getProperty("web_url", "staging.autotests.cloud"),
        apiUrl = "https://" + System.getProperty("api_url", "api.autotests.cloud"),
        browser = System.getProperty("browser", "chrome"),
//        browser = System.getProperty("browser", "firefox"),
//        browser = System.getProperty("browser", "opera"),
        screenResolution = System.getProperty("screen_resolution", "1360x768"),
        webMobileDevice = System.getProperty("web_mobile_device"),
//        webMobileDevice = System.getProperty("web_mobile_device", "iPhone X");
        remoteDriverUrl = System.getProperty("remote_driver_url"), // https://username:password@selenoid.autotests.cloud:4444/wd/hub/
        videoStorageUrl = System.getProperty("video_storage_url"); // https://selenoid.autotests.cloud/video/
    public static final boolean
        isHeadless = parseBoolean(System.getProperty("headless", "false")),
        isWebMobile = webMobileDevice != null,
        isRemoteDriver = remoteDriverUrl != null,
        isVideoOn = videoStorageUrl != null;

// BROWSERSTACK CONFIG
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

// CI CONFIG (jenkins)
    public static final String
            buildNumber = System.getProperty("build_number", "0"),
            jobBaseName = System.getProperty("job_base_name", "local");
}
