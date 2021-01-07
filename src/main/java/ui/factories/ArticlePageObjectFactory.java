package ui.factories;

import org.openqa.selenium.remote.RemoteWebDriver;
import platform.Platform;
import ui.ArticlePageObject;
import ui.android.AndroidArticlePageObject;
import ui.ios.IosArticlePageObject;
import ui.mobile_web.MWArticlePageObject;

public class ArticlePageObjectFactory {
    public static ArticlePageObject get(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidArticlePageObject(driver);
        } else if (Platform.getInstance().isIos()) {
            return new IosArticlePageObject(driver);
        } else {
            return new MWArticlePageObject(driver);
        }
    }
}