package ui.android;

import io.appium.java_client.AppiumDriver;
import ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@resource-id,'search_container')]";
        SEARCH_INPUT = "xpath://*[contains(@resource-id,'search_src_text')]";
        SEARCH_CANCEL_BUTTON = "xpath://*[contains(@resource-id,'search_close_btn')]";
        SEARCH_RESULT_BY_SUBSTRING_TPL =
                "xpath://*[contains(@resource-id,'page_list_item_container')]//*[contains(@text,'{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "xpath://*[contains(@resource-id,'search_results_list')]" +
                "/*[contains(@resource-id,'page_list_item_container')]";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']";
        SEARCH_INIT_TEXT = "xpath://*[contains(@resource-id,'search_container')]" +
                "/*[@class='android.widget.TextView']";
        LIST_ITEM_CONTAINER = "xpath://*[contains(@resource-id,'page_list_item_container')]";
        LIST_ITEM_TITLE = "xpath://*[contains(@resource-id, 'page_list_item_title')]";
        LIST_ITEM_DESCRIPTION = "xpath://*[contains(@resource-id, 'page_list_item_description')]";
        LIST_RESULTS_BY_TILE_AND_DESCRIPTION_TPL =
                "xpath://*[contains(@resource-id,'title') and contains(@text,'{SUBTITLE}')]" +
                        "/..//*[contains(@resource-id,'description') and contains(@text,'{SUBDESCRIPTION}')]";
    }

    public AndroidSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}
