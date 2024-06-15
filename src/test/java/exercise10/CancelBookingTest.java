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
import pageObject.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CancelBookingTest extends BaseTest {
    protected HomePage homePage = new HomePage();
    protected LoginPage loginPage = new LoginPage();
    protected BookTicketPage bookTicketPage = new BookTicketPage();
    protected SuccessPage successPage = new SuccessPage();
    protected MyTicketPage myTicketPage = new MyTicketPage();

    protected DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
    protected String getDateAdd(int number){
        return dateTimeFormatter.format((LocalDate.now()).plusDays(number));
    }

    protected String username = "nhiagest@grr.la";
    protected String password = "12345678";
    protected String departDate = getDateAdd(5);

    User validUser = new User(username, password);
    Ticket ticketBook = new Ticket(departDate, DepartFrom.SAI_GON, ArriveAt.PHAN_THIET, SeatType.HB, Amount.TWO);
    Ticket ticket = new Ticket(DepartFrom.SAI_GON, ArriveAt.PHAN_THIET, SeatType.HB, departDate, Amount.TWO);
    @Test(description = "User can cancel a ticket")
    public void CancelTicket(){
        DriverManagement.open();
        homePage.gotoTab("Login");
        loginPage.submitLoginForm(validUser);

        homePage.gotoTab("Book ticket");
        bookTicketPage.bookTicket(ticketBook);
        bookTicketPage.clickBookTicketButton();

        successPage.gotoTab("My ticket");
        myTicketPage.cancelTicket(ticket);
        myTicketPage.confirmCancel();
        Assert.assertTrue(myTicketPage.checkTicketDisappear(ticket),"The ticket does not disappear.");

    }
}
