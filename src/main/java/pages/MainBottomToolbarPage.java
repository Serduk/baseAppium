package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class MainBottomToolbarPage extends BasePage {
    public MainBottomToolbarPage(AndroidDriver driver) {
        super(driver);
        loadPage(getClass().getName());
    }

    @AndroidFindBy(xpath = "gjhg")
    MobileElement btnSettings;

    public void goToSettings() {
//        btnSettings.click();
        click(btnSettings);
    }
}
