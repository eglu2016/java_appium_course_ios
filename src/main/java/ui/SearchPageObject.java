package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import platform.Platform;

import java.util.List;

import static junit.framework.TestCase.*;

abstract public class SearchPageObject extends MainPageObject {

    protected static String
            LIST_RESULTS_BY_TILE_AND_DESCRIPTION_TPL,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT,
            SEARCH_INIT_TEXT,
            LIST_ITEM_CONTAINER,
            LIST_ITEM_TITLE,
            LIST_ITEM_DESCRIPTION,
            LIST_RESULTS_BY_TILE_DESCRIPTION;

    public SearchPageObject(RemoteWebDriver driver) {
        super(driver); // берем драйвер из MainPageObject
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
     * @param search_line -
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
                "\nCannot find search result with substring " + substring, 15);
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
                "Cannot find empty result label - " + SEARCH_EMPTY_RESULT_ELEMENT, 15);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON,
                "Cannot find and click search cancel button", 5);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_locator = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_locator,
                "\nCannot find and click search result with substring " + substring, 15);
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
        if (Platform.getInstance().isAndroid()) {
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
        } else {
            int count = 0;
            List<WebElement> resultList = this.waitForElementsPresent(LIST_RESULTS_BY_TILE_DESCRIPTION,
                        String.format("\n >>> cannot find results with text: '%s' on 'Result page'", exp_text),
                        15);
            for (WebElement element : resultList) {
                String actValue = element.getText();
                if (actValue.contains(exp_text)) {
                    count++;
                }
            }
            assertFalse(String.format(" >>> cannot find word '%s' in description", exp_text), count == 0);
        }
    }

    public void waitForElementByTitleAndDescription(String title, String description) {
        String xpath = this.getResultSearchElementByTitleAndDescription(title, description);
        List<WebElement> result_items = this.waitForElementsPresent(xpath,
                "Cannot find results on 'Result page'; when title contains text: " + title +
                        " and description contains text: " + description,
                15);
        assertTrue(
                "\nAmount results on 'Result Page' less 3",
                result_items.size() >= 3);
    }

    public void checkElementsByTitleAndDescription(String text) {
        int count = 0;
        List<WebElement> resultList = this.waitForElementsPresent(LIST_RESULTS_BY_TILE_DESCRIPTION,
                "Cannot find results with text: " + text + " on 'Result page'", 15);
        for (WebElement element : resultList) {
            String actValue = element.getText();
            String[] split = actValue.split(text);
            int number_of_words_found = split.length - 1;
            if (number_of_words_found == 2) {
                count++;
            }
        }
        assertTrue(
                "\namount results with text " + text +
                        " in title and description on 'Result Page' less 3; AR = " + count,
                count >= 3);
    }
}
