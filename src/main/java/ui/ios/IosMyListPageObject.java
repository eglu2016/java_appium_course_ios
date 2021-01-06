package ui.ios;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import ui.MyListPageObject;

public class IosMyListPageObject extends MyListPageObject {
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeLink[contains(@name, '{TITLE}')]";
        ALL_ARTICLES_LIST = "xpath://XCUIElementTypeCell/XCUIElementTypeLink";
    }

    public IosMyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}