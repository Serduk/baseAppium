package pages.alerts;

import io.appium.java_client.android.AndroidDriver;

public class LocationAlertPage extends BaseAlertPage {
    public LocationAlertPage(AndroidDriver driver) {
        super(driver);
    }

    public void denyLocationAccess() {
        denyAccess();
    }

    public void acceptLocationAccess() {
        acceptAccess();
    }

    public boolean isAlertDisplayed() {
        return super.isAlertDisplayed();
    }
}
