package iOS;

import core.iOSTestCase;
import org.junit.Test;
import ui.WelcomePageObject;

public class GetStartedTest extends iOSTestCase {
    @Test
    public void testPassThroughWelcome() throws Exception {
        WelcomePageObject WelcomePage = new WelcomePageObject(driver);

        WelcomePage.waitForLearnMoreLink();
        WelcomePage.clickNextLink();

        WelcomePage.waitForNewWaysToExploreText();
        WelcomePage.clickNextLink();

        WelcomePage.waitForAddOrEditPreferredLanguagesLink();
        WelcomePage.clickNextLink();

        WelcomePage.waitForLearnMoreAboutDataCollectedLink();
        WelcomePage.clickGetStartedButton();

        Thread.sleep(1000);
    }
}
