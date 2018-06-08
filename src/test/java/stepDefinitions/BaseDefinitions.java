package stepDefinitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.BasePage;

import static pageObjects.BasePage.driver;

public class BaseDefinitions {

    @Before()
    public void setUp(Scenario scenario) {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
//      BasePage.driver = new GifWebDriver(new ChromeDriver());
        BasePage.scenario = scenario;
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
