package BaiTap;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_01_Register {
    WebDriver driver;
    By ButtonText = By.xpath("//button[@type='submit']");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "/Users/ios/Desktop/Thăng/SeleniumDriver/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
        public void TC_01_Register_With_Empty_Data () {
            driver.findElement(ButtonText).click();

            WebElement errorName = driver.findElement(By.xpath("//label[@id='txtFirstname-error']"));
            String errorNameText = errorName.getText();
            Assert.assertEquals(errorNameText, "Vui lòng nhập họ tên");

            WebElement errorEmail = driver.findElement(By.xpath("//label[@id='txtEmail-error']"));
            String errorEmailText = errorEmail.getText();
            Assert.assertEquals(errorEmailText, "Vui lòng nhập email");

            WebElement errorEmail2 = driver.findElement(By.xpath("//label[@id='txtCEmail-error']"));
            String errorEmail2Text = errorEmail2.getText();
            Assert.assertEquals(errorEmail2Text, "Vui lòng nhập lại địa chỉ email");

            WebElement errorPass = driver.findElement(By.xpath("//label[@id='txtPassword-error']"));
            String errorPassText = errorPass.getText();
            Assert.assertEquals(errorPassText, "Vui lòng nhập mật khẩu");

            WebElement errorPass2 = driver.findElement(By.xpath("//label[@id='txtCPassword-error']"));
            String errorPass2Text = errorPass2.getText();
            Assert.assertEquals(errorPass2Text, "Vui lòng nhập lại mật khẩu");

            WebElement errorPhone = driver.findElement(By.xpath("//label[@id='txtPhone-error']"));
            String errorPhoneText = errorPhone.getText();
            Assert.assertEquals(errorPhoneText, "Vui lòng nhập số điện thoại.");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    @Test
    public void TC_02_Register_With_Invalid_Email() {
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("abc@123@");
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("abc@123@");

        driver.findElement(ButtonText).click();

        WebElement errorEmail = driver.findElement(By.xpath("//label[@id='txtEmail-error']"));
        WebElement errorEmail2 = driver.findElement(By.xpath("//label[@id='txtCEmail-error']"));

        String errorEmailText = errorEmail.getText();
        String errorEmail2Text = errorEmail2.getText();

        Assert.assertEquals(errorEmailText,"Vui lòng nhập email hợp lệ");
        Assert.assertEquals(errorEmail2Text,"Email nhập lại không đúng");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TC_03_Register_With_Incorrect_Email() {
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("johnwick@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("123@gmail.com");

        driver.findElement(ButtonText).click();

        WebElement errorEmail = driver.findElement(By.xpath("//label[@id='txtCEmail-error']"));
        String errorEmailText = errorEmail.getText();
        Assert.assertEquals(errorEmailText,"Email nhập lại không đúng");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TC_04_Register_With_Less_6_Character() {
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123");
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123");

        driver.findElement(ButtonText).click();

        WebElement errorPass = driver.findElement(By.xpath("//label[@id='txtPassword-error']"));
        WebElement errorPass2 = driver.findElement(By.xpath("//label[@id=\"txtCPassword-error\"]"));

        String errorPassText = errorPass.getText();
        String errorPass2Text = errorPass2.getText();

        Assert.assertEquals(errorPassText,"Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(errorPass2Text,"Mật khẩu phải có ít nhất 6 ký tự");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TC_05_Register_With_Incorrect_Pass() {
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("1234567");
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");

        driver.findElement(ButtonText).click();

        WebElement errorPass = driver.findElement(By.xpath("//label[@id='txtCPassword-error']"));
        String errorPassText = errorPass.getText();
        Assert.assertEquals(errorPassText,"Mật khẩu bạn nhập không khớp");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TC_06_Register_With_Invalid_Phone_Number() {
        By phoneNumberText = By.xpath("//input[@id='txtPhone']");

        driver.findElement(phoneNumberText).sendKeys(".-");
        driver.findElement(ButtonText).click();
        WebElement errorPhone = driver.findElement(By.xpath("//label[@id='txtPhone-error']"));
        String errorPhoneText = errorPhone.getText();
        Assert.assertEquals(errorPhoneText,"Vui lòng nhập con số");

        driver.findElement(phoneNumberText).clear();

        driver.findElement(phoneNumberText).sendKeys("098765");
        WebElement errorPhone2 = driver.findElement(By.xpath("//label[@id='txtPhone-error']"));
        String errorPhone2Text = errorPhone2.getText();
        Assert.assertEquals(errorPhone2Text,"Số điện thoại phải từ 10-11 số.");

        driver.findElement(phoneNumberText).clear();

        driver.findElement(phoneNumberText).sendKeys("122456");
        WebElement errorPhone3 = driver.findElement(By.xpath("//label[@id='txtPhone-error']"));
        String errorPhone3Text = errorPhone3.getText();
        Assert.assertEquals(errorPhone3Text,"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
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