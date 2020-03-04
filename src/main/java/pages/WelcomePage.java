package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * Welcome screen
 * Displayed after first login and while some devices not be connected
 * on this screen, after login should be displayed alert dialog
 * */
public class WelcomePage extends BasePage {
    public WelcomePage(AndroidDriver driver) {
        super(driver);
    }

    /* first screen */
    @AndroidFindBy(accessibility = "img_ob_board")
    private MobileElement firstScreenHead;

    @AndroidFindBy(accessibility = "tv_title")
    private MobileElement firstScreenTitleText;

    @AndroidFindBy(accessibility = "tv_welcome")
    private MobileElement firstScreenBody;

    public void skipTutorial() {
        swipe(SwipeDirection.RIGHT);
        swipe(SwipeDirection.RIGHT);
        swipe(SwipeDirection.RIGHT);
        swipe(SwipeDirection.RIGHT);
        swipe(SwipeDirection.RIGHT);
        swipe(SwipeDirection.RIGHT);
        swipe(SwipeDirection.RIGHT);
//        calculate button "start" in percent, because its not UI element in app
        clickByCoordinatesInPercent(0.85, 0.1);
    }
}
