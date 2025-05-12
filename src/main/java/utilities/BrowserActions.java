package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserActions {

    public static WebDriver driver;
    public static void initializeDriver(Browsers browser) {
        try{
            switch (browser){
                case chrome:
                    driver = new ChromeDriver();
                    break;

                case firefox:
                    driver= new FirefoxDriver();
                    break;

                case InternetExplorer:
                    driver= new InternetExplorerDriver();
                    break;

                case edge:
                    driver= new EdgeDriver();
                    break;

                case Safari:
                    driver= new SafariDriver();
                    break;
            }
        }
        catch(Exception exception){
            throw new RuntimeException("Failed to initialize the driver in the " + browser, exception);
        }
    }

    public static void navigateToPage(String url){
        driver.get(url);
    }

    public static void maximizeWindow(){
        driver.manage().window().maximize();
    }

    public static void minimizeWindow(){
        driver.manage().window().minimize();
    }

    public static void deleteAllCookies(){
        driver.manage().deleteAllCookies();
    }

    public static void closeDriver(){
        driver.close();
    }

    public static void teardown(){
        driver.quit();
    }

    public enum Browsers{
        chrome,
        firefox,
        InternetExplorer,
        edge,
        Safari
    }
}
