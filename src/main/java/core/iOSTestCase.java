package core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.time.Duration;

public class iOSTestCase extends TestCase {
    protected AppiumDriver driver;
    private static String AppiumUrl = "http://127.0.0.1:4723/wd/hub";

    // for run Mac
    private static String PathApk = "/Users/evgeniy_g/coures/project/java_appium_course_ios/apks/Wikipedia.app";

    @Override
    protected void setUp() throws Exception {
        super.setUp();  // используем метод из TestCase
        DesiredCapabilities сapabilities = new DesiredCapabilities();
        сapabilities.setCapability("platformName", "iOS");
        сapabilities.setCapability("deviceName", "iPhone SE");
        сapabilities.setCapability("platformVersion", "11.3");
        сapabilities.setCapability("app", PathApk);
        driver = new IOSDriver(new URL(AppiumUrl), сapabilities);
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
}