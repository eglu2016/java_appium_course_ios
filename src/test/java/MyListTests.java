import core.CoreTestCase;
import org.junit.Test;
import platform.Platform;
import ui.ArticlePageObject;
import ui.MyListPageObject;
import ui.NavigationUI;
import ui.SearchPageObject;
import ui.factories.ArticlePageObjectFactory;
import ui.factories.MyListPageObjectFactory;
import ui.factories.NavigationUiFactory;
import ui.factories.SearchPageObjectFactory;

public class MyListTests extends CoreTestCase {

    private static String name_of_folder = "Learning programming";

    @Test
    public void testSaveFirstArticleToMyList() throws Exception {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyNewList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }
        // Back
        ArticlePageObject.closeArticle();
        NavigationUI navigationUI = NavigationUiFactory.get(driver);
        navigationUI.clickMyList();
        MyListPageObject MyListPageObject = MyListPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            MyListPageObject.openFolderByName(name_of_folder);
        }
        MyListPageObject.swipeByArticleToDelete(article_title);
        Thread.sleep(1000);
    }
}