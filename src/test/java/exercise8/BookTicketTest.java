package exercise8;

import exercise8.base.Config;
import exercise8.pageObject.*;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static exercise8.base.Config.driver;

public class BookTicketTest {

    private String emailLogin;
    private String passwordLogin;
    private String msgWelcome = "//*[@id='content']/h1[text()='Welcome to Safe Railway']";
    private String msgSuccess = "//*[@id='content']/h1";
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

        String password = "12345678";
        String pid = "12345678";

        RegisterPage registerPage = homePage.gotoTab("Register", RegisterPage.class);
        registerPage.submitRegisterForm(email, password, password, pid);
        emailLogin = registerPage.getUsername();
        passwordLogin = registerPage.getPassword();

        System.out.println("user: " + registerPage.getUsername());
        System.out.println("pass: " + registerPage.getPassword());
        registerPage.clickBtnRegister();
        Config.driver.switchTo().window(MailWindow);
        driver.navigate().refresh();

        mailPage.confirmAccount();

        driver.switchTo().window(RailwayWindow);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void TC02(){
        System.out.println("TC02 - User can book ticket from TrainTimetable");
        TC01();

        //Login

        BasePage basePage = new BasePage(driver);
        basePage.gotoTab("Login", LoginPage.class);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.submitLoginForm(emailLogin, passwordLogin);

        //Book ticket from Train Timetable
        HomePage homePage = new HomePage(driver);
        homePage.waitElement(msgWelcome);

        //Book ticket from Train Timetable
        homePage.gotoTab("Timetable", TrainTimetablePage.class);

        TrainTimetablePage trainTimetablePage = new TrainTimetablePage(driver);
        trainTimetablePage.selectTicketToCheck("Sài Gòn", "Đà Nẵng");

        TicketPricePage ticketPricePage = new TicketPricePage(driver);
        ticketPricePage.selectSeatType("Soft seat");

        BookTicketPage bookTicketPage = new BookTicketPage(driver);
        bookTicketPage.selectInfor("Date","6/13/2024");
        bookTicketPage.selectInfor("TicketAmount","2");
        bookTicketPage.clickBookTicketButton();

        bookTicketPage.waitElement(msgSuccess);

        SuccessPage successPage = new SuccessPage(driver);
        String actualMsg = successPage.getSuccessfulMsg();
        String expectedMsg = "Ticket booked successfully!";
        Assert.assertEquals(actualMsg,expectedMsg,"Fail");

        boolean check = successPage.checkInforTicket("Sài Gòn", "Đà Nẵng","Soft seat", "6/13/2024", "2");
        Assert.assertTrue(check);
    }

}
