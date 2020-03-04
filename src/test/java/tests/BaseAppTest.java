//package tests;

//import androidDriver.AndroidWorker;
//import constants.Constants;
//import io.appium.java_client.android.AndroidDriver;
//import org.testng.annotations.AfterTest;
//import org.testng.asserts.Assertion;
//import org.testng.asserts.SoftAssert;
//import pages.app.AppStartPage;
//import pages.app.LoginPage;
//import pages.app.MainBottomToolbarPage;
//import pages.app.SettingsPage;
//import pages.app.alerts.CameraAlertPage;
//import pages.app.alerts.LocationAlertPage;
//import pages.app.diaryPage.DiaryPage;
//import pages.app.forgotPassword.ForgotPasswordPage;
//import pages.app.logging.LoggingType;
//import pages.app.logging.food.AddFoodItemPage;
//import pages.app.logging.food.FoodSelectionPage;
//import pages.app.logging.food.MealLoggingPage;
//import pages.app.logging.food.Tab;
//
//public class BaseAppTest {
//    protected AndroidDriver driver = new AndroidWorker().getDriver();
//    protected Assertion hardAssert = new Assertion();
//    protected SoftAssert softAssert = new SoftAssert();
//
//    protected String email = Constants.USER_EMAIL;
//    protected String password = Constants.TEST_PASSWORD;
//
//    protected void goToLogin() {
//        MainBottomToolbarPage mainToolbar = new MainBottomToolbarPage(driver);
//        ForgotPasswordPage forgotPassword = new ForgotPasswordPage(driver);
//
//        try {
//            mainToolbar.goToSettings();
//            logout();
//        } catch (Exception ex) {
//            forgotPassword.goToPreviousStep();
//        }
//    }
//
//    protected void quickLogin(String login, String password) {
//        AppStartPage appStartPage = new AppStartPage(driver);
//        appStartPage.goToLogin();
//
//        login(login, password);
//    }
//
//    protected void simpleLoginFromStartPage(String login, String password) {
//        AppStartPage appStartPage = new AppStartPage(driver);
//        appStartPage.goToLogin();
//
//        login(login, password);
//    }
//
//    protected void login(String login, String password) {
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.signIn(login, password);
//    }
//
//    protected void goToSignUp() {
//        AppStartPage appStartPage = new AppStartPage(driver);
//        appStartPage.goToSignUp();
//    }
//
//    protected void goToDiaryAfterLogin(boolean grantAccess) {
//        LocationAlertPage alerts = new LocationAlertPage(driver);
//        if (alerts.isAlertDisplayed()) {
//            if (grantAccess) {
//                alerts.acceptLocationAccess();
//            } else {
//                alerts.denyLocationAccess();
//            }
//        }
//        skipTutorial();
//    }
//
//    //    see parent issue: MVP-669
//    protected void skipTutorial() {
//        System.out.println("Tutorial temporary skipped");
////        WelcomePage welcomePage = new WelcomePage(driver);
////        welcomePage.skipTutorial();
//    }
//
//    protected void goToDiary() {
//        MainBottomToolbarPage toolbarPage = new MainBottomToolbarPage(driver);
//        toolbarPage.goToDiary();
//    }
//
//    protected void expandLogging() {
//        DiaryPage diaryPage = new DiaryPage(driver);
//        diaryPage.expandLoggingTypes();
//    }
//
//    protected void goToLogging(LoggingType logging) {
//        DiaryPage diaryPage = new DiaryPage(driver);
//        diaryPage.goToLogging(logging);
//    }
//
//    protected void goToReports() {
//        MainBottomToolbarPage toolbarPage = new MainBottomToolbarPage(driver);
//        toolbarPage.goToReports();
//    }
//
//    protected void goToPoints() {
//        MainBottomToolbarPage toolbarPage = new MainBottomToolbarPage(driver);
//        toolbarPage.goToPoints();
//    }
//
//    protected void goToSettings() {
//        MainBottomToolbarPage toolbarPage = new MainBottomToolbarPage(driver);
//        toolbarPage.goToSettings();
//    }
//
//    protected void logout() {
//        MainBottomToolbarPage toolbarPage = new MainBottomToolbarPage(driver);
//        toolbarPage.goToSettings();
//
//        SettingsPage settingsPage = new SettingsPage(driver);
//        settingsPage.logout();
//    }
//
//    protected void wait(int sec) {
//        SettingsPage settingsPage = new SettingsPage(driver);
//        settingsPage.sleep(sec);
//    }
//
//    protected void goBack() {
//        driver.navigate().back();
//    }
//
//
//    /* default methods for simplifying FOOD LOGGING */
//
//    protected void goToAddItemToMealScreen(String text) {
//        searchFirstAvailableProduct(text);
//        FoodSelectionPage page = new FoodSelectionPage(driver);
//        page.selectItemFromList(Tab.GENERAL, 0);
//    }
//
//    protected void goToAddMealScreen(String text) {
//        goToAddItemToMealScreen(text);
//        AddFoodItemPage itemPage = new AddFoodItemPage(driver);
//        itemPage.addToMeal();
//    }
//
//    protected void searchFirstAvailableProduct(String text) {
//        FoodSelectionPage page = new FoodSelectionPage(driver);
//        page.goToTab(Tab.GENERAL);
//        page.setItemInField(text);
//    }
//
//    protected void logOneFood(String text) {
//        goToLogging(LoggingType.FOOD);
//        goToAddMealScreen(text);
//        MealLoggingPage page = new MealLoggingPage(driver);
//        page.save();
//    }
//
//    protected void allowCameraIfPresent() {
//        CameraAlertPage page = new CameraAlertPage(driver);
//        if (page.isAlertDisplayed()) {
//            page.acceptCameraAccess();
//        }
//    }
//
//    protected void allowLocationIfPresent() {
//        LocationAlertPage page = new LocationAlertPage(driver);
//        if (page.isAlertDisplayed()) {
//            page.acceptLocationAccess();
//        }
//    }
//
//    @AfterTest
//    public void tearDown() {
//        driver.closeApp();
//    }
//}
