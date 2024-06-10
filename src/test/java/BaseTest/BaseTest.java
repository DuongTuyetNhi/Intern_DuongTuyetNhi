package BaseTest;

import base.DriverManagement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageObject.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BaseTest {
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("Pre-condition");
        DriverManagement.driver = new ChromeDriver();
        DriverManagement.driver.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("Post-condition");
        DriverManagement.driver.quit();
    }


    protected String username = "nhiagest@grr.la";
    protected String password = "12345678";
    protected String pid = "12345678";
    protected DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
    protected String getDateAdd(int number){
        return dateTimeFormatter.format((LocalDate.now()).plusDays(number));
    }

    protected HomePage homePage = new HomePage();
    protected LoginPage loginPage = new LoginPage();
    protected RegisterPage registerPage = new RegisterPage();
    protected BookTicketPage bookTicketPage = new BookTicketPage();
    protected TrainTimetablePage trainTimetablePage = new TrainTimetablePage();
    protected TicketPricePage ticketPricePage = new TicketPricePage();
    protected SuccessPage successPage = new SuccessPage();
    protected MailPage mailPage = new MailPage();

}
