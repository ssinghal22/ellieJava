import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.lang.Thread.sleep;

public class GoogleNewWindow {

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

    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@class='yuRUbf']/a")));
    List<WebElement> list = driver.findElements(By.xpath("//*[@class='yuRUbf']/a"));
    String href = list.get(2).getAttribute("href");
    String jsScript = "window.open(arguments[0]);";

    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript(jsScript, href);
    }
}
