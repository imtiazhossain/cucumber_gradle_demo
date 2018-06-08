package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class Helpers extends BasePage {

    private static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void selectByVisibleText(WebElement element, String value) {
        new Select(element).selectByVisibleText(value);
    }

    static void scrollIntoView(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true)", element);
        sleep();
    }


    private static void waitFor(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void waitForAjaxRequestToBeFinished() {
        int sleepTime = 500;
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        try {
            for (int i = 0; i < 5000 / sleepTime; i++) {
                waitFor(sleepTime / 2);
                if ((Boolean) jse.executeScript(
                        "return document.readyState == 'complete' && window.jQuery != undefined && jQuery.active == 0")) {
                    return;
                }
                waitFor(sleepTime / 2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void type(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }

    static Connection openStagingDBConnection() {
        try {
            return DriverManager.getConnection("jdbc:sqlserver:", "", "");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Couldn't connect to DB", e);
        }
    }

    private static void screenshot() {
        scenario.embed(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png");
    }

    static void log(String log) {
        scenario.write(log);
        waitForAjaxRequestToBeFinished();
        screenshot();
    }
}
