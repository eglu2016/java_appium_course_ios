package ui.factories;

import io.appium.java_client.AppiumDriver;
import platform.Platform;
import ui.NavigationUI;
import ui.android.AndroidNavigationUi;
import ui.ios.IosNavigationUi;

public class NavigationUiFactory {

    public static NavigationUI get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidNavigationUi(driver);
        } else {
            return new IosNavigationUi(driver);
        }
    }
}
