import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class WF2 {

    WebDriver driver;
    WebDriverWait wait;
    String baseUrl = "https://ticketmaster.com";

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "browsers/chromedriver");
        driver = new ChromeDriver();
        driver.get(baseUrl);
        Thread.sleep(5000);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 5);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void testTicketMaster() throws InterruptedException {

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@placeholder, 'City')]"))).sendKeys("San Franc");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@data-gtm1='San_Francisco_CA']"))).click();

        driver.findElement(By.xpath("//*[@form='SearchSuggestWithFilters']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='San Francisco, CA']")));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='All Dates']"))).click();

       wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@title='Date Range']"))).click();

        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//div[text()='Apr 2020']/..//button[contains(@class, 'dayToday')]"))).click().perform();

       actions.moveToElement(driver.findElement(By.xpath("//div[text()='Apr 2020']/..//button[contains(@class, 'dayEndOfMonth')]"))).click().perform();

       //actions.moveToElement(driver.findElement(By.xpath("//button[contains(@class, 'button--apply')]")), 1, 1).click().perform();

        WebElement applyButton = driver.findElement(By.xpath("//button[contains(@class, 'button--apply')]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", applyButton);

       Thread.sleep(5000);

       By cancelled = By.xpath("//div[@color='#d93a3a'][contains(@class, badge)]/ancestor::div[contains(@class, 'kSaEcM')]");
       wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(cancelled));
       List<WebElement> list = driver.findElements(cancelled);

        for(WebElement element: list){
            String[] textOfElementArr = element.getText().split("\\R");

            if((textOfElementArr[1].contains("pm")) && (textOfElementArr[1].charAt(6)>6) && (textOfElementArr[1].charAt(8) > 0)){
                for (String item: textOfElementArr){
                    System.out.println(item);
                }
            }
        }
    }
    }



