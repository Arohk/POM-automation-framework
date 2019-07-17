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
import pages.BookOfKnowledgePage;
import pages.MainPage;
import pages.SettingsPage;

public class BaseTest {

    private WebDriver driver;

    MainPage objMainPage;
    SettingsPage objSettingsPage;
    BookOfKnowledgePage objBookOfKnowledgePage;

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
                System.setProperty("Webdriver.gecko.driver", "Resources/Unix/geckodriver");
            }

            // add desired capabilities
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addPreference("media.volume_scale", "0.0");
            firefoxOptions.addPreference("marionette.enabled", true);
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
            options.addArguments("mute-audio");
            options.addArguments("disable-infobars");
//            options.addArguments("--headless");
            driver = new ChromeDriver(options);
        }

        // Create objects for the PageClasses, pass driver
        objMainPage = new MainPage(driver);
        objSettingsPage = new SettingsPage(driver);
        objBookOfKnowledgePage = new BookOfKnowledgePage(driver);

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
