package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.*;
import homeworks.*;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        ComplexScripts.class,
        SimpleScripts.class
        // ArticleTests.class,
        // ChangeAppConditionTests.class,
        // GetStartedTest.class,
        // MyListTests.class,
        // SearchTests.class
})
public class TestSuite { }