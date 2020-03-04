package pages.dateAndTimePickers;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;

/**
 * Default class for working with android time picker
 */
public class TimePickerPage extends BasePage {
    public TimePickerPage(AndroidDriver driver) {
        super(driver);
    }

    private WebElement pickerTimer() {
        return getElementById("android:id/timePickerLayout");
    }

    private WebElement btnDatePickerOk() {
        return getElementById("android:id/button1");
    }

    private WebElement btnDatePickerCancel() {
        return getElementById("android:id/button2");
    }

    private WebElement etHour() {
        return getElementById("android:id/hours");
    }

    private WebElement etMinutes() {
        return getElementById("android:id/minutes");
    }

    private WebElement btnAm() {
        return getElementById("android:id/am_label");
    }

    private WebElement btnPm() {
        return getElementById("android:id/pm_label");
    }

    /**
     * Default available items in Dial
     * Work only for items which % 5
     * example: 0, 5, 10, 15, 20, 25
     * For now, we can't select minutes like 33
     * Time: 11:33 -> not allowed
     * time: 11:35 -> allowed
     */
    private WebElement tvTimePath(String hour) {
        return getElementByXPath("//*[@content-desc='" + hour + "']");
    }

    public void selectTime(String hour, String minutes, TimeMidnight midnight) {
        click(tvTimePath(hour));
        click(tvTimePath(minutes));

        if (midnight == TimeMidnight.CURRENT) return;

        switch (midnight) {
            case AM:
                click(btnAm());
                break;
            case PM:
                click(btnPm());
        }
    }

    public void confirmTime() {
        click(btnDatePickerOk());
    }

    public void declineTime() {
        click(btnDatePickerCancel());
    }

    public String getHour() {
        return getText(etHour());
    }

    public String getMinutes() {
        return getText(etMinutes());
    }

    public void setMorning() {
        click(btnAm());
    }

    public void setEvening() {
        click(btnPm());
    }
}
