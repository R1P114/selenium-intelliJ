package WebDrivers;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Textbox {
    WebDriver driver;
    String pathProject = System.getProperty("user.dir");
    String loginPageUrl, name, gender, dateOfBirthInput, dateoOfBirthOutput, addressInput, addressOutput, city, state, pin, mobile, emailBox;
    String userID, Password;
    String firstName, lastName, fullName, password;
    By nameTextBox = By.xpath("//input[@name='name']");
    By genderTextBox = By.xpath("//input[@value='m']");
    By dobTextBox = By.xpath("//input[@name='dob']");
    By addressTextBox = By.xpath("//textarea[@name='addr']");
    By cityTextBox = By.xpath("//input[@name='city']");
    By stateTextBox = By.xpath("//input[@name='state']");
    By pinNumberBox = By.xpath("//input[@name='pinno']");
    By mobileNumberBox = By.xpath("//input[@name='telephoneno']");
    By emailTextBox = By.xpath("//input[@name='emailid']");
    By passwordTextBox = By.xpath("//input[@name='password']");

    @BeforeClass
    public void beforeClass () {
        System.setProperty("webdriver.chrome.driver", "/Users/ios/Downloads/SeleniumDriver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        //firstName = "John";
        //lastName = "Wick";
        //fullName = firstName + "" + lastName;
        //email = "john" + randomData() +"@gmail.com";
        //password = "123456";
        name = "Lucifer Morningstar";
        gender = "male";
        dateOfBirthInput = "01/01/1995";
        dateoOfBirthOutput = "1995-01-01";
        addressInput = "1 Dai Co Viet\nHanoi";
        addressOutput = "1 Dai Co Viet Hanoi";
        city = "Hanoi";
        state = "Hanoi";
        pin = "100000";
        mobile = "6669996969";
        emailBox = "lucifer" + randomData() + "@gmail.com";
    }
    @Test
    public void TC_01_Textbox () {
        driver.get("http://demo.guru99.com/v4/");
        loginPageUrl = driver.getCurrentUrl();
        driver.findElement(By.xpath("//a[@href='http://demo.guru99.com/']")).click();
        driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(emailBox);
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

        //get text User ID and password
        userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
        Password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

        // Open Login Page and write id and pass
        driver.get(loginPageUrl);
        driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userID); //input[@name='uid']
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(Password);
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

        // Verify Homepage after login successfully
        String titleHomePage = driver.getTitle();
        Assert.assertEquals(titleHomePage,"Guru99 Bank Manager HomePage");

        // Click New Customer
        driver.findElement(By.xpath("//a[@href='addcustomerpage.php']")).click();

        // Enter data
        driver.findElement(nameTextBox).sendKeys(name);
        driver.findElement(genderTextBox).click();
        driver.findElement(dobTextBox).sendKeys(dateOfBirthInput);
        driver.findElement(addressTextBox).sendKeys(addressInput);
        driver.findElement(cityTextBox).sendKeys(city);
        driver.findElement(stateTextBox).sendKeys(state);
        driver.findElement(pinNumberBox).sendKeys(pin);
        driver.findElement(mobileNumberBox).sendKeys(mobile);
        driver.findElement(emailTextBox).sendKeys(emailBox);
        driver.findElement(passwordTextBox).sendKeys(Password);

        driver.findElement(By.xpath("//input[@name='sub']")).click();

        // Get ID's info
        String customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();


        //Verify all info
        String customerName = driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText();
        Assert.assertEquals(customerName, name);

        String customerGender = driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText();
        Assert.assertEquals(customerGender, gender);

        String customerDob = driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText();
        Assert.assertEquals(customerDob, dateoOfBirthOutput);

        String customerAddress = driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText();
        Assert.assertEquals(customerAddress, addressOutput);

        String customerCity = driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText();
        Assert.assertEquals(customerCity, city);

        String customerState = driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText();
        Assert.assertEquals(customerState, state);

        String customerPin = driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText();
        Assert.assertEquals(customerPin, pin);

        String customerPhone = driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText();
        Assert.assertEquals(customerPhone, mobile);

        String customerEmail = driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText();
        Assert.assertEquals(customerEmail, emailBox);

        // Edit Customer
        driver.findElement(By.xpath("//a[@href='EditCustomer.php']")).click();
        driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
        driver.findElement(By.xpath("//input[@value='Submit']")).click();

        // Verify 2 field
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='name']")).getAttribute("value"), name);
        Assert.assertEquals(driver.findElement(By.xpath("//textarea[@name='addr']")).getAttribute("value"), addressInput);

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
