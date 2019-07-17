package tests;

import org.testng.annotations.Test;

public class sampleTest extends BaseTest {

    @Test
    public void sampleTestMethod() throws InterruptedException {
        objHomePage.LoginWithTestUserAndDoStuff();

    }

}
