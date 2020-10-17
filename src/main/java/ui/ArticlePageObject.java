package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import platform.Platform;

import static junit.framework.TestCase.assertEquals;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            TITLE,
            FOOTER_ELEMENT,
            OPTION_BUTTON,
            OPTION_ADD_TO_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            FOLDER_BY_NAME_TPL,
            CLOSE_BUTTON,
            LOG_IN_TO_SYNC_YOUR_SAVED_ARTICLES_BUTTON;

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    private static String getFolderXpathByName(String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE,
                "\nCannot find article title on page from locator: " + TITLE,
                15);
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        return title_element.getText();
    }

    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "\nCannot find the of article by locator: " + FOOTER_ELEMENT,
                    40);
        } else {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT,
                    "\nCannot find the of article by locator: " + FOOTER_ELEMENT,
                    40);
        }
    }

    public void addArticleToMyNewList(String name_of_folder) {
        this.waitForElementAndClick(OPTION_BUTTON,
                "Cannot find and press 'More options' button", 5);
        this.waitForElementAndClick(OPTION_ADD_TO_MY_LIST_BUTTON,
                "Cannot find and press 'Add to reading list' item", 5);
        // click 'Got It'
        this.waitForElementAndClick(ADD_TO_MY_LIST_OVERLAY,
                "Cannot find and press 'Got It' button", 5);
        // clear Name of List input
        this.waitForElementAndClear(MY_LIST_NAME_INPUT,
                "Cannot find and clear 'Name of List' input", 10);
        // set Learning programing in input
        this.waitForElementAndSendKeys(MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text in 'Name of List' input", 5);
        // click 'OK'
        this.waitForElementAndClick(MY_LIST_OK_BUTTON,
                "Cannot find and press 'OK' button", 5);
    }

    public void addArticleToMyList(String name_of_folder) {
        // click More options
        this.waitForElementAndClick(OPTION_BUTTON,
                "Cannot find and press 'More options' button", 5);
        // click 'Add to reading list'
        this.waitForElementAndClick(OPTION_ADD_TO_MY_LIST_BUTTON,
                "Cannot find and press 'Add to reading list' item", 5);
        // click Name Folder
        this.waitForElementAndClick(getFolderXpathByName(name_of_folder),
                "Cannot find folder by name " + name_of_folder, 5);
    }

    public void addArticleToMySaved() {
        this.waitForElementAndClick(OPTION_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list", 5);
        this.waitForElementAndClick(LOG_IN_TO_SYNC_YOUR_SAVED_ARTICLES_BUTTON,
                "Cannot find and click 'Log in to sync ...' button", 5);
        this.waitForElementAndClick(CLOSE_BUTTON,
                "Cannot find and click 'Close' button", 5);
    }

    public void closeArticle() {
        this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON,
                "Cannot find X link and close article", 5);
    }

    public void assertArticleTitle(String article_title) {
        String actual_title_article = this.getArticleTitle();
        assertEquals(
                "Article title have been wrong value",
                article_title, actual_title_article);
    }
}
