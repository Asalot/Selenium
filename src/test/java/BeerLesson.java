import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BeerLesson extends utils {

    @Test
    public void Test(){
        driver.get("http://www.99-bottles-of-beer.net/");
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div/div[3]/h2")).getText().equals("Welcome to 99 Bottles of Beer"));
   }





}
