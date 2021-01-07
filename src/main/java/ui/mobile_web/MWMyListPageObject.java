package ui.mobile_web;

import org.openqa.selenium.remote.RemoteWebDriver;
import ui.MyListPageObject;

public class MWMyListPageObject extends MyListPageObject {
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://a[@class='title']/h3[contains(text(),'{TITLE}')]";
        REMOVE_FROM_SAVED_BUTTON =
                "xpath://a[@class='title']/h3[contains(text(),'{TITLE}')]/../../a[contains(@class, 'unStar')]";
        // ----------------------
        ALL_ARTICLES_LIST = "xpath://XCUIElementTypeCell/XCUIElementTypeLink";
    }

    public MWMyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}