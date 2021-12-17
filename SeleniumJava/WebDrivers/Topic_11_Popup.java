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

public class Topic_11_Popup {
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
    public void TC_01_Fixed_Popup () {
        driver.get("https://ngoaingu24h.vn/");

        By loginPopup = By.xpath("//div[@id='modal-login-v1']");

        Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());

        driver.findElement(By.xpath("//button[@class='login_ icon-before']")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

        driver.findElement(By.xpath("//input[@id='account-input']")).sendKeys("Lucifer");
        driver.findElement(By.xpath("//input[@id='password-input']")).sendKeys("123456");
        driver.findElement(By.xpath("//button[@class='btn-v1 btn-login-v1 buttonLoading']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Tài khoản không tồn tại!']")).getText(), "Tài khoản không tồn tại!");
    }

    @Test
    public void TC_02_Random_Popup_In_DOM () {
        driver.get("https://blog.testproject.io/");
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        By bluePopup = By.xpath("//div[@class='mailch-wrap']");

        // If bluePopup is displayed => close. If bluePopup is not => move to next: write Selenium
        if (driver.findElement(bluePopup).isDisplayed())
            driver.findElement(By.xpath("//div[@id='close-mailch']")).click();

        Assert.assertFalse(driver.findElement(bluePopup).isDisplayed());

        // Next step
        driver.findElement(By.xpath("//section[@id='search-2']//input[@class='search-field']")).sendKeys("Selenium");
        driver.findElement(By.xpath("//section[@id='search-2']//span[@class='glass']")).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify all title contain keyword Selenium
        List<WebElement> keywordSeles = driver.findElements(By.xpath("//h3[@class='post-title']/a"));
        // System.out.println("All Selenium = " + keywordSeles.size());

        for (WebElement keyWord:keywordSeles) {
            Assert.assertTrue(keyWord.getText().contains("Selenium"));
        }
    }

    @Test
    public void TC_03_Random_Popup_Not_In_DOM () {
        driver.get("https://shopee.vn/");
        By shopeePopupBy = By.xpath("//div[@class='shopee-popup__container']");

        List<WebElement> shopeePopupElement = driver.findElements(shopeePopupBy);

        if (shopeePopupElement.size() > 0 && shopeePopupElement.get(0).isDisplayed() ) {
            System.out.println("Popup hiển thị r");
            driver.findElement(By.xpath("//div[@class='shopee-popup__close-btn']")).click();
            System.out.println("Số popup đang hiển thị = " + shopeePopupElement.size());
        }

        // Assert.assertEquals(shopeePopupElement.size(), 1);

        driver.findElement(By.xpath("//input[@class='shopee-searchbar-input__input']")).sendKeys("iphone 13");
        driver.findElement(By.xpath("//button[@class='btn btn-solid-primary btn--s btn--inline shopee-searchbar__search-button']")).click();
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
