package ui.factories;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import platform.Platform;
import ui.ArticlePageObject;
import ui.android.AndroidArticlePageObject;
import ui.ios.IosArticlePageObject;

public class ArticlePageObjectFactory {

    public static ArticlePageObject get(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidArticlePageObject(driver);
        }
        else {
            return new IosArticlePageObject(driver);
        }
    }
}