package WebDrivers;

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

public class Topic_02_Login {
    WebDriver driver;
    String email;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "/Users/ios/Desktop/Thăng/SeleniumDriver/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://live.demoguru99.com/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        email = "lucifer" + randomData() + "@gmail.com";
    }

    @Test
    public void TC_01_Login_With_Empty_Email_And_Password() {
        // Tìm nút My Account rồi ấn để tới trang đăng nhập
        driver.findElement(By.xpath("//div[@class='footer']//a[@title=\"My Account\"]")).click();

        // Không điền Username/Pass

        // Click Login Button
        driver.findElement(By.xpath("//div[@class=\"buttons-set\"]//button[@type=\"submit\"]")).click();

        // Tìm error msg tại Email Add
        driver.findElement(By.xpath("//div[@id=\"advice-required-entry-email\"]"));
        // Đặt tên cho error msg
        WebElement errorEmailAdd = driver.findElement(By.xpath("//div[@id=\"advice-required-entry-email\"]"));
        // Get text của error msg
        String errorEmailAddText = errorEmailAdd.getText();
        // So sánh text được get với text mình mong muốn
        Assert.assertEquals(errorEmailAddText, "This is a required field.");

        // Tìm error msg tại Pass
        driver.findElement(By.xpath("//div[@id=\"advice-required-entry-pass\"]"));
        // Đặt tên cho error pass
        WebElement errorPass = driver.findElement(By.xpath("//div[@id=\"advice-required-entry-pass\"]"));
        // Get text của error pass
        String errorPassText = errorPass.getText();
        // So sánh text được get với text mình mong muốn
        Assert.assertEquals(errorPassText, "This is a required field.");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TC_02_Login_With_Invalid_Email() {
        // Tìm nút My Account rồi ấn để tới trang đăng nhập
        driver.findElement(By.xpath("//div[@class='footer']//a[@title=\"My Account\"]")).click();

        // Tìm box email rồi nhập
        driver.findElement(By.xpath("//input[@name=\"login[username]\"]")).sendKeys("12345@1234.1234");

        // Tìm box pass rồi nhập
        driver.findElement(By.xpath("//input[@type=\"password\"]")).sendKeys("123456");

        // Tìm nút Log in rồi ấn
        driver.findElement(By.xpath("//button[@title=\"Login\"]")).click();

        // Tìm error msg tại Email Add
        driver.findElement(By.xpath("//div[@class=\"validation-advice\"]"));

        // Đặt tên cho error msg
        WebElement errorEmailAddress = driver.findElement(By.xpath("//div[@class=\"validation-advice\"]"));

        // Get text chi error msg
        String errorEmailAddText = errorEmailAddress.getText();

        // So sánh text đc get với text mình muốn
        Assert.assertEquals(errorEmailAddText, "Please enter a valid email address. For example johndoe@domain.com.");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TC_03_Login_With_Password_Less_6_Char() {
        // Tìm nút My Account rồi ấn để tới trang đăng nhập
        driver.findElement(By.xpath("//div[@class='footer']//a[@title=\"My Account\"]")).click();

        // Tìm ô email rồi nhập
        driver.findElement(By.xpath("//div[@class=\"content fieldset\"]//input[@type='email']")).sendKeys("johnwick@gmail.com");

        // Tìm ô pass rồi nhập 123
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("123");

        // Tìm ô Login rồi ấn
        driver.findElement(By.xpath("//div[@class=\"buttons-set\"]//button[@type='submit']")).click();

        // Tìm error msg tại Pass
        driver.findElement(By.xpath("//div[@id=\"advice-validate-password-pass\"]"));

        // Đặt tên cho error msg
        WebElement errorPass = driver.findElement(By.xpath("//div[@id=\"advice-validate-password-pass\"]"));

        // Get text cho error msg
        String errorPassText = errorPass.getText();

        // So sánh error msg với text mình muốn
        Assert.assertEquals(errorPassText, "Please enter 6 or more characters without leading or trailing spaces.");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TC_04_Login_With_Incorrect_Pass() {
        // Tìm nút My Account rồi ấn để tới trang đăng nhập
        driver.findElement(By.xpath("//div[@class='footer']//a[@title=\"My Account\"]")).click();

        // Tìm ô email rồi nhập
        driver.findElement(By.xpath("//div[@class=\"content fieldset\"]//input[@type='email']")).sendKeys("johnwick@gmail.com");

        // Tìm ô pass rồi nhập 123123123
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("123123123");

        // Tìm ô Login rồi ấn
        driver.findElement(By.xpath("//div[@class=\"buttons-set\"]//button[@type='submit']")).click();

        // Tìm error msg: Invalid login or password
        driver.findElement(By.xpath("//li[@class=\"error-msg\"]//span"));

        // Đặt tên cho error msg
        WebElement errorInvalid = driver.findElement(By.xpath("//li[@class=\"error-msg\"]//span"));

        // Get text cho error msg
        String errorInvalidText = errorInvalid.getText();

        // So sánh error msg với text mình muốn
        Assert.assertEquals(errorInvalidText, "Invalid login or password.");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TC_05_Create_A_New_Account() {
        // Tìm nút My Account rồi ấn để tới trang đăng nhập
        driver.findElement(By.xpath("//div[@class='footer']//a[@title=\"My Account\"]")).click();

        // Ấn Create an account
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        ;

        // Tìm ô First Name rồi nhập
        driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Lucifer");

        // Tìm ô Middle Nam rồi nhập
        driver.findElement(By.xpath("//input[@id='middlename']")).sendKeys("Samael");

        // Tìm ô Last Name rồi nhập
        driver.findElement(By.xpath("//input[@id=\"lastname\"]")).sendKeys("Morningstar");

        // Tìm ô Email rồi nhập
        driver.findElement(By.xpath("//input[@id=\"email_address\"]")).sendKeys(email);

        // Tìm ô Pass rồi nhập
        driver.findElement(By.xpath("//input[@id=\"password\"]")).sendKeys("lucifer13");

        // Tìm ô Confirm pass rồi nhập
        driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("lucifer13");

        // Tìm ô Register rồi ấn
        driver.findElement(By.xpath("//button[@title='Register']")).click();

        // Đặt tên noti
        WebElement successNoti = driver.findElement(By.xpath("//li[@class=\"success-msg\"]//span"));

        // Get text
        String successNotiText = successNoti.getText();

        // Verify noti
        Assert.assertEquals(successNotiText, "Thank you for registering with Main Website Store.");

        // Tìm vị trí tên và pass và đặt tên
        WebElement infoLucifer = driver.findElement(By.xpath("(//div[@class='box-content']/p)[1]"));

        // Get text
        String infoLuciferText = infoLucifer.getText();

        // So sánh
        Assert.assertTrue(infoLuciferText.contains("Lucifer"));
        Assert.assertTrue(infoLuciferText.contains("Morningstar"));
        Assert.assertTrue(infoLuciferText.contains(email));

        // Step 8: Log out khỏi hệ thống
        driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
        driver.findElement(By.xpath("//a[@title='Log Out']")).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Lấy title
        String homePageTitle = driver.getTitle();

        // So sánh
        Assert.assertEquals(homePageTitle, "Home page");

    }

    @Test
    public void TC_06_Login_With_Valid_Email_And_Phone() {
        // Find My Account button then click
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        // Find Email Add then fill
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("lucifer@gmail.com");

        // Fill Pass
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("lucifer13");

        // Find Login button then click
        driver.findElement(By.xpath("//button[@title='Login']")).click();

        // Fine Contact Information and name it
        WebElement infoLucifer = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p"));

        // Get text
        String infoLuciferText = infoLucifer.getText();

        // Compare
        Assert.assertTrue(infoLuciferText.contains("Lucifer"));
        Assert.assertTrue(infoLuciferText.contains("Morningstar"));
        Assert.assertTrue(infoLuciferText.contains("lucifer@gmail.com"));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public int randomData(){
        Random random = new Random();
        return random.nextInt(999999);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
        }
    }
