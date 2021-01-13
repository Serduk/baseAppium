# base Appium framework

Location for all QA docs and scripts for the application(s)

# Prepare environment: 
1. install [Android studio with android SDK](https://developer.android.com/studio/) This tools help install android sdk end android emulators
2. Insall [intellij idea](https://www.jetbrains.com/idea/)
3. Install [Appium server](http://appium.io/) In feature we will use server from terminal
4. Clone repository
5. Install [ADB](https://www.xda-developers.com/install-adb-windows-macos-linux/) (if needed)

# Launch tests:
- Launch android emulator
- Enable [developer mode on device](https://developer.android.com/studio/debug/dev-options)
- in terminal on mac/pc/linux type "adb devices" (e.g. you will see: emulator-5554	device)
 - Copy first part of name (it will be Device_Name)
 - Check android version of emulator (in settings)
- in IDE, open class with test (e.g. HelloTest.class/ login/logout test)
- in "Before Test" put additional data to hashmap:

```java
        params.put(AndroidWorker.MyCapabilities.DEVICE_NAME, "YOUR EMULATOR NAME");
        params.put(AndroidWorker.MyCapabilities.PLATFORM_VERSION, "YOUR ANDROID VERSION e.g. 9");
        params.put(AndroidWorker.MyCapabilities.APP, "YOUR PATH TO APP e.g. /Users/sserdiuk/Downloads/myApp.apk");
```

- Tap play in test


# About: 
1. Most used classes:

- [AndroidWorker](https://github.com/Serduk/baseAppium/blob/master/src/main/java/driver/AndroidWorker.java) => Main class which init driver for running test on any env like emulator or real device. Can be update from any test

- [BasePage](https://github.com/Serduk/baseAppium/blob/master/src/main/java/pages/BasePage.java) => Base page, from which we should exted all new application pages


# Appium preferences JSon:
```java
{
  "deviceName": "emulator-5554",
  "platformName": "Android",
  "platformVersion": "9",
  "appActivity": ".MainActivity",
  "appPackage": "ai.APPLICATION_NAME.challenge",
  "automationName": "UiAutomator2",
  "app": "PATH_TO_APK/app-release.apk"
}
```


## One more:
```java
{
  "deviceName": "emulator-5554",
  "platformName": "Android",
  "platformVersion": "9",
  "appActivity": ".login.LoginActivity",
  "appPackage": "com.APPLICATION_NAME.challenge",
  "automationName": "UiAutomator2",
  "app": "PATH_TO_APK/app-release.apk"
}
 
 
{
  "deviceName": "avd",
  "avd": "Nexus_S_API_28",
  "platformName": "Android",
  "automationName": "UiAutomator2",
  "app": "PATH_TO_APK/app-release.apk"
}
 
{
  "deviceName": "iPhone 11",
  "platformName": "iOS",
  "platformVersion": "13.3",
  "app": "PATH_TO_APK/app-release.apk",
  "automationName": "XCUITest",
  "useNewWDA": "true"
}
```
