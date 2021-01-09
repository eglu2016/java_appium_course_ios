package ui.android;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "xpath://*[contains(@resource-id,'view_page_title_text')]";
        TITLE_ARTICLE_TPL = "xpath://*[@text='{ARTICLE_NAME}']";
        FOOTER_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/page_external_link']";
        OPTION_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']";
        OPTION_ADD_TO_MY_LIST_BUTTON = "xpath://*[@text='Add to reading list']";
        // GOT_IT button
        ADD_TO_MY_LIST_OVERLAY = "xpath://*[contains(@resource-id, 'onboarding_button')]";
        MY_LIST_NAME_INPUT = "xpath://*[@resource-id='org.wikipedia:id/text_input']";
        MY_LIST_OK_BUTTON = "xpath://*[@text='OK']";
        CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";
    }

    public AndroidArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}