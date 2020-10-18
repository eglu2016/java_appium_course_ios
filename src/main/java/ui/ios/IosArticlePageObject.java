package ui.ios;

import io.appium.java_client.AppiumDriver;
import ui.ArticlePageObject;

public class IosArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "id:Java (programming language)";
        TITLE_ARTICLE_TPL = "id:{ARTICLE_NAME}";
        FOOTER_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='View article in browser']";
        // toolbar - Save for later button
        OPTION_ADD_TO_MY_LIST_BUTTON = "xpath://XCUIElementTypeButton[@name='Save for later']";
        // < 'Back' button
        CLOSE_ARTICLE_BUTTON = "id:Back";
        LOG_IN_TO_SYNC_YOUR_SAVED_ARTICLES_BUTTON = "id:Log in to sync your saved articles";
        // X button
        CLOSE_BUTTON = "id:close";
    }

    public IosArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}