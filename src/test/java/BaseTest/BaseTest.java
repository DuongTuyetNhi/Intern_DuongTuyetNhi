package BaseTest;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageObject.*;

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

    protected HomePage homePage = new HomePage();
    protected LoginPage loginPage = new LoginPage();
    protected RegisterPage registerPage = new RegisterPage();
    protected BookTicketPage bookTicketPage = new BookTicketPage();
    protected TrainTimetablePage trainTimetablePage = new TrainTimetablePage();
    protected TicketPricePage ticketPricePage = new TicketPricePage();
    protected SuccessPage successPage = new SuccessPage();
    protected MyTicketPage myTicketPage = new MyTicketPage();
    protected MailPage mailPage = new MailPage();
    protected ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();

    protected DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
    protected String getDateAdd(int number){
        return dateTimeFormatter.format((LocalDate.now()).plusDays(number));
    }

    protected void pause(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected String username = "nhiagest@grr.la";
    protected String password = "12345678";
    protected String pid = "12345678";
    protected String departDate = getDateAdd(2);
    protected String departFrom = "Đà Nẵng";
    protected String arriveAt = "Sài Gòn";
    protected String seatType = "Hard seat";
    protected String amountTicket = "2";

    String[] str = username.split("@");
    protected String mailName = str[0];
    protected String domainName = str[1];

}
