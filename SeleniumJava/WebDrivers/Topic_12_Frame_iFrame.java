package WebDrivers;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Frame_iFrame {
    WebDriver driver;
    Actions action;
    JavascriptExecutor jsExcutor;

    @BeforeClass
    public void beforeClass () {
        System.setProperty("webdriver.chrome.driver", "/Users/ios/Downloads/SeleniumDriver/chromedriver");
        driver = new ChromeDriver();
        action = new Actions(driver);
        jsExcutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_iFrame () {
        driver.get("https://kyna.vn/");

        // Switch into iframe has element
        driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='fanpage ']//iframe")));

        String kynaFanpageLike = driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText();
        System.out.println(kynaFanpageLike);

        // Back to parent page
        driver.switchTo().defaultContent();

        //Login button in parent page
        Assert.assertTrue(driver.findElement(By.xpath("//a[@class='login-btn']")).isDisplayed());

        // Click to chat iframe
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='cs_chat_iframe']")));

        driver.findElement(By.xpath("//div[@class='border_overlay meshim_widget_widgets_BorderOverlay']")).click();

        // Fill data
        driver.findElement(By.xpath("//input[@ng-model='login.username']")).sendKeys("John Wick");
        driver.findElement(By.xpath("//input[@ng-model='login.phone']")).sendKeys("0123456789");
        driver.findElement(By.xpath("//select[@id='serviceSelect']")).click();
        driver.findElement(By.xpath("//div[@class='meshim_widget_widgets_Form generated_form']//option[@value='60021729']")).click();
        driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("Hi, I am Lucifer Samael Morningstar");
        driver.findElement(By.xpath("//input[@value='Gửi tin nhắn']")).click();

        // Verify all data just filled
        String nameText = driver.findElement(By.xpath("//div[@class='floater_inner_seriously']//label[@class='logged_in_name ng-binding']")).getText();
        String phoneText = driver.findElement(By.xpath("//div[@class='floater_inner_seriously']//label[@class='logged_in_phone ng-binding']")).getText();
        String messengerText = driver.findElement(By.xpath("//textarea[@name='message']")).getText();

        Assert.assertEquals(nameText, "John Wick");
        Assert.assertEquals(phoneText, "0123456789");
        Assert.assertEquals(messengerText, "Hi, I am Lucifer Samael Morningstar");
    }

    @Test
    public void TC_02_Excel () {
        driver.get("https://kyna.vn/");

        // Fill "Excel" in search bar
        driver.findElement(By.xpath("//input[@id='live-search-bar']")).sendKeys("Excel");
        driver.findElement(By.xpath("//button[@class='search-button']")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify all page contain Excel
        List<WebElement> keywordExcels = driver.findElements(By.xpath("//div[@class='content']"));

        for (WebElement keywordExcel:keywordExcels) {
            Assert.assertTrue(keywordExcel.getText().contains("Excel"));
        }
    }

    @Test
    public void TC_03_Frame () {
        driver.get("https://netbanking.hdfcbank.com/netbanking/");

        driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='login_page']")));

        driver.findElement(By.xpath("//input[@class='form-control text-muted']")).sendKeys("rip9x");
        driver.findElement(By.xpath("//a[@class='btn btn-primary login-btn']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='fldPasswordDispId']")).isDisplayed());

        driver.findElement(By.xpath("//div[@class='footer-btm']/a[text()='Terms and Conditions']")).click();
    }

    @AfterClass
    public void afterClass () {
        driver.quit();
    }
}
