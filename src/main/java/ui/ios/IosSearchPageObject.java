package ui.ios;

import io.appium.java_client.AppiumDriver;
import ui.SearchPageObject;

public class IosSearchPageObject extends SearchPageObject {

    static {
        // 'Search Wikipedia' input
        SEARCH_INIT_ELEMENT = "xpath://*[@name='Search Wikipedia']";
        // Search Wikipedia' input for type text
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@label='Search Wikipedia']";
        // Close button
        SEARCH_CANCEL_BUTTON = "xpath://XCUIElementTypeButton[@name='Close']";
        // Search Result for find article by title and description
        SEARCH_RESULT_BY_SUBSTRING_TPL =
                "xpath://XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
        // for All elements search
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeLink";
        // 'No results found' text
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";

        SEARCH_INIT_TEXT = "xpath://*[contains(@resource-id,'search_container')]" +
                "/*[@class='android.widget.TextView']";
        LIST_ITEM_CONTAINER = "xpath://*[contains(@resource-id,'page_list_item_container')]";
        LIST_ITEM_TITLE = "xpath://*[contains(@resource-id, 'page_list_item_title')]";
        LIST_ITEM_DESCRIPTION = "xpath://*[contains(@resource-id, 'page_list_item_description')]";
        LIST_RESULTS_BY_TILE_AND_DESCRIPTION_TPL =
                "xpath://*[contains(@resource-id,'title') and contains(@text,'{SUBTITLE}')]" +
                        "/..//*[contains(@resource-id,'description') and contains(@text,'{SUBDESCRIPTION}')]";
    }

    public IosSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}