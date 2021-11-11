package WebDrivers;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Default_DropDown {
    WebDriver driver;
    Select select;

    @BeforeClass
    public void beforeClass () {
        System.setProperty("webdriver.chrome.driver", "/Users/ios/Downloads/SeleniumDriver/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.rode.com/wheretobuy");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Dropdown () {
        // Choose a item
        // select.selectByVisibleText();

        // Check Dropdown is multiple or not
        // Assert.assertFalse(select.isMultiple());

        // Check choose A item is right or not
        //Assert.assertEquals(select.getFirstSelectedOption().getText(),);

        // Get total item in dropdown list => Verify how many it is?
        // Assert.assertEquals(select.getOptions().size(),);

        // Check Dropdown is multiple or not
        select = new Select(driver.findElement(By.xpath("//select[@id='where_country']")));
        Assert.assertFalse(select.isMultiple());

        // Choose Vietnam value
        select.selectByVisibleText("Vietnam");

        // Check Vietnam is chosen
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"Vietnam");

        driver.findElement(By.xpath("//input[@value='Search']")).click();

        // Check 31 value back
        List<WebElement> storeList = driver.findElements(By.xpath("//div[@id='search_results']/div[@class='result_item']"));
        System.out.println(" số lượng cửa hàng = " + storeList.size());
        Assert.assertEquals(storeList.size(),31);
        for (WebElement store:storeList){
            System.out.println(store.getText());
        }
    }

    @Test
    public void TC_02_Dropdown_II () {
        driver.get("https://demo.nopcommerce.com/register");
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();

        // Fill form to register
        driver.findElement(By.xpath("//input[@value='M']")).click();
        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Lucifer");
        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Morningstar");

        // Check select item
        select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
        select.selectByVisibleText("6");
        Assert.assertEquals(select.getOptions().size(),32);

        select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
        select.selectByVisibleText("June");
        Assert.assertEquals(select.getOptions().size(),13);

        select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
        select.selectByVisibleText("1996");
        Assert.assertEquals(select.getOptions().size(),112);

        driver.findElement(By.xpath("//input[@name='Email']")).sendKeys("lucifer" + randomData() + "@gmail.com");

        driver.findElement(By.xpath("//input[@name='Password']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@name='ConfirmPassword']")).sendKeys("123456");

        driver.findElement(By.xpath("//button[@name='register-button']")).click();

        String successInform = driver.findElement(By.xpath("//div[@class='result']")).getText();
        Assert.assertEquals(successInform,"Your registration completed");

        driver.findElement(By.xpath("//a[@class='ico-account']")).click();

        // Check result
        select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"6");

        select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"June");

        select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"1996");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int randomData () {
        Random random = new Random();
        return random.nextInt(999999);
    }

    @AfterClass
    public void afterClass () {
        driver.quit();
    }
}
