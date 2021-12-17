package Demo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Linkhay {
    WebDriver driver;
    JavascriptExecutor jsExcutor;

    @BeforeClass
    public void beforeClass () {
        System.setProperty("webdriver.chrome.driver", "/Users/ios/Downloads/SeleniumDriver/chromedriver");
        driver = new ChromeDriver();
        jsExcutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Login () {
        driver.get("https://linkhay.com/link/stream/hot");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath("//a[@class='link-nologin mrk-login']")).click();

        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='login_box_frame']")));

        By loginPopup = By.xpath("//input[@name='account']");

        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
    }

    @AfterClass
    public void afterClass () {
        driver.quit();
    }
}
