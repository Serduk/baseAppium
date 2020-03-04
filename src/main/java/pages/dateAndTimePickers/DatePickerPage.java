package pages.dateAndTimePickers;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.BasePage;

/**
 * Helper class
 * Base class for working with default Android date picker
 * Mostly used in Logging pages
 * <p>
 * // * @see pages.app.logging.water.WaterLoggingPage as example
 *
 * @deprecated
 * @see AndroidDatePicker
 */
public class DatePickerPage extends BasePage {
    public DatePickerPage(AndroidDriver driver) {
        super(driver);
    }

    protected WebElement btnDatePickerOk() {
        return getElementById("android:id/button1");
    }

    protected WebElement btnDatePickerCancel() {
        return getElementById("android:id/button2");
    }

    private WebElement pickerDate() {
        return getElementById("android:id/pickers");
    }

    /**
     * Solutions example:
     * https://github.com/appium/appium/issues/6591
     * https://github.com/appium/appium/issues/10442
     * http://easybix.com/handle-date-appium/
     */
    public void selectDate(String month, String day, String year) {
        WebElement first = driver.findElement(By.xpath("path"));

        WebElement second = driver.findElement(By.xpath("path"));

        WebElement third = driver.findElement(By.xpath("path"));

        setKeys(first, month);
        setKeys(second, day);
        setKeys(third, year);
    }

    public void confirmDate() {
        click(btnDatePickerOk());
    }

    public void declineDate() {
        click(btnDatePickerCancel());
    }
}
