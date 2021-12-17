package WebDrivers;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_User_Interaction {
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
    public void TC_01_Hover () {
        driver.get("https://automationfc.github.io/jquery-tooltip/");

        action.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(), "We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC_02_Hover_Element () {
        driver.get("https://www.myntra.com/");

        action.moveToElement(driver.findElement(By.xpath("//a[@data-group='kids']"))).perform();

        // Move to elements + click
        action.click(driver.findElement(By.xpath("//a[@data-reactid='451']"))).perform();

        // If use get text => AssertEqual, if use "and text" in By ... => use AssertTrue
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb']")).getText(), "Kids Home Bath");
    }

    @Test
    public void TC_03_Hover_To_Element () {
        driver.get("https://www.fahasa.com/");

        action.moveToElement(driver.findElement(By.xpath("//div[@class='row custom-menu-homepage']//a[@href='https://www.fahasa.com/sach-trong-nuoc.html']"))).perform();
    }

    @Test
    public void TC_04_Select_Multiple () {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> allNumber = driver.findElements(By.xpath("//ol[@id='selectable']//li"));

        action.clickAndHold(allNumber.get(0))
                .moveToElement(allNumber.get(3))
                .release()
                .perform();
        Assert.assertEquals(driver.findElements(By.xpath("//ol[@id='selectable']//li[@class='ui-state-default ui-selectee ui-selected']")).size(),4);
    }

    @Test
    public void TC_05_Select_Multiple_Random () {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> allNumber = driver.findElements(By.xpath("//ol[@id='selectable']//li"));

        // Press Ctrl
        action.keyDown(Keys.CONTROL).perform();

        action.click(allNumber.get(0)).click(allNumber.get(2)).click(allNumber.get(4)).click(allNumber.get(6)).perform();

        //Release Ctrl
        action.keyUp(Keys.CONTROL).perform();

        Assert.assertEquals(driver.findElements(By.xpath("//ol[@id='selectable']//li[@class='ui-state-default ui-selectee ui-selected']")).size(),4);
    }

    @Test
    public void TC_06_Double_Click () {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // Scroll to element
        jsExcutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[@ondblclick='doubleClickMe()']")));

        action.doubleClick(driver.findElement(By.xpath("//button[@ondblclick='doubleClickMe()']"))).perform();

        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(), "Hello Automation Guys!");
    }

    @Test
    public void TC_07_Right_Click () {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

        action.contextClick(driver.findElement(By.xpath("//span[@class='context-menu-one btn btn-neutral']"))).perform();

        action.moveToElement(driver.findElement(By.xpath("//li[@class='context-menu-item context-menu-icon context-menu-icon-quit']"))).perform();

        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='context-menu-item context-menu-icon context-menu-icon-quit context-menu-hover context-menu-visible']")).isDisplayed());

        action.click(driver.findElement(By.xpath("//li[@class='context-menu-item context-menu-icon context-menu-icon-quit context-menu-hover context-menu-visible']"))).perform();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.switchTo().alert().accept();

        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='context-menu-item context-menu-icon context-menu-icon-quit']")).isDisplayed());
    }

    @Test
    public void TC_08_Drag_Drop_HTML4 () {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");

        WebElement smallCircle = driver.findElement(By.xpath("//div[@id='draggable']"));
        WebElement bigCircle = driver.findElement(By.xpath("//div[@id='droptarget']"));

        Assert.assertEquals(bigCircle.getText(), "Drag the small circle here.");
        action.dragAndDrop(smallCircle, bigCircle).perform();

        Assert.assertEquals(bigCircle.getText(), "You did great!");

        // Verify button with color = blue
        String rgbaColor = driver.findElement(By.xpath("//div[@id='droptarget']")).getCssValue("background-color");
        System.out.println("RGBA = " + rgbaColor);
        String hexaColor = Color.fromString(rgbaColor).asHex().toUpperCase(Locale.ROOT);
        System.out.println("Hex = " + hexaColor);
        Assert.assertEquals(hexaColor,"#03A9F4");
    }

    @Test
    public void TC_09_Drag_Drop_HTML5 () {
        driver.get("https://automationfc.github.io/drag-drop-html5/");

        WebElement squareA = driver.findElement(By.xpath("//div[@id='column-a']"));
        WebElement squareB = driver.findElement(By.xpath("//div[@id='column-b']"));
    }

    @AfterClass
    public void afterClass () {
        driver.quit();
    }
}
