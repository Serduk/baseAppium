package pages.alerts;

import io.appium.java_client.android.AndroidDriver;

public class CameraAlertPage extends BaseAlertPage {
    public CameraAlertPage(AndroidDriver driver) {
        super(driver);
    }

    public void denyCameraAccess() {
        denyAccess();
    }

    public void acceptCameraAccess() {
        acceptAccess();
    }

    public boolean isAlertDisplayed() {
        return super.isAlertDisplayed();
    }
}
