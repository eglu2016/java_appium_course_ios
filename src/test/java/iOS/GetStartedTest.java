package iOS;

import core.CoreTestCase;
import org.junit.Test;
import ui.WelcomePageObject;

public class GetStartedTest extends CoreTestCase {
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
