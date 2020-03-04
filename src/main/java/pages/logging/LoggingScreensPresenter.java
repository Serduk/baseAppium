package pages.logging;

import pages.dateAndTimePickers.TimeMidnight;

/**
 * Interface for basic navigation on Logging screens
 * All Logging screens has same logic, but has different path to elements
 *
// * @see pages.app.logging.water.WaterLoggingPage
// * @see pages.app.logging.weight.WeightLoggingPage
 * as example
 */
public interface LoggingScreensPresenter {
    /**
     * Tap on Button Log
     */
    void log();

    /**
     * User can change Date for logging his Logging
     *
     * @param year  used in {@link utils.RandomUtils#getYearFromCurrent(int)}
     * @param month in {@link utils.RandomUtils#getMonthFromCurrent(int)}
     * @param day   in {@link utils.RandomUtils#getDayFromCurrent(int)}
     *              <p>
     *              Very agile class which can set any date inside.
     *              Use Default Java Gregorian calendar
     */
    void selectDate(String day, String month, String year);

    /**
     * User can change time for logging his Logging
     *
     * @param hour    used in {@link utils.RandomUtils#getTime(String)}
     * @param minutes used in {@link utils.RandomUtils#getTime(String)}
     */
    void selectTime(String hour, String minutes, TimeMidnight midnight);

    /**
     * @return boolean is Button "Log" displayed or not
     * By default button should be displayed after adding some data in fields
     * @see #setItemInField(String)
     */
    boolean isBtnLogDisplayed();

    /**
     * @return boolean is Title In Top toolbar present
     */
    boolean isTittleDisplayed();

    /**
     * @return boolean is Cancel button in top right in toolbar displayed
     */
    boolean isCancelBtnDisplayed();

    /**
     * @return boolean
     * Is text displayed on top of the date and time
     */
    boolean isTimeHeaderMessageDisplayed();

    /**
     * @return boolean is button with date displayed?
     * @see #selectDate(String, String, String) for clicking on it
     */
    boolean isBtnDateDisplayed();

    /**
     * @return Is button Time displayed
     * @see #selectTime(String, String, TimeMidnight) for clicking on it
     */
    boolean isBtnTimeDisplayed();

    /**
     * @return boolean, is field for logging displayed?
     * @see #getTextInLoggingField()
     */
    boolean isLoggingFieldDisplayed();

    /**
     * @return boolean is Unit type of data from field displayed
     */
    boolean isUnitTypeDisplayed();

    /**
     * Click on default btn "goToPreviousStep"
     * which should be displayed at the top fo the screen in toolbar
     */
    void pressBtnCancel();

    /**
     * Set text in field
     */
    void setItemInField(String data);

    /**
     * Should clear data in field before using
     */
    void clearField();

    /**
     * @param data take data which will be putted in text filed for logging it in future
     * @see #getTextInLoggingField()
     * */
    void sendItemInField(String data);

    /**
     * @return String with text in logging field
     * It's one field which available for filling for logging something
     */
    String getTextInLoggingField();
}
