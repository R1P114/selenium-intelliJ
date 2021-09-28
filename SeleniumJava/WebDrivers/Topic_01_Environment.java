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

public class Topic_01_Environment{
    WebDriver driver;
    By fullEmailTextbox = By.xpath("//input[@id=\"txtEmail\"]");
    By fullEmailAgainTextbox = By.xpath("//input[@id=\"txtCEmail\"]");
    By fullPassTextbox = By.xpath("//input[@id=\"txtPassword\"]");
    By fullPassAgainTextbox = By.xpath("//input[@id='txtCPassword']");
    By fullPhoneTextbox = By.xpath("//input[@id=\"txtPhone\"]");
    By fullPressTextbox = By.xpath("//button[@type='submit']");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "/Users/ios/Desktop/Thăng/SeleniumDriver/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Register_With_Empy_Data(){
        // Step 1: Click vào button Đăng Ký
        driver.findElement(fullPressTextbox).click();

        // Step 2: Tìm error msg tại Họ và tên
        driver.findElement(By.xpath("//label[@id='txtFirstname-error']"));
        // Đặt tên cho error mess
        WebElement errorName = driver.findElement(By.xpath("//label[@id='txtFirstname-error']"));
        // Get text của error mess
        String errorNameText = errorName.getText();
        // in ra màn hình text đc get
        System.out.println("ErrorText = " + errorNameText);
        // So sánh text được get với text mình mong muốn
        Assert.assertEquals(errorNameText,"Vui lòng nhập họ tên");

        // Step 3: Tìm error msg tại Địa chỉ Email
        driver.findElement(By.xpath("//label[@id='txtEmail-error']"));
        // Đặt tên cho error mess
        WebElement errorEmail = driver.findElement(By.xpath("//label[@id='txtEmail-error']"));
        // Get text của error mess
        String errorEmailText = errorEmail.getText();
        // in ra màn hình text đc get
        System.out.println("ErrorText = " + errorEmailText);
        // So sánh text được get với text mình mong muốn
        Assert.assertEquals(errorEmailText,"Vui lòng nhập email");

        // Step 4: Tìm error msg tại Nhập lại Email
        driver.findElement(By.xpath("//label[@id='txtCEmail-error']"));
        // Đặt tên cho error mess
        WebElement errorEmailagain = driver.findElement(By.xpath("//label[@id='txtCEmail-error']"));
        // Get text của error mess
        String errorEmailTextagain = errorEmailagain.getText();
        // in ra màn hình text đc get
        System.out.println("ErrorText = " + errorEmailTextagain);
        // So sánh text được get với text mình mong muốn
        Assert.assertEquals(errorEmailTextagain,"Vui lòng nhập lại địa chỉ email");

        // Step 5: Tìm error msg tại Mật khẩu
        driver.findElement(By.xpath("//label[@id='txtPassword-error']"));
        // Đặt tên cho error mess
        WebElement errorPass = driver.findElement(By.xpath("//label[@id='txtPassword-error']"));
        // Get text của error pass
        String errorPassText = errorPass.getText();
        // in ra màn hình text đc get
        System.out.println("ErrorText = " + errorPassText);
        // So sánh text được get với text mình mong muốn
        Assert.assertEquals(errorPassText,"Vui lòng nhập mật khẩu");

        // Step 6: Tìm error msg tại Nhập lại mật khẩu
        driver.findElement(By.xpath("//label[@id='txtCPassword-error']"));
        // Đặt tên cho error mess
        WebElement errorPassagain = driver.findElement(By.xpath("//label[@id='txtCPassword-error']"));
        // Get text của error pass
        String errorPassagainText = errorPassagain.getText();
        // in ra màn hình text đc get
        System.out.println("ErrorText = " + errorPassagainText);
        // So sánh text được get với text mình mong muốn
        Assert.assertEquals(errorPassagainText,"Vui lòng nhập lại mật khẩu");

        // Step 7: Tìm error msg tại Điện thoại
        driver.findElement(By.xpath("//label[@id='txtPhone-error']"));
        // Đặt tên cho error mess
        WebElement errorPhone = driver.findElement(By.xpath("//label[@id='txtPhone-error']"));
        // Get text của error phone
        String errorPhoneText = errorPhone.getText();
        // in ra màn hình text đc get
        System.out.println("ErrorText = " + errorPhoneText);
        // So sánh text được get với text mình mong muốn
        Assert.assertEquals(errorPhoneText,"Vui lòng nhập số điện thoại.");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TC_02_Register_with_invalid_Email() {
        // Step 1: Tìm Email rồi điền Email sai
        driver.findElement(fullEmailTextbox).sendKeys("abc@abc@");

        // Step 2: Tìm Nhập lại Email rồi điền sai
        driver.findElement(fullEmailAgainTextbox).sendKeys("abc123@abc123@");

        driver.findElement(fullPressTextbox).click();

        // Step 3: Tìm error msg tại Địa chỉ Email
        WebElement errorEmail = driver.findElement(By.xpath("//label[@id='txtEmail-error']"));
        // Get text của error msg
        String errorEmailText = errorEmail.getText();
        // So sánh text được get với text mình muốn
        Assert.assertEquals(errorEmailText,"Vui lòng nhập email hợp lệ");

        // Step 4: tìm error msg tại Nhập lại Email
        WebElement errorEmailagain = driver.findElement(By.xpath("//label[@id='txtCEmail-error']"));
        // Get text của error msg
        String errorEmailagainText = errorEmailagain.getText();
        // So sánh text được get với text mình muốn
        Assert.assertEquals(errorEmailagainText,"Email nhập lại không đúng");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TC_03_Register_with_incorrect_Confirm_email() {
        // Step 1: Tìm ô Địa chỉ Email rồi nhập mail đúng
        driver.findElement(fullEmailTextbox).clear();
        driver.findElement(fullEmailTextbox).sendKeys("johnwick@gmail.com");

        // Step 2: Click vào button Đăng ký
        driver.findElement(fullPressTextbox).click();

        // Step 3: Tìm error msg tại Nhập lại Email
        WebElement errorEmailagain = driver.findElement(By.xpath("//label[@id='txtCEmail-error']"));
        // Get text cho error msg
        String errorEmailagainText = errorEmailagain.getText();
        // So sánh text được get với text mình muốn
        Assert.assertEquals(errorEmailagainText,"Email nhập lại không đúng");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TC_04_Register_with_pass_less_6_char() {
        // Step 1: Tìm ô Mật khẩu rồi nhập pass ít hơn 6 ký tự
        driver.findElement(fullEmailTextbox).clear();
        driver.findElement(fullEmailAgainTextbox).clear();
        driver.findElement(fullPassTextbox).sendKeys("1234");
        driver.findElement(fullPassAgainTextbox).sendKeys("1234");

        // Step 2: Tìm nút Đăng ký rồi ấn
        driver.findElement(fullPressTextbox).click();

        // Step 3: Tìm error msg tại Pass (Đặt tên và chỉ ra địa điểm cái cần lấy)
        WebElement errorPass = driver.findElement(By.xpath("//label[@id='txtPassword-error']"));
        // Get text của error msg (Lấy cái đó)
        String errorPassText = errorPass.getText();
        // So sánh text đc get với text mình muốn
        Assert.assertEquals(errorPassText,"Mật khẩu phải có ít nhất 6 ký tự");

        // Step 4: Tìm error msg tại Nhập lại mật khẩu
        WebElement errorPassAgain = driver.findElement(By.xpath("//label[@id='txtCPassword-error']"));
        // Get text của error msg
        String errortPassagainText = errorPassAgain.getText();
        // So sánh text được get với text mình muốn
        Assert.assertEquals(errortPassagainText,"Mật khẩu phải có ít nhất 6 ký tự");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TC_05_Register_with_incorrect_confirm_password () {
        driver.findElement(By.xpath("//input[@id=\"txtPassword\"]")).sendKeys("12345678");
        driver.findElement(By.xpath("//input[@id=\"txtCPassword\"]")).sendKeys("1234567");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        WebElement errorPassAgain = driver.findElement(By.xpath("//label[@id=\"txtCPassword-error\"]"));
        String errorPassAgainText = errorPassAgain.getText();
        Assert.assertEquals(errorPassAgainText,"Mật khẩu bạn nhập không khớp");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TC_06_Register_with_invalid_phone1number () {
        driver.findElement(fullPassTextbox).clear();
        driver.findElement(fullPassAgainTextbox).clear();
        driver.findElement(By.xpath("//input[@id=\"txtPhone\"]")).sendKeys("0945678");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        WebElement errorPhone = driver.findElement(By.xpath("//label[@id=\"txtPhone-error\"]"));
        String errorPhoneText = errorPhone.getText();
        Assert.assertEquals(errorPhoneText,"Số điện thoại phải từ 10-11 số.");
        try {
            Thread.sleep(3000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath("//input[@id=\"txtPhone\"]")).clear();
        driver.findElement(By.xpath("//input[@id=\"txtPhone\"]")).sendKeys("12345");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        WebElement errorNumber = driver.findElement(By.xpath("//label[@id=\"txtPhone-error\"]"));
        String errorNumberText = errorNumber.getText();
        Assert.assertEquals(errorNumberText,"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}