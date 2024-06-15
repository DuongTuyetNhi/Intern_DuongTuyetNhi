package BaseTest;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static base.DriverManagement.driver;

public class BaseTest {
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("Pre-condition");
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("Post-condition");
        driver.quit();
    }
}
