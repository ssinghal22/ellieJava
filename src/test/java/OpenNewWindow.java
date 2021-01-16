import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

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

import static java.lang.Thread.sleep;

public class OpenNewWindow {
    WebDriver driver;
    WebDriverWait wait;
    String baseUrl = "https://google.com";

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
        //driver.quit();
    }

    @Test
    public void testGoogleExample() throws InterruptedException {
    driver.findElement(By.xpath("//*[@name='q']")).sendKeys("books" + Keys.ENTER);
    sleep(5000);
    String href = driver.findElement(By.xpath("(//h3[contains(@class,'LC20lb')])[7]/..")).
            getAttribute("href");

       String jsScript = "window.open(arguments[0]);";

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(jsScript, href);

        // below not needed. focus is at second tab from above line.
        String original_window = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();

        windows.remove(original_window);

        driver.switchTo().window(windows.iterator().next());
        sleep(10000);
    }
}
