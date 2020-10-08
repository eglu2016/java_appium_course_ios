import core.CoreTestCase;
import org.junit.Test;
import ui.SearchPageObject;

public class SimpleScriptsHome extends CoreTestCase {
    @Test
    public void testMethodCreation() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.assertSearchInputHasLabelText();
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
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
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.assertResultsContainsText("Java");
    }
}
