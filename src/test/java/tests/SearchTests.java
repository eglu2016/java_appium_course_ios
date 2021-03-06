package tests;

import core.CoreTestCase;
import org.junit.Test;
import platform.Platform;
import ui.SearchPageObject;
import ui.factories.SearchPageObjectFactory;

public class SearchTests extends CoreTestCase {
    @Test
    public void testSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        String expectedSearchResult = "";
        if (Platform.getInstance().isMW()) {
            expectedSearchResult = "язык программирования";
        } else {
            expectedSearchResult = "Object-oriented programming language";
        }
        SearchPageObject.waitForSearchResult(expectedSearchResult);
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisAppear();
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();

        String search_line = "";
        if (Platform.getInstance().isMW()) {
            search_line = "Дискография Linkin Park";
        } else {
            search_line = "linkin park discography";
        }
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_result = SearchPageObject.getAmountOfFoundArticles();
        assertTrue("We found too few results", amount_of_search_result > 0);
    }

    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "xcdafgh";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultSearch();
    }
}