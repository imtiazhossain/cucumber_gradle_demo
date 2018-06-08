package stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pageObjects.BasePage;
import pageObjects.GooglePage;

public class GoogleDefs {

    private GooglePage googlePage;

    @Given("^I navigate to google\\.com$")
    public void i_navigate_to_google_com() {
        googlePage = new GooglePage(BasePage.driver);
        googlePage.goToGooglePage();
    }

    @Given("^I search for something$")
    public void i_search_for_something() {
        googlePage.search("Google");
    }

    @Then("^Search should show$")
    public void search_should_show() {
        googlePage.verifyLink();
    }
}
