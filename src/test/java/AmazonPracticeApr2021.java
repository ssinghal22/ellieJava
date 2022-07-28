import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class AmazonPracticeApr2021 {
    WebDriver driver;
    WebDriverWait wait;
    String baseURL = "https://amazon.com";

    @Before
    public void setUp() throws Exception{
        System.setProperty("webdriver.chrome.driver", "browsers/chromedriver");
        driver = new ChromeDriver();
        driver.get(baseURL);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 2);
    }

    @After
    public void tearDown() throws Exception{
        //driver.quit();
    }

    private void openLinkInNewTab(String href){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String jsScript = "window.open(arguments[0]);";
        js.executeScript(jsScript, href);

        String originalWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        windowHandles.remove(originalWindow);
        for (String windowHandle: windowHandles){
            driver.switchTo().window(windowHandle);
        }
        driver.findElement(By.xpath("//*[@id='add-to-cart-button']")).click();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='attach-sidesheet-checkout-button-announce']")));
        }
        catch (TimeoutException e){
            boolean popup1 = driver.findElement(By.xpath("//*[@aria-labelledby='attachSiNoCoverage-announce']")).isDisplayed();
            if (popup1){
                driver.findElement(By.xpath("//*[@aria-labelledby='attachSiNoCoverage-announce']")).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='attach-sidesheet-checkout-button-announce']")));
            }
        }
        driver.close();
        driver.switchTo().window(originalWindow);
    }

    @Test
    public void testAmazon() {
        driver.findElement(By.xpath("//*[@id='twotabsearchtextbox']")).
                sendKeys("headphones" + Keys.ENTER);

        List<WebElement> bestSellers = driver.findElements(By.xpath("//*[text()='Best Seller']/ancestor::" +
                "*[@class='a-section a-spacing-medium']//img/ancestor::a"));
        for (WebElement link: bestSellers){
            String href = link.getAttribute("href");
            openLinkInNewTab(href);
        }
        driver.findElement(By.xpath("//*[@id='nav-cart-count']")).click();
    }
}
