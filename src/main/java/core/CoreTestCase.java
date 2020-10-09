package core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class CoreTestCase extends TestCase {

    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    protected AppiumDriver driver;

    @Override
    protected void setUp() throws Exception {
        super.setUp();  // используем метод из TestCase
        driver = this.getDriverPlatformEnv();
        this.rotateScreenPortrait();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        driver.quit();
    }

    protected void rotateScreenPortrait() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(int seconds) {
        driver.runAppInBackground(Duration.ofSeconds(seconds));
    }

    private AppiumDriver getDriverPlatformEnv() throws Exception {
        String AppiumUrl = "http://127.0.0.1:4723/wd/hub";
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities сapabilities = new DesiredCapabilities();
        AppiumDriver driver;

        if (platform.equals(PLATFORM_ANDROID)) {
            String PathApk = "/Users/evgeniy_g/coures/project/java_appium_course_ios/apks/org.wikipedia.apk";
            сapabilities.setCapability("platformName", "Android");
            сapabilities.setCapability("deviceName", "AndroidTestDevice");
            сapabilities.setCapability("platformVersion", "8.0");
            сapabilities.setCapability("automationName", "Appium");
            сapabilities.setCapability("appPackage", "org.wikipedia");
            сapabilities.setCapability("appActivity", ".main.MainActivity");
            сapabilities.setCapability("app", PathApk);
            driver = new AndroidDriver(new URL(AppiumUrl), сapabilities);
        }
        else if (platform.equals(PLATFORM_IOS)) {
            String PathApk = "/Users/evgeniy_g/coures/project/java_appium_course_ios/apks/Wikipedia.app";
            сapabilities.setCapability("platformName", "iOS");
            сapabilities.setCapability("deviceName", "iPhone SE");
            сapabilities.setCapability("platformVersion", "11.3");
            сapabilities.setCapability("app", PathApk);
            driver = new IOSDriver(new URL(AppiumUrl), сapabilities);
        }
        else {
            throw new Exception("Cannot get run driver from env variable " + platform);
        }
        return driver;
    }
}