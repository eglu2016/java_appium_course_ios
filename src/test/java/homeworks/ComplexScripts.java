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
            FOLDER_NAME_FOR_ARTICLES = "FOLDER_ARTICLES",
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
            article.addArticleToMyNewList(FOLDER_NAME_FOR_ARTICLES);
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
            assertEquals(" :: мы не на той же странице после авторизации",
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
            article.addArticleToMyList(FOLDER_NAME_FOR_ARTICLES);

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
            myList.openFolderByName(FOLDER_NAME_FOR_ARTICLES);
        }
        myList.swipeByArticleToDelete(first_article_title);
        Thread.sleep(1000);

        if (Platform.getInstance().isAndroid()) {
            myList.clickByArticleTitleInFolder("Microsoft Windows");
            String actual_title_article = article.getArticleTitle();
            assertEquals(" :: article title have been wrong value after opened",
                    second_article_title, actual_title_article);
        } else {
            assertEquals(" :: wrong total amount articles in 'All Articles' list after deleted",
                    1, myList.getTotalAmountArticlesInList());
            ArrayList<String> articles_value = new ArrayList<String>();
            articles_value = myList.getTextArticlesFromList();
            assertTrue(" :: incorrect article text in 'All Articles' list.",
                    articles_value.get(0).contains(second_article_title));
        }
        Thread.sleep(500);
    }

    @Test
    public void testAssertTitle() throws InterruptedException {
        System.out.println("\n\n----- run: testAssertTitle ----- ");
        String searchLine = "Android";
        String descriptionArticle = "", headingArticle = "";
        if (Platform.getInstance().isMW()) {
            descriptionArticle = "одиннадцатая версия ОС Android";
            headingArticle = "Android 11";
        } else {
            headingArticle = descriptionArticle = "Android (operating system)";
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
        String searchLine = "", expSubstring = "";
        if (Platform.getInstance().isMW()) {
            searchLine = "Android";
            expSubstring = "Android";
        } else {
            searchLine = "bmw 3 series";
            expSubstring = "BMW";
        }
        SearchPageObject search = SearchPageObjectFactory.get(driver);
        search.initSearchInput();
        search.typeSearchLine(searchLine);
        if (Platform.getInstance().isAndroid() || Platform.getInstance().isMW()) {
            search.waitForElementByTitleAndDescription(expSubstring, expSubstring);
        } else {
            search.checkElementsByTitleAndDescription(expSubstring);
        }
    }
}
