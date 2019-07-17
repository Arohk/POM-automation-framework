package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    // ***Constructor***

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // ***Elements***

    // Side menu button locator
    @FindBy(xpath = "//div[@class='myaccount']/button[@class='menu button-myaccount userlogin']")
    WebElement sideMenuButton;

    // ***Implementations***

    public void LoginWithTestUser() {

        setPosition(0, 0, 500, 500);
        navigateToUrl("https://www.takeaway.com/bg/");
        click(sideMenuButton);

    }
}
