package ui.mobile_web;

import org.openqa.selenium.remote.RemoteWebDriver;
import ui.MyListPageObject;

public class MWMyListPageObject extends MyListPageObject {
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://a[@class='title']/h3[contains(text(),'{TITLE}')]";
        REMOVE_FROM_SAVED_BUTTON =
                "xpath://a[@class='title']/h3[contains(text(),'{TITLE}')]/../../a[contains(@class, 'unStar')]";
        // Список наблюдения (кол-во статей в списке)
        ALL_ARTICLES_LIST = "xpath://li[contains(@class, 'page-summary')]";
    }

    public MWMyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}