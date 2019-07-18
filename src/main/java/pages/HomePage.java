package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class HomePage extends BasePage {

    // ***Constructor***
    public HomePage(WebDriver driver) {
        super(driver);
    }

    // ***Elements***
    @FindBy(xpath = "//div[@class=\"notice-container\"]/a[@id='privacybanner']")
    WebElement closeCookieNotice;

    // Side menu button locator
    @FindBy(xpath = "//div[@class='myaccount']/button[@class='menu button-myaccount userlogin']")
    WebElement sideMenuButton;

    @FindBy(id = "imysearchstring")
    WebElement searchBar;

    @FindBy(xpath = "//div[@class=\"autoCompleteDropDownContent\"]//a[@data-name=\"НДК, София\"]")
    WebElement resultNDKSofia;

    @FindBy(id = "irestaurantN7NQNOQ")
    WebElement otechestvoRestaurant;

    // ***Implementations***

    public void findRestaurant() throws InterruptedException {
        setPosition(0, 0, 1920, 1080);
        navigateToUrl("https://www.takeaway.com/bg/");

        if (isElementDisplayed(closeCookieNotice) == true) {
            click(closeCookieNotice);
        }
        click(searchBar);
        clearText(searchBar);
        writeText(searchBar, "НДК, София");
        click(resultNDKSofia);

        Thread.sleep(2500);
//        Assert that at least 6 restaurants are available in the list
        List<WebElement> restaurantsCount = driver.findElements(By.xpath("//div[@class=\"restaurants restaurantlist\"]//div[@class=\"restaurant \"]"));
        System.out.println("The Number of restaurants on the page is: " + restaurantsCount.size());
        Assert.assertTrue(restaurantsCount.size() >= 6);

        javaScriptExecutorInteraction("arguments[0].scrollIntoView(true);", otechestvoRestaurant);

        javaScriptExecutorInteraction("arguments[0].click();", otechestvoRestaurant);

    }

}
