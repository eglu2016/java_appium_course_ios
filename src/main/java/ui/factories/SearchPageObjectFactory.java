package ui.factories;

import org.openqa.selenium.remote.RemoteWebDriver;
import platform.Platform;
import ui.SearchPageObject;
import ui.android.AndroidSearchPageObject;
import ui.ios.IosSearchPageObject;
import ui.mobile_web.MWSearchPageObject;

public class SearchPageObjectFactory {
    public static SearchPageObject get(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidSearchPageObject(driver);
        } else if (Platform.getInstance().isIos()) {
            return new IosSearchPageObject(driver);
        } else {
           return new MWSearchPageObject(driver);
        }
    }
}
