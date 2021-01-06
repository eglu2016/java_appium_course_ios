package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import platform.Platform;

import java.util.ArrayList;
import java.util.List;

abstract public class MyListPageObject extends MainPageObject {
    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            ALL_ARTICLES_LIST;

    public MyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    private static String getFolderXpathByName(String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedXpathByArticle(String article_title) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    public void openFolderByName(String name_of_folder) {
        this.waitForElementAndClick(getFolderXpathByName(name_of_folder),
                "Cannot find folder by name " + name_of_folder, 5);
    }

    public void waitForArticleToAppearByTitle(String article_title) {
        this.waitForElementPresent(
                getSavedXpathByArticle(article_title),
                "Cannot find saved article by title " + article_title, 15);
    }

    public void waitForArticleToDisappearByTitle(String article_title) {
        this.waitForElementIsNotPresent(getSavedXpathByArticle(article_title),
                "Saved article still present with title " + article_title, 15);
    }

    public void swipeByArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        this.swipeElementToLeft(getSavedXpathByArticle(article_title),
                "\nCannot find saved article by title " + article_title);
        if (Platform.getInstance().isIos()) {
            this.clickElementToTheRightUpperCorner(this.getSavedXpathByArticle(article_title),
                    "Cannot find saved article");
        }
        waitForArticleToDisappearByTitle(article_title);
    }

    public void clickByArticleTitleInFolder(String article_title) {
        this.waitForElementAndClick(getSavedXpathByArticle(article_title),
                "Cannot find and click article by title " + article_title, 10);
    }

    public int getTotalAmountArticlesInList() {
        return this.getAmountOfElements(ALL_ARTICLES_LIST);
    }

    public ArrayList<String> getTextArticlesFromList() {
        List<WebElement> elements = this.waitForElementsPresent(ALL_ARTICLES_LIST,
                "Cannot find elements by locator " + ALL_ARTICLES_LIST, 10);
        ArrayList<String> articles_text = new ArrayList<String>();
        for (WebElement element : elements) {
            String text = element.getText();
            articles_text.add(text);
        }
        return  articles_text;
    }
}
