package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import static pageObjects.BasePage.driver;
import static pageObjects.Helpers.log;

public class GooglePage {

    @FindBy(name = "q")
    private WebElement searchField;
    @FindBy(linkText = "Google")
    private WebElement googleLink;

    public GooglePage(WebDriver driver) {
        BasePage.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(BasePage.driver, 20), this);
    }

    public void goToGooglePage() {
        driver.get("https://www.google.com/");
        log("Go to https://www.google.com/");
    }

    public void search(String searchTerm) {
        searchField.sendKeys(searchTerm);
        searchField.sendKeys(Keys.ENTER);
        log("Search for " + searchTerm);
    }

    public void verifyLink() {
        googleLink.isDisplayed();
    }
}
