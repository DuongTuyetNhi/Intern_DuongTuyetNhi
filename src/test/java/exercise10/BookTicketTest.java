package exercise10;

import BaseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class BookTicketTest extends BaseTest {

    @Test(description = "User can book 1 ticket at a time")
    public void TC12(){
        homePage.open();
        homePage.gotoTab("Login");
        loginPage.submitLoginForm(username, password);

        homePage.gotoTab("Book ticket");
        String dateNext12 = getDateAdd(12);
        bookTicketPage.bookTicket(dateNext12,"Nha Trang", "Huế", "Soft bed with air conditioner", "2");
        bookTicketPage.clickBookTicketButton();

        String actualMsg = successPage.getSuccessfulMsg();
        String expectedMsg = "Ticket booked successfully!";
        Assert.assertEquals(actualMsg, expectedMsg, "The actual message is not the same as expected.");
        Assert.assertTrue(successPage.checkInforTicket("Nha Trang", "Huế","Soft bed with air conditioner",dateNext12, "2"));
    }

    @Test(description = "User can book many tickets at a time")
    public void TC13(){
        homePage.open();
        homePage.gotoTab("Login");
        loginPage.submitLoginForm(username, password);

        homePage.gotoTab("Book ticket");
        String dateNext25 = getDateAdd(25);
        bookTicketPage.bookTicket(dateNext25,"Nha Trang", "Sài Gòn", "Soft seat with air conditioner", "5");
        bookTicketPage.clickBookTicketButton();

        String actualMsg = successPage.getSuccessfulMsg();
        String expectedMsg = "Ticket booked successfully!";
        Assert.assertEquals(actualMsg, expectedMsg, "Message display is not same");
        Assert.assertTrue(successPage.checkInforTicket("Nha Trang", "Sài Gòn","Soft seat with air conditioner",dateNext25, "5"));
    }

    @Test(description = "User can check price of ticket from Timetable")
    public void TC14(){
        homePage.open();
        homePage.gotoTab("Login");
        loginPage.submitLoginForm(username, password);

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
    public void TC15(){
        homePage.open();
        homePage.gotoTab("Login");
        loginPage.submitLoginForm(username, password);

        homePage.gotoTab("Timetable");
        trainTimetablePage.selectTicketToBook("Quảng Ngãi", "Huế");

        String dateNext10 = getDateAdd(10);
        bookTicketPage.selectInfor("Date",dateNext10);
        bookTicketPage.selectInfor("TicketAmount", "5");
        bookTicketPage.clickBookTicketButton();

        String actualMsg = successPage.getSuccessfulMsg();
        String expectedMsg = "Ticket booked successfully!";
        Assert.assertEquals(actualMsg,expectedMsg,"Fail");
        Assert.assertTrue(successPage.checkInforTicket("Quảng Ngãi", "Huế",seatType, dateNext10, "5"));
    }
}
