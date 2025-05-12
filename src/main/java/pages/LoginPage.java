package pages;

import org.openqa.selenium.WebElement;
import utilities.UIActions;

import java.time.Duration;

public class LoginPage extends UIActions {

    public void setUsername(String username) throws Exception {
        implicitWait(10);
        WebElement userName = waitUntil(locateElement(LocatorType.xpath,"//input[@name='username']"),ExpectedConditionsEnum.visibilityOfElement, Duration.ofSeconds(10));
        fill(userName, username);
    }
    public void setPassword(String pass) throws Exception {
        WebElement password = find(LocatorType.name, "password");
        fill(password,pass);
    }
    public void clickOnLogin() throws Exception {
       final WebElement loginBtn = find(LocatorType.xpath, "//button[@type='submit']");
        clickOnElement(loginBtn);
    }
}
