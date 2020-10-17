package ui;

import io.appium.java_client.AppiumDriver;
import platform.Platform;

abstract public class MyListPageObject extends MainPageObject {
    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL;

    public MyListPageObject(AppiumDriver driver) {
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
                "Cannot find and click article by title " + article_title, 15);
    }
}
