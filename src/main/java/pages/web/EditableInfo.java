package pages.web;

import utils.RandomUtils;

/**
 * Include Editable info for
 *
// * @see EditUserInfoPage
// * @see EditRegistrationInfoPage
 * should be used in tests and
 * @see RandomUtils#getEditableInfoForUser()
 */
public enum EditableInfo {
    FIRST_NAME, LAST_NAME, EMAIL, PHONE, SMS_NOTIFY,
    ADDRESS, CITY, STATE, ZIP_CODE, DOCTOR_NAME, DOCTOR_EMAIL, DOCTOR_PHONE, CHALLENGE_NAME
}
