package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import platform.Platform;

abstract public class NavigationUI extends MainPageObject {
    protected static String
            MY_LIST_LINK,
            OPEN_NAVIGATION_BUTTON;

    public NavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickMyList() {
        if (Platform.getInstance().isMW()) {
            this.tryClickElementWithFewAttempts(MY_LIST_LINK,
                    "Не найдена или не нажата кнопка 'Список наблюдения'", 5);
        } else {
            this.waitForElementAndClick(MY_LIST_LINK,
                    "Cannot find and press navigation 'My lists' button", 10);
        }
    }

    /**
     * открытие главного меню
     */
    public void clickOpenNavigationButton() {
        if (Platform.getInstance().isMW()) {
            this.waitForElementAndClick(OPEN_NAVIGATION_BUTTON,
                    "Не найдена кнопка 'Открыть главное меню'", 5);
        } else {
            System.out.println("Метод 'clickOpenNavigationButton()' не поддерживается - " +
                    Platform.getInstance().getPlatformVar());
        }
    }
}