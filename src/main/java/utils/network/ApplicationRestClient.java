package utils.network;

import constants.Constants;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import utils.RandomUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Coverage class for HttpClient-apache-http-client
 * Example of using class:
 * https://github.com/Serduk/badoo/blob/master/src/core/helpers/httpHelper/BadooHelper.java
 */
public class ApplicationRestClient {
    private HttpClient http = new HttpClient();

    HashMap<String, String> outputData = new HashMap<>();
    JSONObject json;
    String content;

    private String email, password, userName, phoneNumber, actionType;

    /**
     * Precondition
     * data need for using in next, futures steps
     * Pre-set-up data for executiong queries
     *
     * @see #userRegistration(HashMap) forExample
     */
    public ApplicationRestClient setData(HashMap<String, String> data) {
        actionType = data.get(LoginPref.ACTION_TYPE);

        if (data.get(LoginPref.EMAIL) != null) {
            email = data.get(LoginPref.EMAIL);
        } else {
            email = RandomUtils.getEmail(Constants.TEST_EMAIL);
        }

        if (data.get(LoginPref.PASSWORD) != null) {
            password = data.get(LoginPref.PASSWORD);
        } else {
            password = Constants.TEST_PASWORD;
        }

        if (data.get(LoginPref.USER_NAME) != null) {
            userName = data.get(LoginPref.USER_NAME);
        } else {
            userName = RandomUtils.getTestUserName();
        }

        if (data.get(LoginPref.PHONE_NUMBER) != null) {
            phoneNumber = data.get(LoginPref.PHONE_NUMBER);
        } else {
            phoneNumber = Constants.TEST_PHONE_NUMBER;
        }

        return this;
    }

    /**
     * Method for get/post user registration on sites
     * Require: email, sitelink
     */
    public JSONObject userRegistration(HashMap<String, String> data) {
        String url;

        if (data.get(LoginPref.REGISTRATION_ENV) != null) {
            if (data.get(LoginPref.REGISTRATION_ENV).contains(LoginPref.PROD_ENV)) {
                url = Constants.PROD_URL;
            } else {
                url = Constants.DEV_URL;
            }
        } else {
            url = Constants.DEV_URL;
        }

        List<NameValuePair> regForm = new ArrayList<>();
        regForm.add(new BasicNameValuePair(ApplicationParams.USERNAME, userName));
        regForm.add(new BasicNameValuePair(ApplicationParams.EMAIL, email));
        regForm.add(new BasicNameValuePair(ApplicationParams.PASSWORD, password));
        regForm.add(new BasicNameValuePair(ApplicationParams.SMS, phoneNumber));

        System.out.println("email: " + email);
        System.out.println("password " + password);
        System.out.println("phone number " + phoneNumber);
        System.out.println("username " + userName);

        System.out.println("url is: " + url + Constants.REGISTRATION_PATH);

        http.post(url + Constants.REGISTRATION_PATH, regForm);
        http.execute();

        return http.toJSON();
    }

    /**
     * Used for putting some additional data
     * for registration or for execute or navigate some additional actions
     */
    public interface LoginPref {
        String EMAIL = "email";
        String PASSWORD = "password";
        String USER_NAME = "userName";
        String PHONE_NUMBER = "phoneNumber";
        String ACTION_TYPE = "actionType";
        String ACTION_LOGIN = "actionLogin";
        String ACTION_REGISTRATION = "actionRegistration";
        String REGISTRATION_TYPE = "registrationType";
        String ACTION_REGISTER_RANDOM_USER = "RegisterRandomUser";
        String REGISTRATION_ENV = "registrationEnv";
        String TEST_ENV = "testEnv";
        String PROD_ENV = "prodEnv";
    }

    /**
     * Used only for default registration on server side
     *
     * @see #userRegistration(HashMap) for example
     * Used for put some data to body of post request
     */
    private interface ApplicationParams {
        String USERNAME = "username";
        String PASSWORD = "password";
        String EMAIL = "email";
        String SMS = "sms";
    }
}
