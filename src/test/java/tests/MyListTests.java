package tests;

import core.CoreTestCase;
import org.junit.Test;
import platform.Platform;
import ui.*;
import ui.factories.ArticlePageObjectFactory;
import ui.factories.MyListPageObjectFactory;
import ui.factories.NavigationUiFactory;
import ui.factories.SearchPageObjectFactory;

public class MyListTests extends CoreTestCase {

    private static String
            name_of_folder = "Learning programming",
            login = "rtstender2021",
            password = "Aasd654321";

    @Test
    public void testSaveFirstArticleToMyList() throws Exception {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        if (Platform.getInstance().isMW()) {
            SearchPageObject.clickByArticleWithSubstring("язык программирования");
        } else {
            SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        }
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyNewList(name_of_folder);
        } else if (Platform.getInstance().isIos()) {
            ArticlePageObject.addArticleToMySaved();
        } else {
            Thread.sleep(1000);
            ArticlePageObject.addArticleToMySaved();
            AuthorizationPageObject AuthorizationPageObject = new AuthorizationPageObject(driver);
            boolean result = AuthorizationPageObject.checkOpenedSection();
            Thread.sleep(1000);
            AuthorizationPageObject.clickAuthorizationButton();
            Thread.sleep(1000);
            AuthorizationPageObject.enterLoginData(login, password);
            AuthorizationPageObject.submitForm();
            ArticlePageObject.waitForTitleElement();
            assertEquals("Мы не на той же странице после авторизации",
                    article_title, ArticlePageObject.getArticleTitle());
            ArticlePageObject.addArticleToMySaved();
        }
        // Back
        ArticlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUiFactory.get(driver);
        navigationUI.clickOpenNavigationButton();
        navigationUI.clickMyList();

        MyListPageObject MyListPageObject = MyListPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            MyListPageObject.openFolderByName(name_of_folder);
        }
        MyListPageObject.swipeByArticleToDelete(article_title);
        Thread.sleep(1000);
    }
}