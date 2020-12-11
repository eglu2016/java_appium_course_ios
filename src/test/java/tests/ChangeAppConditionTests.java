package tests;

import core.CoreTestCase;
import org.junit.Test;
import ui.ArticlePageObject;
import ui.SearchPageObject;
import ui.factories.ArticlePageObjectFactory;
import ui.factories.SearchPageObjectFactory;

public class ChangeAppConditionTests extends CoreTestCase {
    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String title_before_rotate = ArticlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        String title_after_rotate = ArticlePageObject.getArticleTitle();
        assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotate, title_after_rotate);
        this.rotateScreenPortrait();
        String title_after_second_rotate = ArticlePageObject.getArticleTitle();
        assertEquals(
                "Article title have been changed after screen rotation (portrait)",
                title_before_rotate, title_after_second_rotate);
    }

    @Test
    public void testCheckSearchArticleInBackground() throws InterruptedException {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(2);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        Thread.sleep(1000);
    }
}
