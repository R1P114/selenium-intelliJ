package WebDrivers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Web_Browser {
    WebDriver driver;

    @BeforeClass
    public void beforeClass () {
        System.setProperty("webdriver.chrome.driver", "/Users/ios/Desktop/Thăng/SeleniumDriver/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://live.techpanda.org/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Verify_Url () {
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        String loginPageUrl = driver.getCurrentUrl();

        Assert.assertEquals(loginPageUrl,"http://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        String createAccountButton = driver.getCurrentUrl();

        Assert.assertEquals(createAccountButton, "http://live.techpanda.org/index.php/customer/account/create/");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TC_02_Verify_Title () {
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        String titleLoginPage = driver.getTitle();

        Assert.assertEquals(titleLoginPage,"Customer Login");

        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        String titleRegisterPage = driver.getTitle();

        Assert.assertEquals(titleRegisterPage,"Create New Customer Account");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TC_03_Navigate_Function () {
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        String urlRegisterPage = driver.getCurrentUrl();

        Assert.assertEquals(urlRegisterPage,"http://live.techpanda.org/index.php/customer/account/create/");

        driver.navigate().back();

        String urlLoginPage = driver.getCurrentUrl();

        Assert.assertEquals(urlLoginPage,"http://live.techpanda.org/index.php/customer/account/login/");

        driver.navigate().forward();

        String titleRegisterPage = driver.getTitle();

        Assert.assertEquals(titleRegisterPage,"Create New Customer Account");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TC_Get_Page_Source_Code () {
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        String loginPage = driver.getPageSource();

        Assert.assertTrue(loginPage.contains("Login or Create an Account"));

        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        String registerPage = driver.getPageSource();

        Assert.assertTrue(registerPage.contains("Create an Account"));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void afterClass () {
        driver.quit();
    }
}
