package ui;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject {

    private static final String
        STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia",
        STEP_NEW_WAYS_TO_EXPLORE_TEXT = "id:New ways to explore",
        STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK = "id:Add or edit preferred languages",
        STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "id:Learn more about data collected",
        NEXT_LINK = "id:Next",
        GET_STARTED_BUTTON = "id:Get started",
        SKIP_LINK = "id:Skip",
        DISMISS_BUTTON = "xpath:(//XCUIElementTypeButton[@name='Dismiss'])[1]";

    /**
     * Конструктор
     * @param driver
     */
    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForLearnMoreLink() {
        this.waitForElementPresent(STEP_LEARN_MORE_LINK,
                "Cannot find 'Learn more about Wikipedia' link", 15);
    }

    public void waitForNewWaysToExploreText() {
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE_TEXT,
                "Cannot find 'New ways to explore' text", 15);
    }

    public void waitForAddOrEditPreferredLanguagesLink() {
        this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK,
                "Cannot find 'Add or edit preferred languages' link", 15);
    }

    public void waitForLearnMoreAboutDataCollectedLink() {
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK,
                "Cannot find 'Add or edit preferred languages' link", 15);
    }

    public void clickNextLink() {
        this.waitForElementAndClick(NEXT_LINK,
                "Cannot find and click 'Next' button", 5);
    }

    public void clickGetStartedButton() {
        this.waitForElementAndClick(GET_STARTED_BUTTON,
                "Cannot find and click 'Get started' button", 5);
    }

    public void clickSkipLink() {
        this.waitForElementAndClick(SKIP_LINK,
                "Cannot find and click 'Skip' link", 5);
    }

    public void clickDismissButton() {
        this.waitForElementAndClick(DISMISS_BUTTON,
                "Cannot find and click 'Dismiss' button", 5);
    }
}