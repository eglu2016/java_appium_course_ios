package ui.ios;

import io.appium.java_client.AppiumDriver;
import ui.ArticlePageObject;

public class IosArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "id:Java (programming language)";
        FOOTER_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='View article in browser']";
        // toolbar - Save for later button
        OPTION_ADD_TO_MY_LIST_BUTTON = "xpath://XCUIElementTypeButton[@name='Save for later']";
        // Back button
        CLOSE_ARTICLE_BUTTON = "id:Back";
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";
    }

    public IosArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}