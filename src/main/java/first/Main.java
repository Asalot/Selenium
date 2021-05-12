package first;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.*;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class Main {

    @Ignore
    public void Test() throws InterruptedException {

//        //  System.setProperty("webdriver.chrome.driver", "ChromeDriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/");
        WebElement el = driver.findElement(By.xpath("/html/body/header/nav/a[3]"));
   //     SoftAssert as=new SoftAssert();
  //      as.assertEquals(el.getText(),"Documentation");

        el.click();
        Thread.sleep(2000);
        el=driver.findElement(By.id("search-by"));
        el.sendKeys("element");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@data-title='Web element']")).click();
        Thread.sleep(2000);
        driver.findElement(By.tagName("h1"));
        Assert.assertEquals(driver.findElement(By.tagName("h1")).getText(),"Web element");


              driver.quit();


        //  System.out.println("sdsdsdsadx");


    }

}