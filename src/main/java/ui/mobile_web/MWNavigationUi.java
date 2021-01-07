package ui.mobile_web;

import org.openqa.selenium.remote.RemoteWebDriver;
import ui.NavigationUI;

public class MWNavigationUi extends NavigationUI {
    static {
        MY_LIST_LINK = "xpath://span[text()='Список наблюдения']";
        OPEN_NAVIGATION_BUTTON = "xpath://label[contains(@id, 'main-menu-button')]";
    }

    public MWNavigationUi(RemoteWebDriver driver) {
        super(driver);
    }
}