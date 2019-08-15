package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AddressPage extends BasePage {

    // ***Constructor***
    public AddressPage(WebDriver driver) {
        super(driver);
    }

    // ***Elements***

    @FindBy(id = "iaddress")
    WebElement addressField;

    @FindBy(id = "itown")
    WebElement townField;

    @FindBy(id = "ientrance")
    WebElement entranceField;

    @FindBy(id = "isurname")
    WebElement nameField;

    @FindBy(id = "iemail")
    WebElement emailField;

    @FindBy(id = "iphonenumber")
    WebElement phoneField;

    @FindBy(xpath = "//div[@class=\"checkout-orderbutton-btn\"]")
    WebElement submitOrderButton;

    @FindBy(xpath = "//span[@class=\"order-purchaseid\"]")
    WebElement orderPurchaseId;


    // ***Implementation***

    public void completeOrder() throws InterruptedException {

        clearText(addressField);
        writeText(addressField, readTextFromFile("Resources/StaticData/address.txt"));

        clearText(townField);
        writeText(townField, readTextFromFile("Resources/StaticData/town.txt"));

        clearText(entranceField);
        writeText(entranceField, readTextFromFile("Resources/StaticData/entrance.txt"));

        clearText(nameField);
        writeText(nameField, readTextFromFile("Resources/StaticData/name.txt"));

        clearText(emailField);
        writeText(emailField, readTextFromFile("Resources/StaticData/e-mail.txt"));

        clearText(phoneField);
        writeText(phoneField, readTextFromFile("Resources/StaticData/phone.txt"));

//        Select the asap option for delivery
        Select  deliveryDropDown = new Select(driver.findElement(By.id("ideliverytime")));
        deliveryDropDown.selectByIndex(1);

        javaScriptExecutorInteraction("arguments[0].scrollIntoView(true);", submitOrderButton);
        click(submitOrderButton);

        javaScriptExecutorInteraction("arguments[0].scrollIntoView(true);", orderPurchaseId);
        String orderID = orderPurchaseId.getText();

        System.out.println("Your order id is: " + orderID);
    }

    public String readTextFromFile(String path) {
        String data = "null";
        try {
            data = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

}
