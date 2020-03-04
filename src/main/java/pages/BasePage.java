package pages;

import com.google.common.base.Stopwatch;
import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by S Serdiuk
 * Additional documentation:
 * https://github.com/appium/java-client/blob/master/docs/Page-objects.md
 */
public class BasePage {
    public AndroidDriver driver;
    private WebDriverWait wait;
    private static String EXECUTION_MODE = System.getProperty("EXECUTION_MODE");
    private static int GLOBAL_WAIT_MODIFIER = 15;
    private Stopwatch stopwatch = Stopwatch.createUnstarted();
    private static final long ONE_SECOND = 1000;
    private Dimension size;
    private int duration = 1000;

    public BasePage(AndroidDriver driver) {
        if (EXECUTION_MODE == null || EXECUTION_MODE.isEmpty()) {
            EXECUTION_MODE = "LOCAL";
        }

        if (!EXECUTION_MODE.contains("LOCAL")) {
            GLOBAL_WAIT_MODIFIER = 40;
            System.out.println(String.format("Running from Jenkins." +
                    " Be aware that all waiting times are increased by: %s times.", GLOBAL_WAIT_MODIFIER));
        }

        this.driver = driver;
        size = driver.manage().window().getSize();
        wait = new WebDriverWait(driver, GLOBAL_WAIT_MODIFIER);
        sleep(3);
    }

    public void loadPage(String pageName) {
        try {
            Thread.sleep((ONE_SECOND * GLOBAL_WAIT_MODIFIER));
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        System.out.println(String.format("Loading: %s", pageName));
        stopwatch.start();
        PageFactory.initElements(new AppiumFieldDecorator(driver, GLOBAL_WAIT_MODIFIER, TimeUnit.SECONDS), this);
        stopwatch.stop();
        System.out.println(String.format("Time elapsed for loading %s is %s ms", pageName, stopwatch.elapsed(TimeUnit.MILLISECONDS)));
        stopwatch.reset();
    }

    /**
     * Hard wait in seconds
     *
     * @param sec take int in seconds
     */
    public void sleep(int sec) {
//        Not usable for react native
//        driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    protected WebElement getElementByXPath(String path) {
        try {
            return driver.findElement(By.xpath(path));
        } catch (NoSuchElementException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    protected WebElement getElementById(String path) {
        try {
            return driver.findElement(By.id(path));
        } catch (NoSuchElementException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    protected List<WebElement> getElementsByXPath(String path) {
        try {
            return driver.findElements(By.xpath(path));
        } catch (NoSuchElementException ex) {
            System.out.println("ELEMENTS NOT FOUND");
            return null;
        }
    }

    public void click(MobileElement element) {
        waitMyElement(element);
        element.click();
    }

    public void click(WebElement element) {
        waitMyElement(element);
        element.click();
        sleep(1);
    }

    protected void safeClick(MobileElement element) {
        try {
            click(element);
        } catch (NullPointerException ex) {
            try {
                element.click();
            } catch (Exception ex1) {
                ex1.printStackTrace();
                System.out.println("Can't perform any type of click");
            }
        }
    }

    protected void safeClick(WebElement element) {
        try {
            driver.hideKeyboard();
            click(element);
        } catch (NullPointerException ex) {
            try {
                element.click();
            } catch (Exception ex1) {
                ex1.printStackTrace();
                System.out.println("Can't perform any type of click");
            }
        } finally {
            driver.hideKeyboard();
        }
    }

    protected void clickByCoordinates(int x, int y) {
        System.out.println("Coordinates is: " + x + " " + y);

        TouchAction touchAction = new TouchAction(driver);
        touchAction.tap(x, y).perform();
    }

    protected void clickByCoordinatesInPercent(double x, double y) {
        int height = (int) (size.getHeight() * y);
        int width = (int) (size.getWidth() * x);

        clickByCoordinates(width, height);
    }

    protected void waitMyElement(MobileElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception ex) {
            sleep(2);
        }
    }

    protected boolean isDisplayed(WebElement element) {
        driver.hideKeyboard();
        try {
            return element.isDisplayed();
        } catch (Throwable ex) {
            System.out.println("Element not found. Catch exception");
            return false;
        }
    }

    protected boolean isDisplayed(MobileElement element) {
        driver.hideKeyboard();
        try {
            return element.isDisplayed();
        } catch (NotFoundException ex) {
            return false;
        }
    }


    protected void waitMyElement(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception ex) {
            sleep(2);
        }
    }

    protected void waitClickableElement(MobileElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception ex) {
            System.out.println("waitClickableElement Exception found");
        }
    }

    protected void setKeys(MobileElement element, String word) {
        driver.hideKeyboard();
        waitMyElement(element);
        element.clear();
        element.sendKeys(word);
        driver.hideKeyboard();
    }

    protected void setKeys(WebElement element, String word) {
        driver.hideKeyboard();
        waitMyElement(element);
        element.clear();
        element.sendKeys(word);
        driver.hideKeyboard();
    }

    protected void sendKeys(MobileElement element, String word) {
        driver.hideKeyboard();
        waitMyElement(element);
        element.sendKeys(word);
        driver.hideKeyboard();
    }

    protected void sendKeys(WebElement element, String word) {
        driver.hideKeyboard();
        waitMyElement(element);
        element.sendKeys(word);
        driver.hideKeyboard();
    }

    protected void clearField(WebElement element) {
        driver.hideKeyboard();
        element.clear();
        driver.hideKeyboard();
    }

    protected void clearField(MobileElement element) {
        driver.hideKeyboard();
        element.clear();
        driver.hideKeyboard();
    }

    protected String getText(WebElement element) {
        String txt = "";

        waitMyElement(element);
        if (element != null && isDisplayed(element)) {
            txt = element.getText();
        }
        return txt;
    }

    protected String getState(WebElement element) {
        String state = null;
        waitMyElement(element);

        if (element != null && isDisplayed(element)) {
            state = String.valueOf(element.getAttribute("contentDescription").endsWith("true"));
        }
        return state;
    }

    /**
     * code example
     * https://www.swtestacademy.com/appium-mobile-actions-swipe-tap-press/
     */
    protected void swipeElement(MobileElement element, SwipeDirection direction) {
        Dimension elementSize = element.getSize();

        int startY;
        int startX;
        int endX = 0;
        int endY;

        switch (direction) {
            case RIGHT:
                startY = elementSize.height / 2;
                startX = (int) (elementSize.width * 0.90);
                endX = (int) (size.width * 0.01);
                new TouchAction(driver)
                        .press(startX, startY)
                        .waitAction(duration)
                        .moveTo(endX, startY)
                        .release()
                        .perform();
                break;
            case LEFT:
                startY = elementSize.height / 2;
                startX = (int) (elementSize.width * 0.05);
                endX = (int) (size.width * 0.99);
                new TouchAction(driver)
                        .press(startX, startY)
                        .waitAction(duration)
                        .moveTo(endX, startY)
                        .release()
                        .perform();
                break;
            case UP:
                startX = (elementSize.width / 2);
                startY = (int) (elementSize.height * 0.30);
                endY = (int) (size.height * 0.99);
                new TouchAction(driver)
                        .press(startX, startY)
                        .waitAction(duration)
                        .moveTo(endX, endY)
                        .release()
                        .perform();
                break;
            case DOWN:
                startX = (elementSize.width / 2);
                startY = (int) (elementSize.height * 0.70);
                endY = (int) (size.height * 0.01);
                new TouchAction(driver)
                        .press(startX, startY)
                        .waitAction(duration)
                        .moveTo(startX, endY)
                        .release()
                        .perform();
                break;
            default:
                break;
        }
    }

    /**
     * @param direction used for setting way for swiping
     * @see SwipeDirection to see ways for swiping
     */
    protected void swipe(SwipeDirection direction) {
        int startX;
        int endX = 0;
        int startY;
        int endY;

        switch (direction) {
            case RIGHT:
                startY = size.height / 2;
                startX = (int) (size.width * 0.90);
                endX = (int) (size.width * 0.05);
                new TouchAction(driver)
                        .press(startX, startY)
                        .waitAction(duration)
                        .moveTo(endX, startY)
                        .release()
                        .perform();
                break;

            case LEFT:
                startY = size.height / 2;
                startX = (int) (size.width * 0.05);
                endX = (int) (size.width * 0.90);
                new TouchAction(driver)
                        .press(startX, startY)
                        .waitAction(duration)
                        .moveTo(endX, startY)
                        .release()
                        .perform();
                break;

            case UP:
                endY = (int) (size.height * 0.70);
                startY = (int) (size.height * 0.30);
                startX = (size.width / 2);
                new TouchAction(driver)
                        .press(startX, startY)
                        .waitAction(duration)
                        .moveTo(endX, endY)
                        .release()
                        .perform();
                break;

            case DOWN:
                startY = (int) (size.height * 0.70);
                endY = (int) (size.height * 0.30);
                startX = (size.width / 2);
                new TouchAction(driver)
                        .press(startX, startY)
                        .waitAction(duration)
                        .moveTo(startX, endY)
                        .release()
                        .perform();
                break;
        }
    }

    /**
     * Swipe by elements
     */
    public void swipeByElements(MobileElement startElement, MobileElement endElement) {
        int startX = startElement.getLocation().getX() + (startElement.getSize().getWidth() / 2);
        int startY = startElement.getLocation().getY() + (startElement.getSize().getHeight() / 2);

        int endX = endElement.getLocation().getX() + (endElement.getSize().getWidth() / 2);
        int endY = endElement.getLocation().getY() + (endElement.getSize().getHeight() / 2);

        new TouchAction(driver)
                .press(startX, startY)
                .waitAction(duration)
                .moveTo(endX, endY)
                .release().perform();
    }

    /**
     * Multitouch action by using an android element
     */
    public void multiTouchByElement(MobileElement androidElement) {
        TouchAction press = new TouchAction(driver)
                .press(androidElement)
                .waitAction(duration)
                .release();

        new MultiTouchAction(driver)
                .add(press)
                .perform();
    }

    /**
     * Used for setting directions
     *
     * @see #swipe(SwipeDirection)
     */
    public enum SwipeDirection {
        DOWN, UP, LEFT, RIGHT
    }
}
