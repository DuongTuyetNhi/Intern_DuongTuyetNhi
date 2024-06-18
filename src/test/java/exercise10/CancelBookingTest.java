package exercise10;

import BaseTest.BaseTest;
import base.DriverManagement;
import enums.*;
import models.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CancelBookingTest extends BaseTest {
    HomePage homePage = new HomePage();
    LoginPage loginPage = new LoginPage();
    BookTicketPage bookTicketPage = new BookTicketPage();
    SuccessPage successPage = new SuccessPage();
    MyTicketPage myTicketPage = new MyTicketPage();

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
    String getDateAdd(int number){
        return dateTimeFormatter.format((LocalDate.now()).plusDays(number));
    }

    String username = "nhiagest@grr.la";
    String password = "12345678";
    String departDate = getDateAdd(5);

    User validUser = new User(username, password);
    Ticket ticket = new Ticket(departDate, Locations.SAI_GON, Locations.PHAN_THIET, SeatType.HB, Amount.TWO);
    @Test(description = "User can cancel a ticket")
    public void CancelTicket(){
        DriverManagement.open();
        homePage.openLoginTab();
        loginPage.submitLoginForm(validUser);

        homePage.openTab("Book ticket");
        bookTicketPage.bookTicket(ticket);
        bookTicketPage.clickBookTicketButton();

        successPage.openTab("My ticket");
        myTicketPage.cancelTicket(ticket);
        myTicketPage.confirmCancel();
        Assert.assertTrue(myTicketPage.checkTicketDisappear(ticket),"The ticket does not disappear.");

    }
}
