package tests;

import driver.AndroidWorker;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.MainBottomToolbarPage;
import pages.SettingsPage;

import java.util.HashMap;

/**
 * Initial hello world test
 * */
public class HelloTest {
    private AndroidDriver driver;

    @Before
    public void setup() {
        HashMap<String, String> params = new HashMap<>();
        driver = new AndroidWorker().getDriver(params);
    }

    @Test
    public void test() {
        String email = "test@test.test";
        String password = "123X123x";

        LoginPage loginPage = new LoginPage(driver);
        loginPage.signIn(email, password);

        MainBottomToolbarPage bottomToolbarPage = new MainBottomToolbarPage(driver);
        bottomToolbarPage.goToSettings();

        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.logout();
    }

    @After
    public void exit() {
        driver.closeApp();
    }
}
