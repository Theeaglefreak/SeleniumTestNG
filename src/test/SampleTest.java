package test;

import main.java.PageEvents.HomePage;
import org.testng.annotations.Test;

public class SampleTest extends BaseTest {

    @Test
    public void login()
    {
        logger= extent.createTest("homepage");
        HomePage homepage= new HomePage();
        homepage.login();
    }
}
