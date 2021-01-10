package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.*;
import homeworks.*;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        // ArticleTests.class,
        // ChangeAppConditionTests.class,
        // GetStartedTest.class,
        // MyListTests.class,
        // SearchTests.class,
        ComplexScripts.class,
        SimpleScripts.class
})
public class TestSuite { }