package homeworks;

import core.CoreTestCase;
import org.junit.Assert;
import org.junit.Test;
import platform.Platform;
import ui.*;
import ui.factories.ArticlePageObjectFactory;
import ui.factories.MyListPageObjectFactory;
import ui.factories.SearchPageObjectFactory;
import ui.factories.NavigationUiFactory;
import java.util.ArrayList;

public class ComplexScripts extends CoreTestCase {

    private static String
            name_of_folder = "os_lists",
            login = "rtstender2021",
            password = "Aasd654321";

    @Test
    public void testSavedOfTwoArticles() throws InterruptedException {
        System.out.println("\n\n----- run: testSavedOfTwoArticles ----- ");
        String first_article_title = "", name_of_first_article = "";
        String second_article_title = "", name_of_second_article = "";
        if (Platform.getInstance().isMW()) {
            name_of_first_article = "бесплатная и открытая операционная система для мобильных устройств, " +
                    "разрабатываемая компанией Google";
            name_of_second_article = "семейство проприетарных операционных систем корпорации Microsoft";
        } else {
            name_of_first_article = "Android (operating system)";
            name_of_second_article = "Microsoft Windows";
        }

        SearchPageObject search = SearchPageObjectFactory.get(driver);
        search.initSearchInput();
        search.typeSearchLine("Android");
        search.clickByArticleWithSubstring(name_of_first_article);

        ArticlePageObject article = ArticlePageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            article.waitForTitleElement();
            first_article_title = article.getArticleTitle();
            article.addArticleToMyNewList(name_of_folder);
        } else if (Platform.getInstance().isIos()) {
            article.waitForTitleElement(name_of_first_article);
            first_article_title = name_of_first_article;
            article.addArticleToMySaved();
        } else {
            Thread.sleep(1000);
            first_article_title = article.getArticleTitle();
            article.addArticleToMySaved();
            AuthorizationPageObject AuthorizationPageObject = new AuthorizationPageObject(driver);
            boolean result = AuthorizationPageObject.checkOpenedSection();
            Thread.sleep(1000);
            AuthorizationPageObject.clickAuthorizationButton();
            Thread.sleep(1000);
            AuthorizationPageObject.enterLoginData(login, password);
            AuthorizationPageObject.submitForm();
            article.waitForTitleElement();
            assertEquals("Мы не на той же странице после авторизации",
                    "Android", article.getArticleTitle());
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
        } else if (Platform.getInstance().isIos()) {
            article.waitForTitleElement(name_of_second_article);
            second_article_title = name_of_second_article;
            article.addArticleToMySaved();
        } else {
            Thread.sleep(1000);
            second_article_title = article.getArticleTitle();
            article.addArticleToMySaved();
        }
        article.closeArticle();

        NavigationUI NavigationUI = NavigationUiFactory.get(driver);
        NavigationUI.clickOpenNavigationButton();
        NavigationUI.clickMyList();

        MyListPageObject myList = MyListPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            myList.openFolderByName(name_of_folder);
        }
        System.out.println("first_article_title = " + first_article_title);
        myList.swipeByArticleToDelete(first_article_title);
        Thread.sleep(1000);

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
                    articles_value.get(0).contains(second_article_title));
        }
        Thread.sleep(500);
    }

    @Test
    public void testAssertTitle() {
        System.out.println("\n\n----- run: testAssertTitle ----- ");
        String searchLine = "Android";
        String descriptionArticle = "", headingArticle = "";
        if (Platform.getInstance().isMW()) {
            descriptionArticle = "одиннадцатая версия ОС Android";
            headingArticle = "Android 11";
        } else {
            descriptionArticle = "Android (operating system)";
            headingArticle = descriptionArticle;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchLine);
        SearchPageObject.clickByArticleWithSubstring(descriptionArticle);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement(headingArticle);
        ArticlePageObject.assertArticleTitle(headingArticle);
    }

    @Test
    public void testCheckResultsSearchByTitleAndDescription() {
        System.out.println("\n\n----- run: testCheckResultsSearchByTitleAndDescription ----- ");
        String search_line = "bmw 3 series";
        String exp_substring = "BMW";
        SearchPageObject search = SearchPageObjectFactory.get(driver);
        search.initSearchInput();
        search.typeSearchLine(search_line);
        if (Platform.getInstance().isAndroid()) {
            search.waitForElementByTitleAndDescription(exp_substring, exp_substring);
        } else {
            search.checkElementsByTitleAndDescription(exp_substring);
        }
    }
}
