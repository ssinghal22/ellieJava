import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class AmazonInterviewChallenge {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "browsers/chromedriver");
        WebDriver driver = new ChromeDriver();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, 5);

        driver.get("https://www.amazon.com/");
        driver.findElement(By.xpath("//*[@id='twotabsearchtextbox']")).sendKeys("headphones");
        driver.findElement(By.xpath("//*[@value='Go']")).click();

      // List<WebElement> bestSellers = driver.findElements(By.xpath("//span[text()='Best Seller']/ancestor::div[@class='a-section a-spacing-medium']//img[@class='s-image']"));

//       String originalWindow = driver.getWindowHandle();
//
//       for (WebElement bestSeller: bestSellers){
//          // System.out.println(bestSeller);
//
//           new Actions(driver).moveToElement(bestSeller).keyDown(Keys.COMMAND).click(bestSeller).build().perform();
//
//           Set<String> windows = driver.getWindowHandles();
//           for(String window: windows) {
//               driver.switchTo().window(window);
//           }
//            System.out.println(driver.getWindowHandle());
//
//           driver.findElement(By.xpath("//*[@id='add-to-cart-button']")).click();
//           driver.switchTo().window(originalWindow);
//       }

       //driver.quit();
        //click each best seller without having the bestseller web elements above going stale

         List<WebElement> bestSellers = driver.findElements(By.xpath("//span[text()='Best Seller']/ancestor::div[@class='a-section a-spacing-medium']//img[@class='s-image']/ancestor::a"));
//*[text()='Best Seller']/ancestor::*[@class='a-section a-spacing-base']
         String original_window = driver.getWindowHandle();

        for (WebElement item: bestSellers){
            String href = item.getAttribute("href");

           String jsScript = "window.open(' " + href + "');"; //javascript doesn't know about href
            js.executeScript(jsScript);

            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
           Set<String> windows =  driver.getWindowHandles(); //order is not guaranteed
           windows.remove(original_window);

            driver.switchTo().window(windows.iterator().next());

            driver.findElement(By.xpath("//*[@id='add-to-cart-button']")).click();

            String success_message_locator = "#attachAddBaseItemSuccessAlert .a-alert-heading" ;

           if (isElementPresent(driver, By.cssSelector(".a-button-close .a-icon-close"))){
               wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("siNoCoverage-announce"))).click();
               driver.findElement(By.cssSelector(".a-button-close .a-icon-close")).click();
               success_message_locator = "#huc-v2-order-row-confirm-text h1";
           }
           else {
               wait.until(ExpectedConditions.presenceOfElementLocated(By.className("attach-primary-atc-confirm-box")));
           }

            String success = driver.findElement(By.cssSelector(success_message_locator)).getText();
            assert success.equals("Added to Cart");
            driver.close();
            driver.switchTo().window(original_window);
        }
    }

    private static boolean isElementPresent(WebDriver driver, By by) {
        try{
            new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(by));
            return true;
        }
        catch(NoSuchElementException e){
            return false;
        }
        //or can put return true; here
    }
}
