package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends BasePage {
    public LoginPage(AndroidDriver driver) {
        super(driver);
        loadPage(getClass().getName());
    }

    @AndroidFindBy(xpath = "asdfasdfasf")
    MobileElement loginInput;

    @AndroidFindBy(xpath = "asdsaa")
    MobileElement passwordInput;

    @AndroidFindBy(xpath = "asdas")
    MobileElement btnLogin;

    public void signIn(String login, String password) {
        fillLoginEt(login);
        fillPasswordEt(password);
        driver.hideKeyboard();
        btnLogin.click();
    }

    public void fillLoginEt(String login) {
        waitMyElement(loginInput);
        loginInput.clear();
        loginInput.sendKeys(login);
        driver.navigate().back();
    }

    public void fillPasswordEt(String pass) {
        waitMyElement(passwordInput);
        passwordInput.clear();
        passwordInput.sendKeys(pass);
//        driver.navigate().back();
    }


}
