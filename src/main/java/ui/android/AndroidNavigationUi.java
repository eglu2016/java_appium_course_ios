package ui.android;

import io.appium.java_client.AppiumDriver;
import ui.NavigationUI;

public class AndroidNavigationUi extends NavigationUI {

    static {
        MY_LIST_LINK = "xpath://*[@content-desc='My lists']";
    }

    public AndroidNavigationUi(AppiumDriver driver) {
        super(driver);
    }
}