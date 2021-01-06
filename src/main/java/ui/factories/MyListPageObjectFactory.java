package ui.factories;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import platform.Platform;
import ui.MyListPageObject;
import ui.android.AndroidMyListPageObject;
import ui.ios.IosMyListPageObject;

public class MyListPageObjectFactory {
    public static MyListPageObject get(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidMyListPageObject(driver);
        }
        else {
            return new IosMyListPageObject(driver);
        }
    }
}
