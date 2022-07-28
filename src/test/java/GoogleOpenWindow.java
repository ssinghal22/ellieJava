import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleOpenWindow {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "browsers/chromedriver");
        WebDriver driver = new ChromeDriver();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, 5);

        driver.get("https://www.google.com/");

        driver.findElement(By.xpath("//*[@title='Search']")).sendKeys("books" + Keys.ENTER);
        WebElement book = driver.findElement(By.xpath("(//div[@class='g']//a/h3)[2]/.."));

        String href = book.getAttribute("href");

        String jsScript = "window.open('" + href + "');";
        js.executeScript(jsScript);
    }
}
