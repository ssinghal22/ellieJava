import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;

import java.util.List;
import java.util.Set;

public class AmazonPractice {
    WebDriver driver;
    WebDriverWait wait;
    String baseUrl = "https://amazon.com";

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "browsers/chromedriver");
        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        wait = new WebDriverWait(driver, 5);
    }

    @After
    public void teardown() throws Exception {
       // driver.quit();
    }

    @Test
    public void testAmazon() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id='twotabsearchtextbox']")).
                sendKeys("headphones" + Keys.ENTER);

        String originalWindow = driver.getWindowHandle();
        System.out.println("originalWindow" + originalWindow);

        List<WebElement> list = driver.findElements(By.xpath(
                "//span[text()='Best Seller']/ancestor::div[@class='a-section a-spacing-medium']//img/ancestor::a"));

        for (WebElement element : list) {
            String href = element.getAttribute("href");
            System.out.println("href" + href);

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.open(arguments[0]);", href);

            Set<String> windows = driver.getWindowHandles();
            windows.remove(originalWindow);
            System.out.println("windows.size()" + windows.size());

            for (String window : windows) {
                System.out.println("window" + window);

                driver.switchTo().window(window);
                driver.findElement(By.xpath("//*[@id='add-to-cart-button']")).click();

                try {
                    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[contains(text(),'Added to Cart')]")));
                }
                catch (TimeoutException e) {
                    if (isElementPresent(By.id("attachSiNoCoverage-announce"))) {
                        System.out.println("attachSiNoCoverage-announce");
                        driver.findElement(By.id("attachSiNoCoverage-announce")).click();
                    }
                    else if (isElementPresent(By.id("siNoCoverage-announce"))) {
                        System.out.println("siNoCoverage-announce");
                        driver.findElement(By.id("siNoCoverage-announce")).click();
                    }
                    else if (isElementPresent(By.xpath("//button[@aria-label='Close']"))) {
                        System.out.println("//button[@aria-label='Close']");
                        driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
                    }
                }
                driver.close();
                driver.switchTo().window(originalWindow);
            }
        }
        driver.findElement(By.xpath("//*[@id='nav-cart']")).click();
    }

    private Boolean isElementPresent(By by) throws InterruptedException {
        System.out.println("by" + by);
        try {
            Thread.sleep(5000);
            System.out.println("driver.findElement(by)");
            driver.findElement(by);
        }
        catch (NoSuchElementException e) {
            System.out.println("NoSuchElementException e");
            return false;
        }
        System.out.println("return true");
        return true;
    }
}
