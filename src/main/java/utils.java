import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class utils {

    static String number;
    static String description;
    static WebDriver driver;
    static String isPasswordEmailUsername;

    @AfterMethod
    public static void printRandomNumber() throws InterruptedException {
        Thread.sleep(1000);
    }

    @BeforeMethod
    public static void testSetup() {
        System.out.println("----------------------------------------------");
    }

    @BeforeTest(alwaysRun = true)
    public void setupSuite() {
        System.setProperty("webdriver.chrome.driver", "D:/testing/chromedriver.exe");
        driver = new ChromeDriver();
        System.out.println("Test is started");
        System.out.println("Driver is set up");
    }

    @AfterTest
    public void wrapUp() {
       driver.quit();
        System.out.println("Test is finished");
    }

}
