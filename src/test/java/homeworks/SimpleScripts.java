package homeworks;

import core.CoreTestCase;
import org.junit.Test;
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
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("IATA");
        SearchPageObject.waitForSearchResult("Redirected from IATA");
        SearchPageObject.waitForSearchResult("Three-letter code designating many airports around the world");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.clickCancelSearch();
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
