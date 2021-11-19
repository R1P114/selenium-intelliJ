package WebDrivers;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Custom_Dropdown {
    WebDriver driver;
    WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir");
    JavascriptExecutor jsExecutor;
    Select select;

    @BeforeClass
    public void beforeClass () {
        System.setProperty("webdriver.chrome.driver", "/Users/ios/Downloads/SeleniumDriver/chromedriver");
        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver, 30);
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        jsExecutor = (JavascriptExecutor) driver;
    }

    @Test
    public void TC_01_JQuery () {
        driver.findElement(By.xpath("//span[@id='number-button']")).click();

        // Wait for all element is loaded
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[@id='number-menu']//div")));

        // Create List Item to store all element
        List<WebElement> allItems = driver.findElements(By.xpath("//ul[@id='number-menu']//div"));

        for (WebElement item : allItems) {
            if (item.getText().equals("19")) {
                if (item.isDisplayed()) {
                    item.click();
                } else {
                    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
                    item.click();
                }
            }
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TC_02_ReactJS () {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

        driver.findElement(By.xpath("//div[@role='listbox']")).click();

        driver.findElement(By.xpath("//div[@role='option']//span[text()='Jenny Hess']")).click();

        String checkTextBox = driver.findElement(By.xpath("//div[@role='alert']")).getText();

        Assert.assertEquals(checkTextBox, "Jenny Hess");

        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

        driver.findElement(By.xpath("//div[@role='listbox']")).click();

        driver.findElement(By.xpath("//div[@role='option']//span[text()='Justen Kitsune']")).click();

        String checkTextBox2 = driver.findElement(By.xpath("//div[@role='alert']")).getText();

        Assert.assertEquals(checkTextBox2, "Justen Kitsune");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TC_03_VueJS () {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");

        driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).click();

        driver.findElement(By.xpath("//a[@href='javascript:void(0)' and contains(text(),'First Option')]")).click();

        String checkTextBox2 = driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText();

        Assert.assertEquals(checkTextBox2, "First Option");
    }

    @Test
    public void TC_04_Angular () {
        driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");

        driver.findElement(By.xpath("//span[@aria-owns='games_options']")).click();

        selectItemInDropDown(By.cssSelector("span[aria-owns='games_options']"), By.cssSelector("ul#games_options>li"), "Badminton");

        String selectTextBox = driver.findElement(By.xpath("//input[@aria-label='Badminton']")).getAttribute("aria-label");

        Assert.assertEquals(selectTextBox, "Badminton");
    }

    private void selectItemInDropDown(By parentBy, By childBy, String expectedTextItem) {
        driver.findElement(parentBy).click();

        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));

        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedTextItem)) {
                if (item.isDisplayed()) {
                    item.click();
                } else {
                    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
                    item.click();
                }
            }
        }
    }

    @AfterClass
    public void afterClass () {
        driver.quit();
    }
}
