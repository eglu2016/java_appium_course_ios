package ui.mobile_web;

import org.openqa.selenium.remote.RemoteWebDriver;
import ui.SearchPageObject;

public class MWSearchPageObject extends SearchPageObject {
    static {
        // кнопка Поиск (лупа)
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INIT_TEXT = "xpath://form/*[@aria-label='Искать в Википедии']";
        // Search Wikipedia' input for type text
        SEARCH_INPUT = "css:form>input[type='search']";
        // Close button
        SEARCH_CANCEL_BUTTON = "css:div>button.cancel";
        // Search Result for find article by title and description
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://div[@class='wikidata-description' and contains(text(),'{SUBSTRING}')]";
        // for All elements search
        SEARCH_RESULT_ELEMENT = "css:ul.page-list>li.page-summary";
        // 'No results found' text
        SEARCH_EMPTY_RESULT_ELEMENT =
                "xpath://*[contains(text(), 'No page with this title') or contains(text(), 'Нет страниц с таким названием')]";
        // -------------------------------------------
        // for get title and description articles
        LIST_RESULTS_BY_TILE_DESCRIPTION = "xpath://ul[contains(@class,'page-list')]//li";
        LIST_ITEM_CONTAINER = "xpath://div[contains(@class, 'results-list-container')]";
        LIST_ITEM_CONTAINER = "xpath://div[contains(@class, 'results-list-container')]";
        LIST_ITEM_TITLE = "xpath://ul[contains(@class,'page-list')]//a";
        LIST_ITEM_DESCRIPTION = "xpath://ul[contains(@class,'page-list')]//div[@class='wikidata-description']";
        LIST_RESULTS_BY_TILE_AND_DESCRIPTION_TPL =
                "xpath://li/a[contains(@data-title, '{SUBTITLE}')]//div[contains(text(),'{SUBDESCRIPTION}')]";
    }
    public MWSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}