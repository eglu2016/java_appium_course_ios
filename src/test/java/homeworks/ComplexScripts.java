package homeworks;

import core.CoreTestCase;
import org.junit.Assert;
import org.junit.Test;
import platform.Platform;
import ui.ArticlePageObject;
import ui.MyListPageObject;
import ui.NavigationUI;
import ui.SearchPageObject;
import ui.factories.ArticlePageObjectFactory;
import ui.factories.MyListPageObjectFactory;
import ui.factories.SearchPageObjectFactory;
import ui.factories.NavigationUiFactory;

import java.util.ArrayList;

public class ComplexScripts extends CoreTestCase {

    private static String name_of_folder = "os_lists";
    private static String name_of_first_article = "Android (operating system)";
    private static String name_of_second_article = "Microsoft Windows";

    @Test
    public void testSavedOfTwoArticles() throws InterruptedException {
        String first_article_title = "";
        String second_article_title = "";

        SearchPageObject search = SearchPageObjectFactory.get(driver);
        search.initSearchInput();
        search.typeSearchLine("Android");
        search.clickByArticleWithSubstring(name_of_first_article);

        ArticlePageObject article = ArticlePageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            article.waitForTitleElement();
            first_article_title = article.getArticleTitle();
            article.addArticleToMyNewList(name_of_folder);
        } else {
            article.waitForTitleElement(name_of_first_article);
            first_article_title = name_of_first_article;
            article.addArticleToMySaved();
        }
        article.closeArticle();

        // add second article
        search.initSearchInput();
        search.typeSearchLine("Microsoft Windows");
        search.clickByArticleWithSubstring(name_of_second_article);
        if (Platform.getInstance().isAndroid()) {
            article.waitForTitleElement();
            second_article_title = article.getArticleTitle();
            article.addArticleToMyNewList(name_of_folder);
        } else {
            article.waitForTitleElement(name_of_second_article);
            second_article_title = name_of_second_article;
            article.addArticleToMySaved();
        }
        article.closeArticle();

        NavigationUI NavigationUI = NavigationUiFactory.get(driver);
        NavigationUI.clickMyList();

        MyListPageObject myList = MyListPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            myList.openFolderByName(name_of_folder);
        }
        myList.swipeByArticleToDelete(first_article_title);

        if (Platform.getInstance().isAndroid()) {
            myList.clickByArticleTitleInFolder("Microsoft Windows");
            String actual_title_article = article.getArticleTitle();
            assertEquals(
                    "\nArticle title have been wrong value after opened",
                    second_article_title, actual_title_article);
        } else {
            assertEquals("\nWrong total amount articles in 'All Articles' list after deleted",
                    1, myList.getTotalAmountArticlesInList());
            ArrayList<String> articles_value = new ArrayList<String>();
            articles_value = myList.getTextArticlesFromList();
            assertTrue("\nIncorrect article text in 'All Articles' list.",
                    articles_value.get(0).contains(name_of_second_article));
        }
        Thread.sleep(500);
    }

    @Test
    public void testAssertTitle() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Android");
        SearchPageObject.clickByArticleWithSubstring("Android (operating system)");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.assertArticleTitle("Android (operating system)");
    }

    @Test
    public void testCheckResultsSearchByTitleAndDescription() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("BMW 3 Series");
        SearchPageObject.waitForElementByTitleAndDescription("BMW", "BMW");
    }
}
