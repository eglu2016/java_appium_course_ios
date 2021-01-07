package ui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import platform.Platform;
import static junit.framework.TestCase.assertEquals;

abstract public class ArticlePageObject extends MainPageObject {
    protected static String
            FOLDER_BY_NAME_TPL,
            TITLE_ARTICLE_TPL,
            TITLE,
            FOOTER_ELEMENT,
            OPTION_BUTTON,
            OPTION_ADD_TO_MY_LIST_BUTTON,
            OPTION_REMOVE_TO_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            CLOSE_BUTTON,
            LOG_IN_TO_SYNC_YOUR_SAVED_ARTICLES_BUTTON;

    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    private static String getFolderXpathByName(String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getArticleXpathByName(String name_of_article) {
        return TITLE_ARTICLE_TPL.replace("{ARTICLE_NAME}", name_of_article);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE,
                "\n ---> Cannot find article title on page from locator: " + TITLE,
                15);
    }

    public WebElement waitForTitleElement(String title_article) {
        String locator = this.getArticleXpathByName(title_article);
        return this.waitForElementPresent(locator,
                "\n ---> Cannot find article title on page from locator: " + locator,
                15);
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        return title_element.getText();
    }

    public String getArticleTitle(String title_article) {
        WebElement titleWebElement = waitForTitleElement(title_article);
        return titleWebElement.getText();
    }

    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(FOOTER_ELEMENT,
                    "\nCannot find the of article by locator: " + FOOTER_ELEMENT, 40);
        } else if (Platform.getInstance().isIos()) {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT,
                    "\nCannot find the of article by locator: " + FOOTER_ELEMENT, 40);
        } else {
            this.scrollWebPageTillElementNotVisible(FOOTER_ELEMENT,
                    "\nCannot find the of article by locator: " + FOOTER_ELEMENT, 40);
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
        if (Platform.getInstance().isMW()) {
            this.removeArticleFromSavedIfItAdded();
        } else {
            this.waitForElementAndClick(OPTION_ADD_TO_MY_LIST_BUTTON,
                    "Cannot find option to add article to reading list", 5);
            try {
                this.waitForElementAndClick(LOG_IN_TO_SYNC_YOUR_SAVED_ARTICLES_BUTTON,
                        "Cannot find and click 'Log in to sync ...' button", 3);
                this.waitForElementAndClick(CLOSE_BUTTON,
                        "Cannot find and click 'Close' button", 3);
            } catch (Exception ex) {
                System.out.println("-");
            }
        }
    }

    /**
     * для удаления статьи, если она уже была ранее добавлена в лист
     */
    public void removeArticleFromSavedIfItAdded() {
        if (this.isElementPresent(OPTION_REMOVE_TO_MY_LIST_BUTTON)) {
           this.waitForElementAndClick(OPTION_REMOVE_TO_MY_LIST_BUTTON,
                   "Не нажата кнопка удаления статьи", 1);
        }
        this.waitForElementAndClick(OPTION_ADD_TO_MY_LIST_BUTTON,
                "Не найдена и не нажата кнопка добавления статьи в список, после удаления статьи",
                15);
    }

    public void closeArticle() {
        if (Platform.getInstance().isIos() || Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON,
                    "Cannot find X link and close article", 5);
        } else {
            System.out.println("Метод 'closeArticle()' не поддерживается - " +
                    Platform.getInstance().getPlatformVar());
        }
    }

    public void assertArticleTitle(String article_title) {
        String actual_title_article = "";
        if (Platform.getInstance().isAndroid())
            actual_title_article = this.getArticleTitle();
        else
            actual_title_article = this.getArticleTitle(article_title);
        assertEquals(
                String.format("Article title have been wrong value. AR = '%s', ER = '%s'",
                        actual_title_article, article_title),
                article_title, actual_title_article);
    }
}
