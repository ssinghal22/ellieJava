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

public class WF1 {

    WebDriver driver;
    WebDriverWait wait;
    String baseUrl = "https://yahoo.com";

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

    @Test
    public void testYahoo(){
        List<WebElement> links = driver.findElements(By.xpath("//a"));
        for(WebElement link: links){
            System.out.println(link.getText());
        }
    }
}