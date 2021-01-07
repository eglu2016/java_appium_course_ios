package ui;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {
    private final static String
        CANCEL_BUTTON = "xpath://*[contains(@class,'cancel')]",
        LOGIN_BUTTON = "xpath://body/div//a[text()='Войти']",
        LOGIN_INPUT = "xpath://input[@id='wpName1']",
        PASSWORD_INPUT = "xpath://input[@id='wpPassword1']",
        SUBMIT_BUTTON = "xpath://button[@id='wpLoginAttempt']";

    public AuthorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public boolean checkOpenedSection() {
        try {
            WebElement element = this.waitForElementPresent(CANCEL_BUTTON, "-", 5);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);",element);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public void clickAuthorizationButton() {
        this.waitForElementPresent(LOGIN_BUTTON, "Не найдена кнопка 'Войти' для авторизации - " + LOGIN_BUTTON,
                10);
        this.waitForElementAndClick(LOGIN_BUTTON, "Не нажата кнопка 'Войти' - " + LOGIN_BUTTON,
                10);
    }

    public void enterLoginData(String login, String password) {
        this.waitForElementAndSendKeys(LOGIN_INPUT, login,
                "Не найдено поле для ввода логина", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password,
                "Не найдено поле для ввода пароля", 5);
    }

    public void submitForm() {
        this.waitForElementAndClick(SUBMIT_BUTTON,
                "Не найдена или не нажата кнопка 'Войти' - " + SUBMIT_BUTTON, 5);
    }
}