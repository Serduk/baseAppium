package pages.alerts;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.NoAlertPresentException;
import pages.BasePage;

public class BaseAlertPage extends BasePage {
    @AndroidFindBy(id = "com.android.packageinstaller:id/permission_message")
    private MobileElement dialogText;
    @AndroidFindBy(id = "com.android.packageinstaller:id/permission_deny_button")
    private MobileElement btnDenyPermission;
    @AndroidFindBy(id = "com.android.packageinstaller:id/permission_allow_button")
    private MobileElement btnGrantPermission;
    /**
     * Alert, showing sometimes if permissions not granted
     */
    @AndroidFindBy(id = "android:id/alertTitle")
    private MobileElement appTittleAllert;
    /**
     * Alert, showing sometimes if permissions not granted
     */
    @AndroidFindBy(id = "android:id/message")
    private MobileElement messageAllert;

    public BaseAlertPage(AndroidDriver driver) {
        super(driver);
    }

    public void denyAccess() {
        sleep(2);
        driver.switchTo().alert().dismiss();
    }

    public void acceptAccess() {
        sleep(2);
        driver.switchTo().alert().accept();
    }

    public boolean isAlertDisplayed() {
        sleep(2);
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException ex) {
            return false;
        }
    }
}
