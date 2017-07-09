import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.context.ConfigurableApplicationContext;
import wizardsApplication.Application;

@RunWith(PactRunner.class)
@Provider("wizardsProvider")
@PactFolder("C:\\pacts")
public class ContractsVerifyingTests {
    private static ConfigurableApplicationContext applicationContext;

    @BeforeClass
    public static void setUpService() {
        Application.main(new String[]{});
        applicationContext = Application.applicationContext;
    }

    @AfterClass
    public static void tearDownService() {
        applicationContext.close();
    }

    @State("Harry Potter is alive") // Method will be run before testing interactions that require "Harry Potter is alive" state
    public void toAlivePotterState() {
        /*
        example - save harry potter as the wizard to kill in db
        and change the impl to query the data from the db
        it is preferred to do this with in memory db just for the tests
        */
    }

    @TestTarget
    public final Target target = new HttpTarget(8080);
}