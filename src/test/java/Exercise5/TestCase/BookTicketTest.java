package Exercise5.TestCase;

import Exercise5.Base.Config;
import Exercise5.PageObject.HomePage;
import Exercise5.PageObject.MailPage;
import Exercise5.PageObject.RegisterPage;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static Exercise5.Base.Config.driver;

public class BookTicketTest {

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("Pre-condition");
        Config.driver = new ChromeDriver();
        Config.driver.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("Post-condition");
        Config.driver.quit();
    }

    @Test
    public void TC01(){
        System.out.println("TC01 - User can register an account");

        MailPage mailPage = new MailPage();
        mailPage.openMailPage();
        String email = mailPage.getEmail();
        String MailWindow = driver.getWindowHandle();

        Config.driver.switchTo().newWindow(WindowType.TAB);

        HomePage homePage = new HomePage(driver);
        homePage.open();
        String RailwayWindow = driver.getWindowHandle();

        RegisterPage registerPage = homePage.gotoTab("Register", RegisterPage.class);
        registerPage.submitRegisterForm(email, "12345678", "12345678", "12345678");

        Config.driver.switchTo().window(MailWindow);

        mailPage.confirmAccount();

        //driver.switchTo().window(RailwayWindow);
    }

    @Test
    public void TC02(){
        System.out.println("TC02 - User can book ticket from TrainTimetable");


    }
}
