package ui.factories;

import io.appium.java_client.AppiumDriver;
import platform.Platform;
import ui.SearchPageObject;
import ui.android.AndroidSearchPageObject;
import ui.ios.IosSearchPageObject;

public class SearchPageObjectFactory {

    public static SearchPageObject get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidSearchPageObject(driver);
        }
        else {
            return new IosSearchPageObject(driver);
        }
    }
}
