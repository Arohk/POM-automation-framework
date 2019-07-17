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

    // *********Constructor*********
    BasePage(WebDriver driver) {

        this.driver = driver;

        wait = new WebDriverWait(driver, 25);

        PageFactory.initElements(driver, this);

        actions = new Actions(driver);

        this.executor = (JavascriptExecutor) this.driver;

        driver.manage().timeouts().implicitlyWait(11, TimeUnit.SECONDS);

    }

    // Create object of SimpleDateFormat class and decide the format
    DateFormat dateFormat = new SimpleDateFormat("ddMMHHmmss");
    // get current date time with Date()
    Date date = new Date();
    // Now format the date
    String currentDate = dateFormat.format(date);

    // *********BasePage Methods*********

    /**
     * Clicks an element with a default wait time of 40s.
     *
     * @param element - Element to be clicked.
     */
    public void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    /**
     * Clicks an element with a default wait time of 40s after it waits for another
     * element to become clickable before that (usable for buttons with glow
     * animations)
     *
     * @param elementToBeClickable - element, for which the method waits to be available.
     * @param elementToBeClicked   - element, which is actually clicked after.
     * @throws InterruptedException
     */
    public void clickGlow(WebElement elementToBeClickable, WebElement elementToBeClicked) throws InterruptedException {
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(elementToBeClickable));
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(elementToBeClicked)).click();
        Thread.sleep(1000);
    }

    /**
     * Tries to click an element for a custom period of time, returns a message if
     * element is not found and continues the test.
     *
     * @param element              - Element to be clicked.
     * @param secondsToWait        - Time available to try and click the element.
     * @param conditionsNotMetText - Text message returned to console if the click is not successful.
     */
    public void clickTryCatch(WebElement element, int secondsToWait, String conditionsNotMetText) {
        waitX = new WebDriverWait(this.driver, secondsToWait);
        try {
            waitX.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            System.out.println(conditionsNotMetText);
        }
    }

    /**
     * Click an element surrounded with custom wait time and a custom Thread.sleep
     * time.
     *
     * @param element                 - Element to be clicked.
     * @param secondsToWait           - Time available for the click.
     * @param threadSleepMilliseconds - Mandatory timeout before and after the click.
     */
    public void clickSleep(WebElement element, int secondsToWait, int threadSleepMilliseconds)
            throws InterruptedException {
//		executor.executeScript("arguments[0].setAttribute('style', 'border: 9px dashed #ff9999;');", element);
        waitX = new WebDriverWait(driver, secondsToWait);
        Thread.sleep(threadSleepMilliseconds);
        waitX.until(ExpectedConditions.elementToBeClickable(element)).click();
        Thread.sleep(threadSleepMilliseconds);


    }

    public void javaScriptExecutorInteraction(String javaScript, WebElement element) {
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
     * Reads text from a field (This method is used in the assertEquals method).
     *
     * @param element - Element, from which the text will be read.
     * @return - Returns a String of text to the Method.
     */
    public String readText(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return element.getText();
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
     * Accepts an alert generated by the browser
     * @throws InterruptedException
     */
    public void acceptAlert() throws InterruptedException {
        Thread.sleep(4500);
        Alert alertDialog = driver.switchTo().alert();
        alertDialog.accept();
    }

    /**
     * Switches to the passed iFrame
     * @param frame - passed frame
     * @throws InterruptedException
     */
    public void switchToFrame(String frame) throws InterruptedException {
        Thread.sleep(1500);
        driver.switchTo().frame(frame);
        Thread.sleep(1500);
    }

    /**
     * Custom asser that Asserts that an element contains the String of expectedText that is entered
     * with the actual one found from "BasePage.java"->read text method.
     *
     * @param element      - Element, which text is to be returned by the read text method
     *                     and asserted with the expected text.
     * @param expectedText - Expected text that is to be found in the application.
     */
    public void assertText(WebElement element, String expectedText) {
        wait.until(ExpectedConditions.visibilityOf(element));
        Assert.assertEquals(readText(element), expectedText);
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
