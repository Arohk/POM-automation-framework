package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import pages.AddressPage;
import pages.HomePage;
import pages.RestaurantPage;

public class BaseTest {

    private WebDriver driver;
    HomePage objHomePage;
    RestaurantPage objRestaurantPage;
    AddressPage objAddressPage;


    /**
     * Set Up method that launches a browser instance with desired capabilities depending on the OS the script is run from.
     * @param browser - Browser parameter that is defined in the TestNG xml file.
     */
    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("Chrome") String browser) {

        // initialize firefox & chrome setups, Chrome is default.
        if (browser.equalsIgnoreCase("firefox")) {

            // Save operating system to determine web driver (Windows/Linux)
            String operatingSystem = System.getProperty("os.name");
            if (operatingSystem.startsWith("Windows")) {
                System.setProperty("webdriver.gecko.driver", "Resources/geckodriver.exe");
            } else {
                System.setProperty("webdriver.gecko.driver", "Resources/Unix/geckodriver");
            }

            // add desired capabilities
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addPreference("marionette.enabled", true);
            firefoxOptions.addPreference("geo.enabled", true);
            driver = new FirefoxDriver(firefoxOptions);


        } else if (browser.equalsIgnoreCase("chrome")) {

            // Save operating system to determine web driver (Windows/Linux)
            String operatingSystem = System.getProperty("os.name");
            if (operatingSystem.startsWith("Windows")) {
                System.setProperty("webdriver.chrome.driver", "Resources/chromedriver.exe");
            } else {
                System.setProperty("Webdriver.chrome.driver", "Resources/Unix/chromedriver");
            }

            // add desired capabilities
            ChromeOptions options = new ChromeOptions();
            options.addArguments("disable-infobars");
//            options.addArguments("--headless");
            driver = new ChromeDriver(options);
        }

        // Create objects for the PageClasses, passes the driver
        objHomePage = new HomePage(driver);
        objRestaurantPage = new RestaurantPage(driver);
        objAddressPage = new AddressPage(driver);
    }

    /**
     * Tear Down method to kill the driver's instance once a test is finished.
     */
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
