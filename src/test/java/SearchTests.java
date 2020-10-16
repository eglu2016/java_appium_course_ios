import core.CoreTestCase;
import org.junit.Test;
import ui.SearchPageObject;
import ui.factories.SearchPageObjectFactory;

public class SearchTests extends CoreTestCase {
    @Test
    public void testSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
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
        String search_line = "linkin park discography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_result = SearchPageObject.getAmountOfFoundArticles();
        assertTrue("We found too few results",
                amount_of_search_result > 0);
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