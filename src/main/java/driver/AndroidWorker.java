package driver;

import constants.Constants;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Class which init android driver
 * By default use pre-setted preferences which displayed in getCapabilities()
 * if User will have some another custom preferences or capabilities we should put it to hashmap from getDriver()
 * this map will parse all data, and change or replace.
 * */
public class AndroidWorker {

    public AndroidDriver getDriver(HashMap<String, String> prefs) {
        AndroidDriver driver;

        String serverUrl = Constants.DEFAULT_SERVER_URL;

        if (prefs.get(MyCapabilities.SERVER_URL) != null) {
            serverUrl = prefs.get(MyCapabilities.SERVER_URL);
        }

        try {
            driver = new AndroidDriver(new URL(serverUrl), getCapabilities(prefs));
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            driver = null;
        }

        return driver;
    }

    /**
     * get capabilities
     * */
    private DesiredCapabilities getCapabilities(HashMap<String, String> data) {
        DesiredCapabilities dc = new DesiredCapabilities();

        dc.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9");

        //            TODO: get system prop instead
        dc.setCapability(MobileCapabilityType.APP, "PATH_TO_APK/APK_PATH.apk");
        dc.setCapability(MobileCapabilityType.APP_ACTIVITY, "MainActivity");
        dc.setCapability(MobileCapabilityType.APP_PACKAGE, Constants.APP_PACKAGE);
        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

        if (data.get(MyCapabilities.DEVICE_NAME) != null) {
            dc.setCapability(MobileCapabilityType.DEVICE_NAME, data.get(MyCapabilities.DEVICE_NAME));
        }

        if (data.get(MyCapabilities.PLATFORM_NAME) != null) {
            dc.setCapability(MobileCapabilityType.PLATFORM_NAME, data.get(MyCapabilities.PLATFORM_NAME));
        }

        if (data.get(MyCapabilities.PLATFORM_VERSION) != null) {
            dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, data.get(MyCapabilities.PLATFORM_VERSION));
        }

        if (data.get(MyCapabilities.APP) != null) {
            dc.setCapability(MobileCapabilityType.APP, data.get(MyCapabilities.APP));
        }

        if (data.get(MyCapabilities.APP_ACTIVITY) != null) {
            dc.setCapability(MobileCapabilityType.APP_ACTIVITY, data.get(MyCapabilities.APP_ACTIVITY));
        }

        if (data.get(MyCapabilities.APP_PACKAGE) != null) {
            dc.setCapability(MobileCapabilityType.APP_PACKAGE, data.get(MyCapabilities.APP_PACKAGE));
        }

        if (data.get(MyCapabilities.AUTOMATION_NAME) != null) {
            dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, data.get(MyCapabilities.AUTOMATION_NAME));
        }

        return dc;
    }

    /**
     * Custom capabilities which used for updating data for driver from test class
     * */
    public interface MyCapabilities {
        String SERVER_URL = "Url";
        String AUTOMATION_NAME = "automationName";

        String PLATFORM_NAME = "platformName";
        String PLATFORM_VERSION = "platformVersion";

        String DEVICE_NAME = "deviceName";

        String NEW_COMMAND_TIMEOUT = "newCommandTimeout";
        String DEVICE_READY_TIMEOUT = "deviceReadyTimeout";
        String LAUNCH_TIMEOUT = "launchTimeout";

        String APP = "app";
        String APP_PACKAGE = "appPackage";
        String APP_ACTIVITY = "appActivity";
        String APP_WAIT_ACTIVITY = "appWaitActivity";
        String APP_WAIT_PACKAGE = "appWaitPackage";
        String SELENDROID_PORT  = "selendroidPort";
    }
}
