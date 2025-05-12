package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utilities.BrowserActions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static utilities.BrowserActions.*;

public class Hooks {
    public Properties property;

    @Before
    public void setup() throws IOException {
        FileReader file = new FileReader("src/test/config/config.properties");
        property = new Properties();
        property.load(file);
        initializeDriver(BrowserActions.Browsers.chrome);
        navigateToPage(property.getProperty("appProductionUrl"));
        maximizeWindow();
    }

    @After
    public void close(){
        // teardown();
    }

}
