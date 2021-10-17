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

public class Topic_04_Web_Element {
    WebDriver driver;

    @BeforeClass
    public void beforeClass () {
        System.setProperty("webdriver.chrome.driver", "/Users/ios/Desktop/Thăng/SeleniumDriver/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Is_Displayed () {
        WebElement emailBox = driver.findElement(By.xpath("//input[@id='mail']"));
        if (emailBox.isDisplayed()) {
            emailBox.sendKeys("Automation Testing");
            System.out.println("Element is displayed");
        } else {
            System.out.println("Element is not display");
        }

        WebElement ageUnder18 = driver.findElement(By.xpath("//label[@for='under_18']"));
        if (ageUnder18.isDisplayed()) {
            ageUnder18.click();
            System.out.println("Age under 18 is displayed");
        } else {
            System.out.println("Age under 18 is not displayed");
        }

        WebElement educationTextbox = driver.findElement(By.xpath("//textarea[@id='edu']"));
        if (educationTextbox.isDisplayed()) {
            educationTextbox.click();
            System.out.println("Education is displayed");
        } else {
            System.out.println("Education is not displayed");
        }

        WebElement user5Text = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
        if (user5Text.isDisplayed()) {
            System.out.println("User 5 text is displayed");
        } else {
            System.out.println("User 5 text is not displayed");
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TC_02_Is_Enabled () {
        WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='mail']"));
        if (emailTextbox.isEnabled()) {
            System.out.println("Element is enabled");
        } else {
            System.out.println("Element is disabled");
        }

        WebElement ageBox = driver.findElement(By.xpath("//label[@for='under_18']"));
        if (ageBox.isEnabled()) {
            System.out.println("Element is enabled");
        } else {
            System.out.println("Element is disabled");
        }

        WebElement educationBox = driver.findElement(By.xpath("//textarea[@id='edu']"));
        if (educationBox.isEnabled()) {
            System.out.println("Element is enabled");
        } else {
            System.out.println("Element is disabled");
        }

        WebElement job1Box = driver.findElement(By.xpath("//select[@name='user_job1']"));
        if (job1Box.isEnabled()) {
            System.out.println("Element is enabled");
        } else {
            System.out.println("Element is disabled");
        }

        WebElement job2Box = driver.findElement(By.xpath("//select[@name='user_job2']"));
        if (job1Box.isEnabled()) {
            System.out.println("Element is enabled");
        } else {
            System.out.println("Element is disabled");
        }

        WebElement developeBox = driver.findElement(By.xpath("//label[@for='development']"));
        if (developeBox.isEnabled()) {
            System.out.println("Element is enabled");
        } else
            System.out.println("Element is disabled");

        WebElement slider1Text = driver.findElement(By.xpath("//input[@id='slider-1']"));
        if (slider1Text.isEnabled()) {
            System.out.println("Element is enabled");
        } else
            System.out.println("Element is disabled");

        WebElement passText = driver.findElement(By.xpath("//legend[text()='Your basic info']/following-sibling::input[@type='password']"));
        if (passText.isEnabled()) {
            System.out.println("Element is enabled");
        } else
            System.out.println("Element is disabled");

        WebElement radioAgeButton = driver.findElement(By.xpath("//label[@for='radio-disabled']"));
        if (radioAgeButton.isEnabled()) {
            System.out.println("Element is enabled");
        } else
            System.out.println("Element is disabled");

        WebElement biographyBox = driver.findElement(By.xpath("//textarea[@id='bio']"));
        if (biographyBox.isEnabled()) {
            System.out.println("Element is enabled");
        } else
            System.out.println("Element is disabled");

        WebElement job3Role = driver.findElement(By.xpath("//select[@id='job3']"));
        if (job3Role.isEnabled()) {
            System.out.println("Element is enabled");
        } else
            System.out.println("Element is disabled");

        WebElement interestCheckBox = driver.findElement(By.xpath("//label[@for='check-disbaled']"));
        if (interestCheckBox.isEnabled()) {
            System.out.println("Element is enabled");
        } else
            System.out.println("Element is disabled");

        WebElement slider2Box = driver.findElement(By.xpath("//input[@name='slider-2']"));
        if (slider2Box.isEnabled()) {
            System.out.println("Element is enabled");
        } else
            System.out.println("Element is disabled");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TC_03_Is_Selected () {
        WebElement ageBox = driver.findElement(By.xpath("//input[@id='under_18']"));
        WebElement javaBox = driver.findElement(By.xpath("//input[@id='java']"));
        ageBox.click();
        javaBox.click();
        if (ageBox.isSelected()) {
            System.out.println("Element is selected");
        } else
            System.out.println("Element is de-selected");
        if (javaBox.isSelected()) {
            System.out.println("Element is selected");
        } else
            System.out.println("Element is de-selected");
        javaBox.click();
        if (javaBox.isSelected()) {
            System.out.println("Element is selected");
        } else
            System.out.println("Element is de-selected");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void elementIsDisplayed(By by){
        if (driver.findElement(by).isDisplayed()){
            System.out.println("abc xyz");
        } else {
            System.out.println(" 111111111");
        }
    }

    public void elementIsEnabled (By by) {
        if (driver.findElement(by).isEnabled()) {
            System.out.println("Element is enabled");
        } else
            System.out.println("Element is disabled");
    }

    @AfterClass
    public void afterClass () {
        driver.quit();
    }
}
