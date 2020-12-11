package tests;

import core.CoreTestCase;
import org.junit.Test;
import ui.ArticlePageObject;
import ui.SearchPageObject;
import ui.factories.ArticlePageObjectFactory;
import ui.factories.SearchPageObjectFactory;

public class ArticleTests extends CoreTestCase {
    @Test
    public void testCompareArticleTitle() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String article_title = ArticlePageObject.getArticleTitle();
        assertEquals("We see unexpected title!",
                "Java (programming language)",
                article_title);
    }

    @Test
    public void testSwipeArticleTitle() throws Exception {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();
        Thread.sleep(1000);
    }
}