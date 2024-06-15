package exercise10;

import BaseTest.BaseTest;
import base.DriverManagement;
import enums.Amount;
import enums.ArriveAt;
import enums.DepartFrom;
import enums.SeatType;
import models.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObject.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookTicketTest extends BaseTest {
    protected HomePage homePage = new HomePage();
    protected LoginPage loginPage = new LoginPage();
    protected BookTicketPage bookTicketPage = new BookTicketPage();
    protected TrainTimetablePage trainTimetablePage = new TrainTimetablePage();
    protected TicketPricePage ticketPricePage = new TicketPricePage();
    protected SuccessPage successPage = new SuccessPage();
    protected DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
    protected String getDateAdd(int number){
        return dateTimeFormatter.format((LocalDate.now()).plusDays(number));
    }

    protected String username = "nhiagest@grr.la";
    protected String password = "12345678";
    protected String seatType = "Hard seat";
    User validUser = new User(username, password);

    @Test(description = "User can book 1 ticket at a time")
    public void BookATicket(){
        Ticket ticket1 = new Ticket(getDateAdd(12), DepartFrom.NHA_TRANG, ArriveAt.HUE, SeatType.SBC, Amount.TWO);
        Ticket ticket1a = new Ticket(DepartFrom.NHA_TRANG, ArriveAt.HUE, SeatType.SBC, getDateAdd(12), Amount.TWO);

        DriverManagement.open();
        homePage.gotoTab("Login");
        loginPage.submitLoginForm(validUser);

        homePage.gotoTab("Book ticket");
        bookTicketPage.bookTicket(ticket1);
        bookTicketPage.clickBookTicketButton();

        String actualMsg = successPage.getSuccessfulMsg();
        String expectedMsg = "Ticket booked successfully!";
        Assert.assertEquals(actualMsg, expectedMsg, "The actual message is not the same as expected.");
        Assert.assertTrue(successPage.checkInforTicket(ticket1a));
    }

    @Test(description = "User can book many tickets at a time")
    public void BookManyTicket(){
        Ticket ticket2 = new Ticket(getDateAdd(25), DepartFrom.NHA_TRANG, ArriveAt.SAI_GON, SeatType.SSC, Amount.FIVE);
        Ticket ticket2a = new Ticket(DepartFrom.NHA_TRANG, ArriveAt.SAI_GON, SeatType.SSC, getDateAdd(25), Amount.FIVE);
        DriverManagement.open();
        homePage.gotoTab("Login");
        loginPage.submitLoginForm(validUser);

        homePage.gotoTab("Book ticket");
        bookTicketPage.bookTicket(ticket2);
        bookTicketPage.clickBookTicketButton();

        String actualMsg = successPage.getSuccessfulMsg();
        String expectedMsg = "Ticket booked successfully!";
        Assert.assertEquals(actualMsg, expectedMsg, "Message display is not same");
        Assert.assertTrue(successPage.checkInforTicket(ticket2a));
    }

    @Test(description = "User can check price of ticket from Timetable")
    public void CheckPriceTicket(){
        DriverManagement.open();
        homePage.gotoTab("Login");
        loginPage.submitLoginForm(validUser);

        homePage.gotoTab("Timetable");
        trainTimetablePage.selectTicketToCheck("Đà Nẵng", "Sài Gòn");

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(ticketPricePage.checkTitleExist());

        String actualMsg = ticketPricePage.getTicketInfor();
        String expectedMsg = "Ticket price from Đà Nẵng to Sài Gòn";
        softAssert.assertEquals(actualMsg, expectedMsg, "The ticket table displays incorrectly.");

        String actualPriceOfHS = ticketPricePage.getPriceBySeatType("HS");
        String actualPriceOfSS = ticketPricePage.getPriceBySeatType("SS");
        String actualPriceOfSSC = ticketPricePage.getPriceBySeatType("SSC");
        String actualPriceOfHB = ticketPricePage.getPriceBySeatType("HB");
        String actualPriceOfSB = ticketPricePage.getPriceBySeatType("SB");
        String actualPriceOfSBC = ticketPricePage.getPriceBySeatType("SBC");

        String expectedPriceOfHS = "310000";
        String expectedPriceOfSS = "335000";
        String expectedPriceOfSSC = "360000";
        String expectedPriceOfHB = "410000";
        String expectedPriceOfSB = "460000";
        String expectedPriceOfSBC = "510000";

        softAssert.assertEquals(actualPriceOfHS, expectedPriceOfHS,"The price of HS is not the same as expected.");
        softAssert.assertEquals(actualPriceOfSS, expectedPriceOfSS,"The price of SS is not the same as expected.");
        softAssert.assertEquals(actualPriceOfSSC, expectedPriceOfSSC,"The price of SSC is not the same as expected.");
        softAssert.assertEquals(actualPriceOfHB, expectedPriceOfHB,"The price of HB is not the same as expected.");
        softAssert.assertEquals(actualPriceOfSB, expectedPriceOfSB,"The price of SB is not the same as expected.");
        softAssert.assertEquals(actualPriceOfSBC, expectedPriceOfSBC,"The price of SBC is not the same as expected.");

        softAssert.assertAll();
    }

    @Test(description = "User can book ticket from Timetable")
    public void BookTicketFromTimeTable(){
        Ticket ticket4a = new Ticket(DepartFrom.QUANG_NGAI, ArriveAt.HUE, SeatType.HS, getDateAdd(10), Amount.FIVE);

        DriverManagement.open();
        homePage.gotoTab("Login");
        loginPage.submitLoginForm(validUser);

        homePage.gotoTab("Timetable");
        trainTimetablePage.selectTicketToBook("Quảng Ngãi", "Huế");

        String dateNext10 = getDateAdd(10);
        bookTicketPage.selectInfor("Date",dateNext10);
        bookTicketPage.selectInfor("TicketAmount", "5");
        bookTicketPage.clickBookTicketButton();

        String actualMsg = successPage.getSuccessfulMsg();
        String expectedMsg = "Ticket booked successfully!";
        Assert.assertEquals(actualMsg,expectedMsg,"Fail");
        Assert.assertTrue(successPage.checkInforTicket(ticket4a));
    }
}
