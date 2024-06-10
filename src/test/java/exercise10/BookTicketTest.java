package exercise10;

import BaseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;

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
        Assert.assertEquals(actualMsg, expectedMsg, "Message display is not same");
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

        Assert.assertTrue(ticketPricePage.checkTitleExist());

        String actualMsg = ticketPricePage.getTicketInfor();
        String expectedMsg = "Ticket price from Đà Nẵng to Sài Gòn";
        Assert.assertEquals(actualMsg, expectedMsg, "Ticket table shows displays incorrectly");

    }
}
