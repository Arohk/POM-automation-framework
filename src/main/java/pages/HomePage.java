package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    @FindBy(xpath = "//div[@class=\"autoCompleteDropDownContent\"]//a[@data-name=\"Ндк, София\"]")
    WebElement resultNDKSofia;

    @FindBy(id = "irestaurantN10Q3R7")
    WebElement aleHouseID;

    @FindBy(xpath = "//span[contains(text(), 'Телешки наденички')]")
    WebElement mealName;

    @FindBy(xpath = "//*[@id=\"ibasket\"]//button[@class=\"basket__order-button cartbutton-button\"]")
    WebElement orderButton;




    // ***Implementations***

    public void LoginWithTestUserAndDoStuff() throws InterruptedException {
        setPosition(0, 0, 1500, 1500);
        navigateToUrl("https://www.takeaway.com/bg/");

        if (isElementDisplayed(closeCookieNotice) == true){
            click(closeCookieNotice);
        }

        click(searchBar);
        writeText(searchBar, "НДК София");
        click(resultNDKSofia);
        moveToElementActions(aleHouseID);
        click(aleHouseID);
        moveToElementActions(mealName);
        click(mealName);
        click(orderButton);




        Thread.sleep(4500);

    }

}
