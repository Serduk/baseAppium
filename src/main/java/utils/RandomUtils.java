package utils;

import constants.Constants;
import org.apache.commons.lang3.text.WordUtils;
import pages.AddNewUserPage;
import pages.web.EditableInfo;
import utils.network.ApplicationRestClient;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Mostly used in backend reg and data tests
 *
 * @see ApplicationRestClient for example
 */
public class RandomUtils {
    public static String getEmail(String email) {
        String[] parsedMail = email.split("@");

        return parsedMail[0] + Calendar.getInstance().getTimeInMillis() + "@" + parsedMail[1];
    }

    public static String getTestUserName() {
        return Constants.TEST_USERNAME + "_" + getName();
    }

    //TODO create method to generate valid password
    public static String getPassword() {
        return Constants.TEST_PASSWORD;
    }

    public static String getRegistrationUserName() {
        return Constants.TEST_USERNAME.replace("_", ".") + "." + getName();
    }

    public static String getName() {
        String alphabet = "abcdefghijklmnopqrstyvwxyz";

        StringBuilder result = new StringBuilder();

        for(int i = 0; i < 7; i++){
            result.append(alphabet.charAt(randomNumber(0, 25)));
        }

        return WordUtils.capitalize(result.toString());
    }

    public static HashMap<AddNewUserPage.FormParam, Object> getGeneratedDataForRegUser() {
        HashMap<AddNewUserPage.FormParam, Object> data = new HashMap<>();

        data.put(AddNewUserPage.FormParam.USERNAME, RandomUtils.getRegistrationUserName());
        data.put(AddNewUserPage.FormParam.FIRST_NAME, RandomUtils.getName());
        data.put(AddNewUserPage.FormParam.LAST_NAME, RandomUtils.getName());
        data.put(AddNewUserPage.FormParam.EMAIL, RandomUtils.getEmail(Constants.TEST_EMAIL));
        data.put(AddNewUserPage.FormParam.PHONE, RandomUtils.getPhoneNumber());
        data.put(AddNewUserPage.FormParam.PASS, Constants.TEST_PASSWORD);

        return data;
    }

    public static HashMap<EditableInfo, Object> getEditableInfoForUser() {
        HashMap<EditableInfo, Object> data = new HashMap<>();
        data.put(EditableInfo.FIRST_NAME, RandomUtils.getName());
        data.put(EditableInfo.LAST_NAME, RandomUtils.getName());
        data.put(EditableInfo.EMAIL, RandomUtils.getEmail(Constants.TEST_EMAIL));
        data.put(EditableInfo.PHONE, RandomUtils.getPhoneNumber());
        data.put(EditableInfo.SMS_NOTIFY, RandomUtils.getName());

        data.put(EditableInfo.ADDRESS, RandomUtils.getName());
        data.put(EditableInfo.CITY, RandomUtils.getName());
        data.put(EditableInfo.STATE, RandomUtils.getName());
        data.put(EditableInfo.ZIP_CODE, RandomUtils.getNumberRow(6));
        data.put(EditableInfo.DOCTOR_NAME, RandomUtils.getName());
        data.put(EditableInfo.DOCTOR_EMAIL, "doc_" + RandomUtils.getEmail(Constants.TEST_EMAIL));
        data.put(EditableInfo.DOCTOR_PHONE, RandomUtils.getPhoneNumber());

        return data;
    }

    public static String getPhoneNumber() {
        return "+" + getCurrentTimeInMills();
    }

    public static String getNumberRow(int rowSize) {
        String nums = "0123456789";

        StringBuilder result = new StringBuilder();
        for(int i = 0; i < rowSize; i++){
            result.append(nums.charAt(randomNumber(0, nums.length() - 1)));
        }

        return WordUtils.capitalize(result.toString());
    }

    private static int randomNumber(int from, int to) {
        return new Random().nextInt((to - from) + 1) + from;
    }

    public static String getTime(String separator) {
        return  new SimpleDateFormat("HH" + separator + "mm" + separator+"ss")
                .format(Calendar.getInstance().getTime());
    }

    public static String getDate(String separator) {
        return new SimpleDateFormat("dd" + separator + "MM" + separator + "yyyy")
                .format(new Date());
    }

    public static String getCurrentDateAndTime(String separator) {
        return new SimpleDateFormat("HH" + separator + "mm" + separator + "ss"
                + separator + "dd" + separator + "MM" + separator + "yyyy").format(new Date());
    }

    /**
     * Return month from current
     *
     * @param param take int from to from current month
     */
    public static String getMonthFromCurrent(int param) {
        Calendar c = new GregorianCalendar();
        c.setTime(new Date());
        c.add(Calendar.MONTH, param);

        return new SimpleDateFormat("MMM").format(c.getTime());
    }

    /**
     * Solution with example:
     * https://stackoverflow.com/questions/12814504/how-to-get-previous-month-and-years-in-java/12814643
     */
    public static String getYearFromCurrent(int param) {
        return "" + (Calendar.getInstance().get(Calendar.YEAR) + param);
    }

    public static String getDayFromCurrent(int param) {
        Calendar c = new GregorianCalendar();
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_MONTH, param);

        return new SimpleDateFormat("dd").format(c.getTime());
    }

    public static int genInt(int from, int to) {
        int tmp = 0;
        if (to >= from)
            tmp = (int) (from + Math.round((Math.random() * (to - from))));
        return tmp;
    }

    public static long getCurrentTimeInMills() {
        return Calendar.getInstance().getTimeInMillis();
    }
}
