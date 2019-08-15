package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RestaurantPage extends BasePage {

    // ***Constructor***
    public RestaurantPage(WebDriver driver) {
        super(driver);
    }

    // ***Elements***

    @FindBy(xpath = "//span[starts-with(text(), 'Свинска вратна пържола')]")
    WebElement mealName;

    @FindBy(xpath = "//button[starts-with(@class,\"cartbutton-button\")]")
    WebElement addToCartButton;

    @FindBy(xpath = "//button[@class=\"basket__order-button cartbutton-button\"]")
    WebElement completeOrderButton;


    // ***Implementation***

    public void orderFromRestaurant() throws InterruptedException {
        javaScriptExecutorInteraction("arguments[0].click();", mealName);
        javaScriptExecutorInteraction("arguments[0].click();", addToCartButton);
        javaScriptExecutorInteraction("arguments[0].click();", completeOrderButton);
    }

}
