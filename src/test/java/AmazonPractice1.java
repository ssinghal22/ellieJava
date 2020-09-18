import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonPractice1 {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "browsers/chromedriver");
        WebDriver driver = new ChromeDriver();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, 5);

        driver.get("https://www.amazon.com/");


    }
}
