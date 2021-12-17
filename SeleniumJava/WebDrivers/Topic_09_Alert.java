package WebDrivers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Alert {
    WebDriver driver;
    WebDriverWait expliciWait;
    org.openqa.selenium.Alert alert;

    @BeforeClass
    public void beforeClass () {
        System.setProperty("webdriver.chrome.driver", "/Users/ios/Downloads/SeleniumDriver/chromedriver");
        driver = new ChromeDriver();
        expliciWait = new WebDriverWait(driver,30);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Accept_Alert () {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[@onclick='jsAlert()']")).click();

        // First way, wait to alert show up in 15s
        // expliciWait.until(ExpectedConditions.alertIsPresent());

        // Switch to an alert
        // alert = driver.switchTo().alert();

        // Or, in second way, we will wait + switch to an alert
        alert = expliciWait.until(ExpectedConditions.alertIsPresent());

        // Accet alert, then alert will disappear
        // alert.accept();

        // Cancel alert: alert.dismiss
        // Get text: alert.getText()
        // System.out.println(alert.getText());
        // Send value: alert.sendKeys

        // Verify text in alert
        Assert.assertEquals(alert.getText(), "I am a JS Alert");

        alert.accept();

        By messageBox = By.xpath("//p[@id='result']");
        Assert.assertEquals(driver.findElement(messageBox).getText(), "You clicked an alert successfully");
    }

    @Test
    public void TC_02_Prompt_Alert () {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();

        alert = expliciWait.until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(alert.getText(), "I am a JS prompt");

        String alertName = "Lucifer";

        alert.sendKeys(alertName);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        alert.accept();

        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You entered: " + alertName);
    }

    @Test
    public void TC_03_Confirm_Alert () {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();

        alert = expliciWait.until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(alert.getText(), "I am a JS Confirm");

        alert.accept();

        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked: Ok");
    }

    @Test
    public void TC_04_Authentication_Alert () {
        String username = "admin";
        String password = "admin";
        String url = "http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth";
        driver.get(url);
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
    }

    public interface Alert {
        void dismiss();

        void accpet();

        String getText();

        void sendKeys(String keysToSend);
    }

    @AfterClass
    public void afterClass () {
        driver.quit();
    }
}
