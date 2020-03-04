package pages.dateAndTimePickers;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;

import java.util.Arrays;
import java.util.List;

/**
 * Helper class
 * Base class for working with default Android date picker
 * Mostly used in Logging pages
 */
public class AndroidDatePicker extends BasePage {
    public AndroidDatePicker(AndroidDriver driver) {
        super(driver);
    }

    private WebElement btnDatePickerOk() {
        return getElementById("android:id/button1");
    }

    private WebElement btnDatePickerCancel() {
        return getElementById("android:id/button2");
    }

    public void confirmDate() {
        click(btnDatePickerOk());
    }

    public void declineDate() {
        click(btnDatePickerCancel());
    }

    public void selectDate(Months month, String dayStr, String year) {
        int day;

        try {
            day = Integer.valueOf(dayStr);
        } catch (ClassCastException ex) {
            day = 1;
        }

        selectYear(year);
        selectMonth(month);
        selectDay(day);
    }

    //    ex: 1970
    private WebElement selectedYear() {
        return getElementById("android:id/date_picker_header_year");
    }

    //    ex: Fri, JAN 9
    private WebElement selectedDay() {
        return getElementById("android:id/date_picker_header_date");
    }

    private WebElement btnPreviousMonth() {
        return getElementById("android:id/prev");
    }

    private WebElement btnNextMonth() {
        return getElementById("android:id/next");
    }

    /**
     * @return available days in selected month
     */
    private List<WebElement> getDaysInMonth() {
        return getElementsByXPath("//*[@resource-id = 'android:id/month_view']//*[@content-desc]");
    }

    private void goToPreviousMonth() {
        click(btnPreviousMonth());
    }

    private void goToNextMonth() {
        click(btnNextMonth());
    }

    /**
     * Select available day
     * If selected day > than available days in month -> click on last day in month
     */
    private void selectDay(int day) {
        int position = day - 1;
        List<WebElement> days = getDaysInMonth();

        if (days.size() > position && position > 0) days.get(position).click();
    }

    /**
     * Magic
     * Work only for current year, for now
     * <p>
     * how it work:
     *
     * @param month take enum from
     * @see Months
     * <p>
     * and convert it to string
     * Then, we call
     * @see #months() which has list with month in correct queue
     * <p>
     * Then we check difference in position of current month and selected month
     * Then we click on next or previous buttons in "If Else" construction
     */
    private void selectMonth(Months month) {
        String currentMonth = getText(selectedDay()).split(" ")[1].toUpperCase();
        List<Months> listOfMonths = months();
        int currentMonthIndex = listOfMonths.indexOf(Months.valueOf(currentMonth));
        int selectedMonthIndex = listOfMonths.indexOf(month);

        if (currentMonthIndex > selectedMonthIndex) {
            for (int i = 0; i < currentMonthIndex - selectedMonthIndex; i++) {
                goToPreviousMonth();
            }
        } else if (currentMonthIndex < selectedMonthIndex) {
            for (int i = 0; i < selectedMonthIndex - currentMonthIndex; i++) {
                goToNextMonth();
            }
        }
    }

    /**
     * WorkAround for default date picker in Android Apps
     * For now, each type after swiping we confirm date, and then check condition
     * How it work:
     *
     * @param year take year, which we should set in app
     *             <p>
     *             After calendar opening, We get all available years in list,
     *             If our data is unavailable -> we select first/last available year
     *             (depend from current and date which should be selected)
     *             Inside method we use loop for checking year
     *             If selected year +/- 2 from year which should be selected ->
     *             We get list with available data in calendar List, and try found year
     *             <p>
     *             IMPORTANT: If Year not visible on UI, method always click on last/first available in list.
     *             This workaround should be placed for now, because, after each clicking on year ->
     *             Years in calendars will be centered, and all String Years visible
     */
    private void selectYear(String year) {
        int currentYear, selectedYear;

        try {
            currentYear = Integer.valueOf(getText(selectedYear()));
            selectedYear = Integer.valueOf(year);
        } catch (ClassCastException ex) {
            return;
        }

        while (currentYear != selectedYear) {
            click(selectedYear());

            List<WebElement> yearsList = getElementsByXPath("/hierarchy/android.widget.FrameLayout/android.widget" +
                    ".FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget" +
                    ".FrameLayout/android.widget.FrameLayout/android.widget.DatePicker/android.widget" +
                    ".LinearLayout/android.widget.ScrollView/android.widget.ViewAnimator/android.widget" +
                    ".ListView/android.widget.TextView");

//        to past
            if (currentYear > selectedYear) {
                if (yearComparison(year, yearsList)) {
                    break;
                } else {
                    click(yearsList.get(0));
                }
            }

//        to future
            if (currentYear < selectedYear) {
                if (yearComparison(year, yearsList)) {
                    break;
                } else {
                    click(yearsList.get(yearsList.size() - 1));
                }
            }

            currentYear = Integer.valueOf(getText(selectedYear()));
        }
    }

    /**
     * @return ordered queue with Month list
     * We can navigate to any month by index
     * TODO: Simplify it in future. Just call Arrays.asList from
     * @see #selectMonth(Months)
     */
    private List<Months> months() {
        return Arrays.asList(Months.values());
    }

    /**
     * Comparator for years in list of visible years
     * If items equals: Click on item
     */
    private boolean yearComparison(String year, List<WebElement> years) {
        for (WebElement yearVisible : years) {
            String yearInView = getText(yearVisible);
            if (yearInView.equals(year)) {
                click(yearVisible);
                return true;
            }
        }
        return false;
    }
}
