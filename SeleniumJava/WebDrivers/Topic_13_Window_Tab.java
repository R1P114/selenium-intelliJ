package WebDrivers;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class Topic_13_Window_Tab {
    WebDriver driver;
    WebDriverWait expliciWait;
    org.openqa.selenium.Alert alert;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver","/Users/ios/Downloads/SeleniumDriver/chromedriver");
        driver = new ChromeDriver();
        expliciWait = new WebDriverWait(driver,30);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Window_01 () {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        // Switch to Google
        switchToWindownByTitle("Google");

        String googleTitle = driver.getTitle();
        Assert.assertEquals(googleTitle, "Google");

        // Switch to parent page
        switchToWindownByTitle("SELENIUM WEBDRIVER FORM DEMO");

        // Click to Fb
        driver.findElement(By.xpath("//a[@href='https://facebook.com']")).click();

        // Switch to Fb
        switchToWindownByTitle("Facebook - Đăng nhập hoặc đăng ký");

        String fbTitle = driver.getTitle();
        Assert.assertEquals(fbTitle, "Facebook – log in or sign up");

        // Switch to parent page
        switchToWindownByTitle("SELENIUM WEBDRIVER FORM DEMO");

        driver.findElement(By.xpath("//a[@href='https://tiki.vn']")).click();

        // Switch to Tiki
        switchToWindownByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

        String tikiTitle = driver.getTitle();
        Assert.assertEquals(tikiTitle, "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

        // Switch to parent page
        switchToWindownByTitle("SELENIUM WEBDRIVER FORM DEMO");

        // Get ID's parent page
        String parentID = driver.getWindowHandle();

        // Close all without Parent page
        closeAllTabWithoutParent(parentID);

        // Check by verify title
        String parentCheck = driver.getTitle();
        System.out.println(parentCheck);
        Assert.assertEquals(parentCheck, "SELENIUM WEBDRIVER FORM DEMO");
    }

    @Test
    public void TC_02_Window_02 () {
        driver.get("https://kyna.vn/");

        // Click to Fb
        clickToElementByJS("//div[@id='k-footer']//img[@alt='facebook']");
        switchToWindownByTitle("Kyna.vn - Trang chủ | Facebook");
        String fbTitle = driver.getTitle();
        Assert.assertEquals(fbTitle, "Kyna.vn - Home | Facebook");
        switchToWindownByTitle("Kyna.vn - Học online cùng chuyên gia");
        sleepInSecond(3);

        // Click to Youtube
        clickToElementByJS("//div[@id='k-footer']//img[@alt='youtube']");

        switchToWindownByTitle("Kyna.vn - YouTube");
        String youtubeTitle = driver.getTitle();
        Assert.assertEquals(youtubeTitle, "Kyna.vn - YouTube");

        // Back to Kyna
        switchToWindownByTitle("Kyna.vn - Học online cùng chuyên gia");

        // Click to BCT - blue
        clickToElementByJS("//a[@href='http://online.gov.vn/HomePage/CustomWebsiteDisplay.aspx?DocId=61482']");

        switchToWindownByTitle("Thông tin website thương mại điện tử - Online.Gov.VN");
        String blueTitle = driver.getTitle();
        Assert.assertEquals(blueTitle, "Thông tin website thương mại điện tử - Online.Gov.VN");
        sleepInSecond(3);

        // Back to Kyna
        switchToWindownByTitle("Kyna.vn - Học online cùng chuyên gia");

        // Click to BCT - red
        clickToElementByJS("//a[@href='http://online.gov.vn/Home/WebDetails/60140']");

        switchToWindownByTitle("Thông tin website thương mại điện tử - Online.Gov.VN");
        String redTitle = driver.getTitle();
        Assert.assertEquals(redTitle, "Thông tin website thương mại điện tử - Online.Gov.VN");
        sleepInSecond(3);

        switchToWindownByTitle("Kyna.vn - Học online cùng chuyên gia");

        String parentID = driver.getWindowHandle();
        closeAllTabWithoutParent(parentID);
    }

    @Test
    public void TC_03_Window_03 () {
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//a[@href='http://live.techpanda.org/index.php/mobile.html']")).click();

        driver.findElement(By.xpath("//a[@title='Xperia']//following-sibling::div//a[@class='link-compare']")).click();

        // Verify text
        String successXperia = driver.findElement(By.xpath("//li[@class='success-msg']")).getText();
        Assert.assertEquals(successXperia, "The product Sony Xperia has been added to comparison list.");

        driver.findElement(By.xpath("//a[@title='Samsung Galaxy']//following-sibling::div//a[@class='link-compare']")).click();

        // Verify text
        String successSS = driver.findElement(By.xpath("//li[@class='success-msg']")).getText();
        Assert.assertEquals(successSS, "The product Samsung Galaxy has been added to comparison list.");

        driver.findElement(By.xpath("//button[@title='Compare']")).click();

        switchToWindownByTitle("Products Comparison List - Magento Commerce");

        String compareWindow = driver.getTitle();
        Assert.assertEquals(compareWindow, "Products Comparison List - Magento Commerce");
        driver.close();

        switchToWindownByTitle("Mobile");

        driver.findElement(By.xpath("//a[@href='http://live.techpanda.org/index.php/catalog/product_compare/clear/uenc/aHR0cDovL2xpdmUudGVjaHBhbmRhLm9yZy9pbmRleC5waHAvbW9iaWxlLmh0bWw,/']")).click();

        alert = expliciWait.until(ExpectedConditions.alertIsPresent());
        alert.accept();

        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']")).getText(), "The comparison list was cleared.");
    }

    @Test
    public void TC_04_Window_04 () {
        driver.get("https://dictionary.cambridge.org/vi/");

        driver.findElement(By.xpath("//span[text()='Đăng nhập']")).click();

        switchToWindownByTitle("Login");

        driver.findElement(By.xpath("//input[@value='Log in']")).click();

        String idText = driver.findElement(By.xpath("//div[@class='gigya-composite-control gigya-composite-control-textbox']//span[@class='gigya-error-msg gigya-error-msg-active gigya-error-code-400027 gigya-error-type-server']")).getText();
        String passText = driver.findElement(By.xpath("//div[@class='gigya-composite-control gigya-composite-control-password']//span[@class='gigya-error-msg gigya-error-msg-active gigya-error-code-400027 gigya-error-type-server']")).getText();

        Assert.assertEquals(idText, "This field is required");
        Assert.assertEquals(passText, "This field is required");
    }

    public int randomData() {
        Random random = new Random();
        return random.nextInt(999999);
    }

    public void sleepInSecond(long timeoutInSecond){
        try {
            Thread.sleep(timeoutInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void switchToWindownByTitle(String expectedTitle){
        Set<String> allWindown = driver.getWindowHandles();
        for (String id:allWindown){
            driver.switchTo().window(id);
            String windownTitle = driver.getTitle();
            if(windownTitle.equals(expectedTitle)){
                break;
            }
        }
    }

    public boolean closeAllTabWithoutParent(String parentID){
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows){
            driver.switchTo().window(runWindows);
            String windowTitle = driver.getTitle();
            if (!runWindows.equals(parentID)){
                driver.switchTo().window(runWindows);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
        if (driver.getWindowHandles().size() == 1)
            return true;
        else
            return false;
    }

    public void clickToElementByJS(String locator){
        WebElement element = driver.findElement(By.xpath(locator));
        JavascriptExecutor jsExcutor = (JavascriptExecutor) driver;
        jsExcutor.executeScript("arguments[0].click();",element);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}