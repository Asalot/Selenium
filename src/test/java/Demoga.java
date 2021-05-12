import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.*;
import org.testng.annotations.Test;
import java.util.Random;


public class Demoga extends utils {

    public void checkDismissMessage() throws InterruptedException {
        try {
            WebElement el = driver.findElement(By.xpath("/html/body/p/a"));
            el.click();
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }


//    public String setDesc(String desc){
//        utils.description=desc;
//        return desc;
//    }

    @Test(description = "1. Verify if user can create new account", priority = 1, suiteName = "Positive")
    public void PositiveSignUpTest() throws InterruptedException {
        System.out.println("1. Verify if user can create new account");
        Random randomInt = new Random();
        utils.number = String.valueOf(randomInt.nextInt(1000000));
        driver.get("http://shop.demoqa.com");
        Thread.sleep(1000);
        checkDismissMessage();
        driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul[2]/li[2]/a")).click();
        driver.findElement(By.id("reg_username")).sendKeys("demoga" + utils.number);
        driver.findElement(By.id("reg_password")).sendKeys(utils.number);
        driver.findElement(By.id("reg_email")).sendKeys("demoga" + utils.number + "@gmail.com");
        driver.findElement(By.name("register")).click();
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[1]/h1/a")).getText().toLowerCase().contains("wordpress"));
        System.out.println("Created random number: " + number);


    }

    @Test(dependsOnMethods = "PositiveSignUpTest", priority = 2, suiteName = "Positive")
    public void PositiveSignInTest() throws InterruptedException {
        //   utils.description="2.Verify if New user can log in with new credentials";
        System.out.println("2.Verify if New user can log in with new credentials");
        driver.get("http://shop.demoqa.com");
        Thread.sleep(1000);
        checkDismissMessage();
        driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul[2]/li[2]/a")).click();
        driver.findElement(By.id("username")).sendKeys("demoga" + utils.number);
        driver.findElement(By.id("password")).sendKeys(utils.number);
        driver.findElement(By.name("login")).click();
        Assert.assertEquals(driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/article/div/div/nav/ul/li[1]/a")).getText(), "Dashboard");
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/article/div/div/nav/ul/li[6]/a")).click();
    }

    public void negativeTamplateForEmailPasswordUsername(String value) throws InterruptedException {
        driver.get("http://shop.demoqa.com");
        Thread.sleep(1000);
        checkDismissMessage();
        driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul[2]/li[2]/a")).click();
        Random randomInt = new Random();
        String number = String.valueOf(randomInt.nextInt(1000000));
        if (isPasswordEmailUsername.equals("username")) driver.findElement(By.id("reg_username")).sendKeys(value);
        else driver.findElement(By.id("reg_username")).sendKeys("demoga" + number);
        if (isPasswordEmailUsername.equals("email")) driver.findElement(By.id("reg_email")).sendKeys(value);
        else driver.findElement(By.id("reg_email")).sendKeys("demoga" + number + "@gmail.com");
        if (isPasswordEmailUsername.equals("password")) driver.findElement(By.id("reg_password")).sendKeys(value);
        else driver.findElement(By.id("reg_password")).clear();
        driver.findElement(By.name("register")).click();
    }

    @Test(priority = 3, suiteName = "Negative")
    public void NegativeBlankEmailSignUpTest() throws InterruptedException {
        System.out.println("3.Verify if there is error message with blank email");
        utils.isPasswordEmailUsername = "email";
        negativeTamplateForEmailPasswordUsername("");
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/article/div/div/div[1]/ul/li/strong")).getText().contains("Error:") &&
                driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/article/div/div/div[1]/ul/li")).getText().contains("valid email"));
    }

    @Test(priority = 4, suiteName = "Negative", expectedExceptions = NoSuchElementException.class)
    public void NegativeWrongEmailSignUpTest() throws InterruptedException {
        System.out.println("4.Verify if there is error message with wrong email");
        utils.isPasswordEmailUsername = "email";
        negativeTamplateForEmailPasswordUsername("1");
        WebElement el = driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/article/div/div/nav/ul/li[1]/a"));
    }

    @Test(dependsOnMethods = "PositiveSignUpTest", priority = 5, suiteName = "Negative")
    public void NegativeDuplicateEmailSignUpTest() throws InterruptedException {
        System.out.println("5.Verify if there is error message with duplicate email");
        utils.isPasswordEmailUsername = "email";
        negativeTamplateForEmailPasswordUsername("demoga" + utils.number + "@gmail.com");
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/article/div/div/div[1]/ul/li")).getText().contains("Error") &&
                driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/article/div/div/div[1]/ul/li")).getText().contains("already registered"));
    }

    //------------------------------------------------------------------

    @Test(priority = 6, suiteName = "Negative")
    public void NegativeBlankPasswordSignUpTest() throws InterruptedException {
        System.out.println("6.Verify if there is error message with blank password");
        utils.isPasswordEmailUsername = "password";
        negativeTamplateForEmailPasswordUsername("");
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/article/div/div/div[1]/ul/li")).getText().contains("Error:") &&
                driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/article/div/div/div[1]/ul/li")).getText().contains("account password"));
    }

    @Test(priority = 7, suiteName = "Negative")
    public void NegativePasswordLess12SignUpTest() throws InterruptedException {
        System.out.println("7.Verify if there is error message with password length less then 12");
        utils.isPasswordEmailUsername = "password";
        negativeTamplateForEmailPasswordUsername("12345678912");
        Assert.assertFalse(driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/article/div/div/div[2]/div[2]/form/p[3]/span/div")).getText().contains("Very weak"));
    }

    //------------------------------------------------------------------
    @Test(priority = 8, suiteName = "Negative")
    public void NegativeBlankUsernameSignUpTest() throws InterruptedException {
        System.out.println("8.Verify if there is error message with blank username");
        utils.isPasswordEmailUsername = "username";
        negativeTamplateForEmailPasswordUsername("");
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/article/div/div/div[1]/ul/li/strong")).getText().contains("Error:") &&
                driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/article/div/div/div[1]/ul/li")).getText().contains("valid account username"));
    }

    @Test(dependsOnMethods = "PositiveSignUpTest", priority = 9, suiteName = "Negative")
    public void NegativeDuplicateUsernameSignUpTest() throws InterruptedException {
        System.out.println("9.Verify if there is error message with duplicate username");
        utils.isPasswordEmailUsername = "username";
        negativeTamplateForEmailPasswordUsername("demoga" + number);
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/article/div/div/div[1]/ul/li")).getText().contains("Error") &&
                driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/article/div/div/div[1]/ul/li")).getText().contains("registered with that username"));
    }


}
