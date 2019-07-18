package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Nikolay
 */
public class BasePage {

    WebDriver driver;
    WebDriverWait wait;
    WebDriverWait waitX;
    Actions actions;
    JavascriptExecutor executor;

    BasePage(WebDriver driver) {

        this.driver = driver;

        wait = new WebDriverWait(driver, 25);

        PageFactory.initElements(driver, this);

        actions = new Actions(driver);

        this.executor = (JavascriptExecutor) this.driver;

        driver.manage().timeouts().implicitlyWait(11, TimeUnit.SECONDS);

    }

    // *********BasePage Methods*********

    /**
     * Clicks an element with a default wait time of 25s.
     *
     * @param element - Element to be clicked.
     */
    public void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }


    public void javaScriptExecutorInteraction(String javaScript, WebElement element) throws InterruptedException {
        Thread.sleep(1500);
        executor.executeScript(javaScript, element);
    }

    /**
     * Move to element with the Actions class
     * @param element - Element to be interacted
     */
    public void moveToElementActions(WebElement element) {
        actions.moveToElement(element).perform();
    }

    /**
     * Writes text to a text field.
     *
     * @param element - Element for text to be sent to.
     * @param text    - Text to be sent to the element.
     */
    public void writeText(WebElement element, String text) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(text);
    }

    /**
     * Clears text from a field.
     * @param element - Element that needs to be cleared of text
     */
    public void clearText(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).clear();
    }

    /**
     * navigates to the passed URL
     * @param url - Url to navigate
     */
    public void navigateToUrl(String url) {
        driver.get(url);
    }

    /**
     * Sets a custom window position
     *
     * @param startingPositionX - Starting point X axis
     * @param startingPositionY - Starting point Y axis
     * @param pxDimensionWidth  - Width in pixels
     * @param pxDimensionHeight - Height in pixels
     */
    public void setPosition(int startingPositionX, int startingPositionY, int pxDimensionWidth, int pxDimensionHeight) {
        driver.manage().window().setPosition(new Point(startingPositionX, startingPositionY));
        driver.manage().window().setSize(new Dimension(pxDimensionWidth, pxDimensionHeight));
    }

    /**
     * Checks if a passed element is displayed and sets the method to true or false
     * depending on the element being displayed or not
     * @param element - element to check if it's being displayed
     * @return - returns true if the element is displayed, false if not.
     */
    public boolean isElementDisplayed(WebElement element) {
        try {
            element.isDisplayed();
            return true;
        } catch (Exception e) {
            System.out.println("element is not displayed");
            return false;
        }
    }

}
