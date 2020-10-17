package ui.ios;

import io.appium.java_client.AppiumDriver;
import ui.MyListPageObject;

public class IosMyListPageObject extends MyListPageObject {
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeLink[contains(@name, '{TITLE}')]";
    }

    public IosMyListPageObject(AppiumDriver driver) {
        super(driver);
    }
}