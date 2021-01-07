package ui.factories;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import platform.Platform;
import ui.NavigationUI;
import ui.android.AndroidNavigationUi;
import ui.ios.IosNavigationUi;
import ui.mobile_web.MWNavigationUi;

public class NavigationUiFactory {
    public static NavigationUI get(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidNavigationUi(driver);
        } else if (Platform.getInstance().isIos()) {
            return new IosNavigationUi(driver);
        } else {
            return new MWNavigationUi(driver);
        }
    }
}