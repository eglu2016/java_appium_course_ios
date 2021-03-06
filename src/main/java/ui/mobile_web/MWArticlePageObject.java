package ui.mobile_web;

import org.openqa.selenium.remote.RemoteWebDriver;
import ui.ArticlePageObject;

public class MWArticlePageObject extends ArticlePageObject {

    static {
        // заголовок открыв. статьи
        TITLE = "xpath://h1[@id='section_0']";
        // footer
        FOOTER_ELEMENT = "xpath://footer[contains(@class, 'footer')]";
        // кнопка ввиде звездочки (для сохранения статьи)
        OPTION_ADD_TO_MY_LIST_BUTTON =
                "xpath://li[contains(@id, 'watch')]/*[contains(@class, 'watch') and contains(@class, 'star')]";
        // кнопка ввиде звездочки (для удаления статьи)
        OPTION_REMOVE_TO_MY_LIST_BUTTON = "xpath://*[contains(@class, 'watched') and contains(@class, 'unStar')]";
        //
        LOG_IN_TO_SYNC_YOUR_SAVED_ARTICLES_BUTTON = "id:Log in to sync your saved articles";
        // X button
        CLOSE_BUTTON = "id:close";
        // ------------------------------------
        TITLE_ARTICLE_TPL = "xpath://h1[text()='{ARTICLE_NAME}']";
    }
    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}