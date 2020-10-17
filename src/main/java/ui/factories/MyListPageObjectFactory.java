package ui.factories;

import io.appium.java_client.AppiumDriver;
import platform.Platform;
import ui.MyListPageObject;
import ui.android.AndroidMyListPageObject;
import ui.ios.IosMyListPageObject;

public class MyListPageObjectFactory {
    public static MyListPageObject get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidMyListPageObject(driver);
        }
        else {
            return new IosMyListPageObject(driver);
        }
    }
}
