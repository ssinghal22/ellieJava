import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonTestCase {

    WebDriver driver;
    WebDriverWait wait;
    String baseUrl = "https://amazon.com";

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "browsers/chromedriver");
        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 2);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    private Boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    @Test
    public void testAmazonExample() {

        // Find "search" text field, type the word and hit "Enter"
        Actions a = new Actions(driver);
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("headphones" + Keys.ENTER);
//		a.moveToElement(driver.findElement(By.id("twotabsearchtextbox"))).click().sendKeys("Headphones" + Keys.ENTER)
//				.build().perform();

        // Find all Best Sellers products on page, open their page and add them
        // to cart
        List<WebElement> bestSellers = driver.findElements(
                By.xpath("//span[text()='Best Seller']/ancestor::div[@class='a-section a-spacing-medium']//img[@class='s-image']/ancestor::a"));

        for ( WebElement link : bestSellers) {
            String href = link.getAttribute("href");
            String primaryWindow = openInNewWindow(href);

            // in new window
            driver.findElement(By.id("add-to-cart-button")).click();

            // Popups handling
            try {
                wait.until(ExpectedConditions
                        .visibilityOfAllElementsLocatedBy(By.xpath("//*[contains(text(),'Added to Cart')]")));
            } catch (TimeoutException e) {
                if (isElementPresent(By.id("siNoCoverage-announce")))
                    driver.findElement(By.id("siNoCoverage-announce")).click();
                else if (isElementPresent(By.id("attachSiNoCoverage-announce")))
                    driver.findElement(By.id("attachSiNoCoverage-announce")).click();
                else if (isElementPresent(By.xpath("//a[@id='attach-close_sideSheet-link']")))
                    driver.findElement(By.xpath("//a[@id='attach-close_sideSheet-link']")).click();
            }

            driver.close();
            driver.switchTo().window(primaryWindow);
        }
        driver.findElement(By.id("nav-cart")).click();
    }

    private String openInNewWindow(String href) {

        String jsScript = "window.open(arguments[0]);";

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(jsScript, href);

        String original_window = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();

        windows.remove(original_window);

        driver.switchTo().window(windows.iterator().next());
        return original_window;
    }
}
