package ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.Platform;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class MainPageObject {
    protected RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSecond) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(this.getLocatorByString(locator)));
    }

    public WebElement waitForElementPresent(String locator, String error_message) {
        return waitForElementPresent(locator, error_message, 5);
    }

    public boolean waitForElementIsNotPresent(String locator, String error_message, long timeoutInSecond) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(this.getLocatorByString(locator)));
    }

    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSecond) {
        WebElement element = this.waitForElementPresent(locator, error_message, timeoutInSecond);
        if (Platform.getInstance().isMW()) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(
                    "arguments[0].setAttribute('style', 'background: green; border: 3px solid blue;');", element);
        }
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, long timeoutInSecond) {
        WebElement element = this.waitForElementPresent(locator, error_message, timeoutInSecond);
        element.sendKeys(value);
        return element;
    }

    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutInSecond) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSecond);
        element.clear();
        return element;
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String error_message, long timeOutInSecond) {
        WebElement element = waitForElementPresent(locator, error_message, timeOutInSecond);
        return element.getAttribute(attribute);
    }

    public String waitForElementAndGetText(String locator, String error_message, long timeOutInSecond) {
        WebElement element = waitForElementPresent(locator, error_message, timeOutInSecond);
        return element.getText();
    }

    public List<WebElement> waitForElementsPresent(String locator, String error_message, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                this.getLocatorByString(locator)));
    }

    public void swipeUp(int timeOfSwipe) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            TouchAction action = new TouchAction(driver);
            Dimension size = driver.manage().window().getSize();
            int x = size.width / 2;
            int start_y = (int) (size.height * 0.8);
            int end_y = (int) (size.height * 0.2);
            action
                    .press(PointOption.point(x, start_y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                    .moveTo(PointOption.point(x, end_y))
                    .release()
                    .perform();
        } else {
            System.out.println("Метод 'swipeUp()' для браузера не поддерживается " +
                    Platform.getInstance().getPlatformVar());
        }
    }

    public void clickElementToTheRightUpperCorner(String locator, String error_msg) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            WebElement element = this.waitForElementPresent(locator + "/..", error_msg);
            int right_x = element.getLocation().getX();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y + lower_y) / 2;
            int width = element.getSize().getWidth();
            int point_to_click_x = (right_x + width) - 3;
            int point_to_click_y = middle_y;
            TouchAction action = new TouchAction(driver);
            action.tap(PointOption.point(point_to_click_x, point_to_click_y)).perform();
        } else {
            System.out.println("Метод 'clickElementToTheRightUpperCorner()' для браузера не поддерживается " +
                    Platform.getInstance().getPlatformVar());
        }
    }

    public void swipeUpQuick() { swipeUp(200); }

    public void swipeUpToFindElement(String locator, String error_message, int max_swipe) {
        int already_swiped = 0;
        while (driver.findElements(this.getLocatorByString(locator)).size() == 0) {
            if (already_swiped > max_swipe) {
                waitForElementPresent(locator,
                        "\nCannot find element by swiping up" + "\n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    public void swipeUpTillElementAppear(String locator, String error_msg, int max_swipe) {
        int already_swiped = 0;
        while (!this.isElementLocatedOnThisScreen(locator)) {
            if (already_swiped > max_swipe) {
                assertTrue(error_msg, this.isElementLocatedOnThisScreen(locator));
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    public boolean isElementLocatedOnThisScreen(String locator) {
        // получаем расположение по оси Y
        int elem_location_by_y = this.waitForElementPresent(
                locator, "Cannot find element by locator " + locator, 1)
                .getLocation().getY();
        if (Platform.getInstance().isMW()) {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            Object jsResult = jsExecutor.executeScript("return window.pageYOffset");
            elem_location_by_y -= Integer.parseInt(jsResult.toString());
        }
        // длина всего экрана
        int screen_size_by_y = driver.manage().window().getSize().getHeight();
        return elem_location_by_y < screen_size_by_y;
    }

    public void swipeElementToLeft(String locator, String error_message) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            WebElement element = waitForElementPresent(locator, error_message, 10);
            // получаем левую точку по оси Х
            int left_x = element.getLocation().getX();
            // получаем правую точку по оси X
            int right_x = left_x + element.getSize().getWidth();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y + lower_y) / 2;
            TouchAction action = new TouchAction(driver);
            action.press(PointOption.point(right_x, middle_y));
            action.waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)));
            if (Platform.getInstance().isAndroid()) {
                action.moveTo(PointOption.point(left_x, middle_y));
            } else {
                // вычисляем ширину элемента
                int offset_x = (-1 * element.getSize().getWidth());
                action.moveTo(PointOption.point(offset_x, 0));
            }
            action.release();
            action.perform();
        } else {
            System.out.println("Метод 'swipeElementToLeft()' для браузера не поддерживается " +
                    Platform.getInstance().getPlatformVar());
        }
    }

    public void swipeElementToLeft(WebElement element) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            // получаем левую точку по оси Х
            int left_x = element.getLocation().getX();
            // получаем правую точку по оси X
            int right_x = left_x + element.getSize().getWidth();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y + lower_y) / 2;
            TouchAction action = new TouchAction(driver);
            action
                    .press(PointOption.point(right_x, middle_y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                    .moveTo(PointOption.point(left_x, middle_y))
                    .release()
                    .perform();
        }
    }

    public int getAmountOfElements(String locator) {
        List elements = driver.findElements(this.getLocatorByString(locator));
        return elements.size();
    }

    public void assertElementNotPresent(String locator, String error_message) {
        int amount_of_elements = getAmountOfElements(locator);
        if (amount_of_elements > 0) {
            String default_message = "An element " + locator + " supposed to be present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public void assertElementHasText(String locator, String expected_text, String error_message) {
        WebElement element = waitForElementPresent(locator, " :: Cannot find element " + locator, 30);
        String actualText = element.getText();
        if (actualText.equals("")) {
            actualText = element.getAttribute("aria-label");
        }
        assertEquals(error_message, expected_text, actualText);
    }

    public void assertElementPresent(String locator, String error_message) {
        boolean elementIsEnabled = true;
        try {
            driver.findElement(this.getLocatorByString(locator));
        } catch (Exception e) {
            elementIsEnabled = false;
        }
        assertTrue(
                error_message + "; locator: " + locator,
                elementIsEnabled);
    }

    public void scrollWebPageUp() {
        if (Platform.getInstance().isMW()) {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("window.scrollBy(0, 250)");
        } else {
            System.out.println("Метод 'scrollWebPageUp()' не поддерживается для - " +
                    Platform.getInstance().getPlatformVar());
        }
    }

    public void scrollWebPageTillElementNotVisible(String locator, String errorMessage, int maxSwipe) {
        int alreadySwipe = 0;
        WebElement element = this.waitForElementPresent(locator, errorMessage);
        while (!this.isElementLocatedOnThisScreen(locator)) {
            scrollWebPageUp();
            alreadySwipe++;
            if (alreadySwipe > maxSwipe) {
                Assert.assertTrue(errorMessage, element.isDisplayed());
            }
        }
    }

    protected By getLocatorByString(String locator_with_type) {
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];
        if (by_type.equals("xpath")) {
            return By.xpath(locator);
        } else if (by_type.equals("id")) {
            return By.id(locator);
        } else if (by_type.equals("css")) {
            return By.cssSelector(locator);
        } else {
            throw new IllegalArgumentException("Cannot get type of locator " + locator_with_type);
        }
    }

    public boolean isElementPresent(String locator) {
        return getAmountOfElements(locator) > 0;
    }

    public void tryClickElementWithFewAttempts(String locator, String errorMessage, int amountOfAttempts) {
        int currentAttempts = 0;
        boolean needMoreAttempts = true;
        while (needMoreAttempts) {
            try {
                this.waitForElementAndClick(locator, errorMessage, 1);
                needMoreAttempts = false;
            } catch (Exception e) {
                if (currentAttempts > amountOfAttempts) {
                    this.waitForElementAndClick(locator, errorMessage, 1);
                }
            }
            ++ currentAttempts;
        }
    }
 }
