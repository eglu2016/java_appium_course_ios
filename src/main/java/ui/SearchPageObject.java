package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import static junit.framework.TestCase.assertTrue;

public class SearchPageObject extends MainPageObject {
    /**
     * константы
     */
    private static final String
            SEARCH_INIT_ELEMENT = "xpath://*[contains(@resource-id,'search_container')]",
            SEARCH_INPUT = "xpath://*[contains(@resource-id,'search_src_text')]",
            SEARCH_CANCEL_BUTTON = "xpath://*[contains(@resource-id,'search_close_btn')]",
            SEARCH_RESULT_BY_SUBSTRING_TPL =
                    "xpath://*[contains(@resource-id,'page_list_item_container')]//*[contains(@text,'{SUBSTRING}')]",
            SEARCH_RESULT_ELEMENT = "xpath://*[contains(@resource-id,'search_results_list')]" +
                    "/*[contains(@resource-id,'page_list_item_container')]",
            SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']",
            SEARCH_INIT_TEXT = "xpath://*[contains(@resource-id,'search_container')]" +
                    "/*[@class='android.widget.TextView']",
            LIST_ITEM_CONTAINER = "xpath://*[contains(@resource-id,'page_list_item_container')]",
            LIST_ITEM_TITLE = "xpath://*[contains(@resource-id, 'page_list_item_title')]",
            LIST_ITEM_DESCRIPTION = "xpath://*[contains(@resource-id, 'page_list_item_description')]",
            LIST_RESULTS_BY_TILE_AND_DESCRIPTION_TPL =
                    "xpath://*[contains(@resource-id,'title') and contains(@text,'{SUBTITLE}')]" +
                            "/..//*[contains(@resource-id,'description') and contains(@text,'{SUBDESCRIPTION}')]";



    public SearchPageObject(AppiumDriver driver) {
        // берем драйвер из MainPageObject
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    private static String getResultSearchElementByTitleAndDescription(String subtitle, String subdecription) {
        String return_xpath = LIST_RESULTS_BY_TILE_AND_DESCRIPTION_TPL.replace("{SUBTITLE}", subtitle);
        return return_xpath.replace("{SUBDESCRIPTION}", subdecription);
    }
    /* TEMPLATE METHODS */

    /**
     * click 'Search Wikipedia' input and check appears Search input
     */
    public void initSearchInput() {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT,
                "Cannot find and click element", 5);
        this.waitForElementPresent(SEARCH_INPUT,
                "Cannot find search input after clicking init search element");
    }

    /**
     * typeSearchLine
     *
     * @param search_line
     */
    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line,
                "Cannot find and type search input", 5);
    }

    /**
     * check text in search results
     */
    public void waitForSearchResult(String substring) {
        String search_result_locator = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_locator,
                "Cannot find search result with substring " + substring, 15);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON,
                "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisAppear() {
        this.waitForElementIsNotPresent(SEARCH_CANCEL_BUTTON,
                "Search cancel button is still present", 5);
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(
                SEARCH_EMPTY_RESULT_ELEMENT,
                "Cannot find empty result label", 15);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON,
                "Cannot find and click search cancel button", 5);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_locator = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_locator,
                "Cannot find and click search result with substring " + substring, 15);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request", 20);
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void assertThereIsNoResultSearch() {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT,
                "We supposed not to find any results");
    }

    /**
     * check label text in Search Wikipedia input
     */
    public void assertSearchInputHasLabelText() {
        this.assertElementHasText(SEARCH_INIT_TEXT,
                "Search Wikipedia",
                "Search wikipedia input has not label text 'Search Wikipedia'");
    }

    public void assertResultsContainsText(String exp_text) {
        List<WebElement> result_items = this.waitForElementsPresent(LIST_ITEM_CONTAINER,
                "Cannot find results on result page", 20);
        for (int i = 0; i < result_items.size() - 1; i++) {
            String actual_title_value = result_items.get(i)
                    .findElement(this.getLocatorByString(LIST_ITEM_TITLE)).getText();
            String actual_description_value = result_items.get(i)
                    .findElement(this.getLocatorByString(LIST_ITEM_DESCRIPTION)).getText();
            assertTrue(
                    "Cannot find word " + exp_text + " in 'title' or 'description' in result page\nAR = " +
                            actual_title_value + " / " + actual_description_value,
                    actual_title_value.contains(exp_text) || actual_description_value.contains(exp_text));
        }
    }

    public void waitForElementByTitleAndDescription(String title, String description) {
        String xpath = this.getResultSearchElementByTitleAndDescription(title, description);
        List<WebElement> result_items = this.waitForElementsPresent(xpath,
                "Cannot find results on 'Result page'; when title contains text: " + title +
                        " and description contains text" + description,
                15);
        assertTrue(
                "Amount results on 'Result Page' less 3",
                result_items.size() >= 3);
    }
}
