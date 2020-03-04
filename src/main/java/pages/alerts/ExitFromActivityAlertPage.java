package pages.alerts;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;

public class ExitFromActivityAlertPage extends BasePage {
    public final static String TEXT_TITLE = "Back to home page?";
    public final static String TEXT_BODY = "The changes will be lost. Are you sure you do not want to save the changes?";

    private WebElement btnYes() {
        return getElementById("android:id/button1");
    }

    private WebElement btnNo() {
        return getElementById("android:id/button2");
    }

    private WebElement tvTitle() {
        return getElementById("android:id/alertTitle");
    }

    private WebElement tvBody() {
        return getElementById("android:id/message");
    }

    public ExitFromActivityAlertPage(AndroidDriver driver) {
        super(driver);
    }

    public void confirmExit() {
        click(btnYes());
    }

    public void declineExit() {
        click(btnNo());
    }

    public String getTitleText() {
        return tvTitle().getText();
    }

    public String getBodyText() {
        return tvBody().getText();
    }

    public boolean isBodyDisplayed() {
        return tvBody().getText() != null && !tvBody().getText().equals("");
    }

    public boolean isTitleDisplayed() {
        return tvTitle().getText() != null && !tvTitle().getText().equals("");
    }
}
