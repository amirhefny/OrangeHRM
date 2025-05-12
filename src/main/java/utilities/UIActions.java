package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static utilities.BrowserActions.driver;

public class UIActions {

    protected WebElement find(LocatorType locator, String selector){
        return driver.findElement(locateElement(locator,selector));
    }

    protected void fill(WebElement element, String value) throws Exception {
        if(element != null)
            try {
                element.clear();
                element.sendKeys(value);
            }
            catch (Exception exception){
                String message = String.format("Couldn't enter the text on this element: %s because of the exception: %s", element, exception.getMessage());
                throw new Exception(message);
            }

    }

    protected void clearValue(WebElement element){
        element.clear();
    }

    protected String getElementText(WebElement element) {
        return element.getText();
    }

    protected void clickOnElement(WebElement element) throws Exception {
        if(element != null)
            try {
        element.click();
            }
        catch (Exception exception){
            String message = String.format("Couldn't click on button element: %s because of the exception: %s", element, exception.getMessage());
            throw new Exception(message);
            }

    }

    protected void clickOnElementMultipleTimes(WebElement element, int numberOfClicks) throws Exception {
        for (int i=1 ; i<=numberOfClicks ; i++){
            clickOnElement(element);
        }
    }

    public void implicitWait(int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    public WebElement waitUntil(By locator, ExpectedConditionsEnum condition, Duration time) {
        try {
            WebElement element = null;
            switch (condition) {
                case presenceOfElement:
                    element = (new WebDriverWait(driver,time)).until(ExpectedConditions.presenceOfElementLocated(locator));
                    return element;
                case elementToBeClickable:
                    element = (new WebDriverWait(driver, time)).until(ExpectedConditions.elementToBeClickable(locator));
                    return element;
                case visibilityOfElement:
                    element = (new WebDriverWait(driver, time)).until(ExpectedConditions.visibilityOfElementLocated(locator));
                    return element;
                default:
                    element = null;
            }
            return element ;
        } catch (Exception e) {
            throw new RuntimeException("Failed during explicit wait: " + e.getMessage());
        }
    }

    public By locateElement(LocatorType locator, String selector){
        switch (locator){
            case id :
                return new By.ById(selector);
            case cssSelector:
                return new By.ByCssSelector(selector);
            case xpath:
                return new By.ByXPath(selector);
            case tagName:
                return new By.ByTagName(selector);
            case linkText:
                return new By.ByLinkText(selector);
            case className:
                return new By.ByClassName(selector);
            case name:
                return new By.ByName(selector);
            case partialLinkText:
                return new By.ByPartialLinkText(selector);
            default:
                return null;
        }
    }

    public enum LocatorType {
        xpath,
        id,
        className,
        name,
        cssSelector,
        tagName,
        linkText,
        partialLinkText
    }

    public enum ExpectedConditionsEnum {
        elementToBeClickable,
        presenceOfElement,
        visibilityOfElement
    }
}
