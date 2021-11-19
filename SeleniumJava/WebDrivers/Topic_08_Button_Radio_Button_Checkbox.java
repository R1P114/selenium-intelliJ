package WebDrivers;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Button_Radio_Button_Checkbox {
    WebDriver driver;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass () {
        System.setProperty("webdriver.chrome.driver", "/Users/ios/Downloads/SeleniumDriver/chromedriver");
        driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Javacsript_Executor () {
        driver.get("https://www.fahasa.com/customer/account/create");

        driver.findElement(By.xpath("//li[@class='popup-login-tab-item popup-login-tab-login']")).click();

        By loginButton = By.xpath("//button[@class='fhs-btn-login']");

        Assert.assertFalse(driver.findElement(loginButton).isEnabled());

        driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys("lucifer@gmail.com");
        driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("123456");

        Assert.assertTrue(driver.findElement(loginButton).isEnabled());

        // Verify button with color = red
        String rgbaColor = driver.findElement(loginButton).getCssValue("background-color");
        System.out.println("RGBA = " + rgbaColor);
        String hexaColor = Color.fromString(rgbaColor).asHex().toUpperCase(Locale.ROOT);
        System.out.println("Hex = " + hexaColor);
        Assert.assertEquals(hexaColor,"#C92127");

        driver.navigate().refresh();
        driver.findElement(By.xpath("//li[@class='popup-login-tab-item popup-login-tab-login']")).click();

        // Remove Login Button's Disabled
        jsExecutor.executeScript("arguments[0].removeAttribute('disabled');", driver.findElement(loginButton));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(loginButton).click();

        // Verify alert when remove Login
        By loginAlert = By.xpath("//div[@class='popup-login-content']" + "//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']");
        By passwordAlert = By.xpath("//div[@class='popup-login-content']" + "//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']");
        Assert.assertEquals(driver.findElement(loginAlert).getText(), "Thông tin này không thể để trống");
        Assert.assertEquals(driver.findElement(passwordAlert).getText(), "Thông tin này không thể để trống");
    }

    @Test
    public void TC_02_Default_Checkbox_Radio_Button () {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

        By airCondition = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
        driver.findElement(airCondition).click();
        Assert.assertTrue(driver.findElement(airCondition).isSelected());

        driver.findElement(airCondition).click();
        Assert.assertFalse(driver.findElement(airCondition).isSelected());

        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

        By petrolTwo = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");

        // Verify button is selected or not
        Assert.assertFalse(driver.findElement(petrolTwo).isSelected());

        driver.findElement(petrolTwo).click();
        Assert.assertTrue(driver.findElement(petrolTwo).isSelected());
    }

    @Test
    public void TC_03_Custom_Checkbox_Radio_Button () {
        driver.get("https://material.angular.io/components/radio/examples");

        By summerButton = By.xpath("//input[@value='Summer']");

        jsExecutor.executeScript("arguments[0].click();", driver.findElement(summerButton));

        Assert.assertTrue(driver.findElement(summerButton).isSelected());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.get("https://material.angular.io/components/checkbox/examples");
         By checkedBox = By.xpath("//span[text()='Checked']/preceding-sibling::span/input");
         By indeterminateBox = By.xpath("//span[text()='Indeterminate']/preceding-sibling::span/input");

         checkToCheckBox(checkedBox);
         checkToCheckBox(indeterminateBox);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(driver.findElement(checkedBox).isSelected());
        Assert.assertTrue(driver.findElement(indeterminateBox).isSelected());

        unCheckToCheckBox(checkedBox);
        unCheckToCheckBox(indeterminateBox);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertFalse(driver.findElement(checkedBox).isSelected());
        Assert.assertFalse(driver.findElement(indeterminateBox).isSelected());

        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

        By canthoCityBox = By.xpath("//div[@aria-label='Cần Thơ']//div[@class='appsMaterialWizToggleRadiogroupOffRadio exportOuterCircle']");
        Assert.assertTrue(driver.findElement(canthoCityBox).isDisplayed());
        clickToElement(canthoCityBox);
        Assert.assertTrue(driver.findElement(canthoCityBox).isDisplayed());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickToElement (By by) {
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
    }

    public void checkToCheckBox (By by) {
        if (!driver.findElement(by).isSelected()) {
            jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
        }
    }

    public void unCheckToCheckBox (By by) {
        if (driver.findElement(by).isSelected()) {
            jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
        }
    }

    @AfterClass
    public void afterClass () {
        driver.quit();
    }
}

