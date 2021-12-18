package WebDrivers;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

// This subject is written in JE
public class Topic_14_JavaScript_Executor {
    WebDriver driver;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "/Users/ios/Downloads/SeleniumDriver/chromedriver");
        driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_JE () {
        // Open page
        navigateToUrlByJS("http://live.techpanda.org/");

        String liveGuru = (String) executeForBrowser("return document.domain;");
        Assert.assertEquals(liveGuru, "live.techpanda.org");

        String homePageURL = (String) executeForBrowser("return document.URL");
        Assert.assertEquals(homePageURL,"http://live.techpanda.org/");

        highlightElement("//a[text()='Mobile']");
        clickToElementByJS("//a[text()='Mobile']");

        highlightElement("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div//button");
        clickToElementByJS("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div//button");

        // Solution 1 to verify msg
        String innerTextValue = getInnerText();
        Assert.assertTrue(innerTextValue.contains("Samsung Galaxy was added to your shopping cart."));

        // Solution 2
        // Assert.assertTrue(areExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));

        highlightElement("//a[text()='Customer Service']");
        clickToElementByJS("//a[text()='Customer Service']");

        Assert.assertEquals(executeForBrowser("return document.title ;"), "Customer Service");

        scrollToElementOnTop("//input[@title='Sign up for our newsletter']");
        sleepInSecond(3);

        highlightElement("//input[@title='Sign up for our newsletter']");
        sendkeyToElementByJS("//input[@title='Sign up for our newsletter']", "ucifer" + randomData() + "@gmail.com");
        sleepInSecond(3);

        highlightElement("//button[@title='Subscribe']");
        clickToElementByJS("//button[@title='Subscribe']");
        sleepInSecond(3);

        Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));

        navigateToUrlByJS("http://demo.guru99.com/v4/");

        // Verify domain
        Assert.assertEquals(executeForBrowser("return document.domain;"), "demo.guru99.com");
    }

    @Test
    public void TC_02_HTML5_01 () {
        navigateToUrlByJS("https://automationfc.github.io/html5/index.html");
        clickToElementByJS("//input[@value='SUBMIT']");
        Assert.assertEquals(getElementValidationMessage("//input[@id='fname']"), "Please fill out this field.");

        sendkeyToElementByJS("//input[@id='fname']", "Lucifer");
        clickToElementByJS("//input[@value='SUBMIT']");
        Assert.assertEquals(getElementValidationMessage("//input[@id='pass']"), "Please fill out this field.");

        sendkeyToElementByJS("//input[@id='pass']", "Lucifer" + randomData());
        clickToElementByJS("//input[@value='SUBMIT']");
        Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Please fill out this field.");

        sendkeyToElementByJS("//input[@id='em']", "lucifer" + randomData() + "@gmail");
        clickToElementByJS("//input[@value='SUBMIT']");
        Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Please match the requested format.");

        removeAttributeInDOM("//input[@id='em']", "lucifer" + randomData() + "@gmail");
        sleepInSecond(2);

        sendkeyToElementByJS("//input[@id='em']", randomData() + "@" + randomData() );
        clickToElementByJS("//input[@value='SUBMIT']");
        Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Please match the requested format.");

        removeAttributeInDOM("//input[@id='em']", "lucifer" + randomData() + "@gmail");

        sendkeyToElementByJS("//input[@id='em']", "lucifer" + randomData() + "@gmail.com");
        clickToElementByJS("//input[@value='SUBMIT']");
        Assert.assertEquals(getElementValidationMessage("//select"), "Please select an item in the list.");
    }

    @Test
    public void TC_03_HTML5_02 () {
        navigateToUrlByJS("https://login.ubuntu.com/");
        clickToElementByJS("//button[@id='cookie-policy-button-accept']");

    }



    public int randomData () {
        Random random = new Random();
        return random.nextInt(99999);
    }

    public void sleepInSecond(long timeoutInSecond){
        try {
            Thread.sleep(timeoutInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Object executeForBrowser(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean areExpectedTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
    }

    public void highlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
    }

    public void scrollToElementOnTop(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollToElementOnDown(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
        if (status) {
            return true;
        }
        return false;
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }

    @AfterClass
    public void afterClass () {
        driver.quit();
    }
}
