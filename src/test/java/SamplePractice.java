import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SamplePractice {

    WebDriver driver;
    WebDriverWait wait;
    String baseUrl = "https://skryabin.com/webdriver/html/sample.html";

    @Before
    public void setUp() throws Exception{
        System.setProperty("webdriver.chrome.driver", "browsers/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 2);
        driver.get(baseUrl);
    }

    @After
    public void tearDown() throws Exception{
        driver.quit();
    }

    @Test
    public void testSample(){
        driver.findElement(By.xpath("//*[@id = 'name']")).click();
        driver.findElement(By.xpath("//*[@id = 'firstName']")).sendKeys("soniya");
        driver.findElement(By.xpath("//*[@id = 'middleName']")).sendKeys("k");
        driver.findElement(By.xpath("//*[@id = 'lastName']")).sendKeys("singhal");
        driver.findElement(By.xpath("//*[text() = 'Save']")).click();
    }
}
