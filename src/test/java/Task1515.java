import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.List;


public class Task1515 extends utils{

    @Test (priority = 1)
    public void UsingSearchTest() throws InterruptedException {
        driver.get("https://www.webstaurantstore.com/");
        driver.findElement(By.id("searchval")).sendKeys("stainless work table\n");
    }
    @Test(priority = 2, dependsOnMethods = "UsingSearchTest")
    public void CheckResultsAddToCart() throws InterruptedException {

        WebElement NextButton=null;
        Boolean isEnd=true;
        List<WebElement> list=null;
        while (isEnd) {
            NextButton=driver.findElement(By.className("rc-pagination-next"));
            list = driver.findElements(By.xpath("//*[@id='product_listing']/div"));
            for (WebElement el : list) {
                try {
                    Assert.assertTrue(el.findElement(By.className("description")).getText().toLowerCase().contains("table"));
                } catch(AssertionError e) {
                    System.out.println(driver.getCurrentUrl() + "\n" + el.findElement(By.className("description")).getText());
                }
            }
            if (NextButton.getAttribute("aria-disabled").equals("true")) isEnd = false;
            else NextButton.click();
        }

       List<WebElement> listAddToCart = driver.findElements(By.xpath("//input[@type='submit']"));
        listAddToCart.get(listAddToCart.size()-1).click();
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@href='/viewcart.cfm']")));
     }
    @Test(priority = 3, dependsOnMethods = "CheckResultsAddToCart")
    public void EmptyCart() throws InterruptedException {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", driver.findElement(By.className("emptyCartButton")));
         executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@id='td']/div[6]/div/div/div/div[3]/button[1]")));

        Thread.sleep(1000);
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='header-1']")).getText().equals("Your cart is empty."));
   }
}
