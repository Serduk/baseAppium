package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SettingsPage extends BasePage {
    public SettingsPage(AndroidDriver driver) {
        super(driver);
        loadPage(getClass().getName());
    }

    @AndroidFindBy(xpath = "kgjghg")
    MobileElement logoutBtn;

    public void logout() {
        click(logoutBtn);
    }
}
