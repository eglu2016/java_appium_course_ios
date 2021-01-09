package homeworks;

import core.CoreTestCase;
import org.junit.Test;
import platform.Platform;
import ui.SearchPageObject;
import ui.factories.SearchPageObjectFactory;

public class SimpleScripts extends CoreTestCase {
    @Test
    public void testMethodCreation() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.assertSearchInputHasLabelText();
    }

    @Test
    public void testCancelSearch() {
        String searchLine = "", descriptionSearchResultOne = "", descriptionSearchResultTwo = "";
        if (Platform.getInstance().isMW()) {
            searchLine = "Windows";
            descriptionSearchResultOne = "семейство проприетарных операционных систем корпорации Microsoft";
            descriptionSearchResultTwo = "версия операционной системы Windows";
        } else if (Platform.getInstance().isAndroid()) {
            searchLine = "IATA";
            descriptionSearchResultOne = "Redirected from IATA";
            descriptionSearchResultTwo = "Three-letter code designating many airports around the world";
        } else {
            searchLine = "IATA";
            descriptionSearchResultOne = "Redirected from: IATA";
            descriptionSearchResultTwo = "Three-letter code designating many airports around the world";
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchLine);
        SearchPageObject.waitForSearchResult(descriptionSearchResultOne);
        SearchPageObject.waitForSearchResult(descriptionSearchResultTwo);
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        if (Platform.getInstance().isAndroid()) {
            SearchPageObject.clickCancelSearch();
        }
        SearchPageObject.waitForCancelButtonToDisAppear();
    }

    @Test
    public void testCheckWorldsInSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.assertResultsContainsText("Java");
    }
}