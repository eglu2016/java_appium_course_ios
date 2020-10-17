package ui.ios;

import io.appium.java_client.AppiumDriver;
import ui.NavigationUI;

public class IosNavigationUi extends NavigationUI {
    static {
        MY_LIST_LINK = "id:Saved";
    }

    public IosNavigationUi(AppiumDriver driver) {
        super(driver);
    }
}