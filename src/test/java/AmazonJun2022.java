import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class AmazonJun2022 {
    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "browsers/chromedriver");
        driver = new ChromeDriver();
        String baseURL = "https://www.amazon.com";
        WebDriverWait wait = new WebDriverWait(driver, 5);

        driver.get(baseURL);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String jsScript = "window.open(arguments[0]);";

        driver.findElement(By.xpath("//*[@id='twotabsearchtextbox']")).sendKeys("headphones" + Keys.ENTER);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//span[text()='Best Seller']/ancestor::div[@class='a-section a-spacing-none s-badge-spacing']/../..//div[@class='aok-relative']//a")));

        List<WebElement> bestSellers = driver.findElements(By.xpath(
                "//span[text()='Best Seller']/ancestor::div[@class='a-section a-spacing-none s-badge-spacing']/../..//div[@class='aok-relative']//a"));
        System.out.println("no of bestsellers found: " + bestSellers.size());

        String original_window = driver.getWindowHandle();

        for (WebElement element : bestSellers) {
            String href = element.getAttribute("href");

            js.executeScript(jsScript, href);
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));

            Set<String> windows = driver.getWindowHandles();
            windows.remove(original_window);
            driver.switchTo().window(windows.iterator().next());

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='add-to-cart-button']"))).click();

            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Added to Cart')]")));
            } catch (TimeoutException e) {
                driver.findElement(By.xpath("//*[@aria-labelledby='attachSiNoCoverage-announce']")).click();
            }

            driver.close();
            driver.switchTo().window(original_window);
        }

        driver.findElement(By.xpath("//*[@id='nav-cart']")).click();
    }

    private static boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }
}