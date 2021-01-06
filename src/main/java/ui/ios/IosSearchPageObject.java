package ui.ios;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import ui.SearchPageObject;

public class IosSearchPageObject extends SearchPageObject {

    static {
        // 'Search Wikipedia' input
        SEARCH_INIT_ELEMENT = "xpath://*[@name='Search Wikipedia']";
        SEARCH_INIT_TEXT = "xpath://*[@name='Search Wikipedia']";
        // Search Wikipedia' input for type text
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@label='Search Wikipedia']";
        // Close button
        SEARCH_CANCEL_BUTTON = "xpath://*[@name='Close']";
        // Search Result for find article by title and description
        SEARCH_RESULT_BY_SUBSTRING_TPL =
                "xpath://XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
        // for All elements search
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeLink";
        // 'No results found' text
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";
        // for get title and description articles
        LIST_RESULTS_BY_TILE_DESCRIPTION = "xpath://XCUIElementTypeCollectionView//XCUIElementTypeLink";

        LIST_ITEM_CONTAINER = "xpath://*[contains(@resource-id,'page_list_item_container')]";

        LIST_ITEM_TITLE = "xpath://*[contains(@resource-id, 'page_list_item_title')]";
        LIST_ITEM_DESCRIPTION = "xpath://*[contains(@resource-id, 'page_list_item_description')]";

    }

    public IosSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
