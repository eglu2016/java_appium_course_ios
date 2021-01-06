package ui.ios;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import ui.NavigationUI;

public class IosNavigationUi extends NavigationUI {
    static {
        MY_LIST_LINK = "id:Saved";
    }

    public IosNavigationUi(RemoteWebDriver driver) {
        super(driver);
    }
}