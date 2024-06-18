package BaseTest;

import base.DriverManagement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import static base.DriverManagement.driver;

public class BaseTest {
    @BeforeMethod
    public void beforeMethod() throws Throwable{
        System.out.println("Pre-condition");
        DriverManagement.initDriver();
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("Post-condition");
        driver.quit();
    }
}
