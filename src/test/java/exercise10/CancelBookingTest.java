package exercise10;

import BaseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CancelBookingTest extends BaseTest {

    @Test(description = "User can cancel a ticket")
    public void TC16(){
        homePage.open();
        homePage.gotoTab("Login");
        loginPage.submitLoginForm(username, password);

        homePage.gotoTab("Book ticket");
        bookTicketPage.bookTicket(departDate,departFrom,arriveAt,seatType,amountTicket);
        bookTicketPage.clickBookTicketButton();

        String idTicket = successPage.getTicketId();
        System.out.println("id: " + successPage.getTicketId());

        successPage.gotoTab("My ticket");
        myTicketPage.cancelTicket(idTicket);
        myTicketPage.confirmCancel();
        Assert.assertTrue(myTicketPage.checkTicketDisappear(idTicket),"The ticket does not disappear.");

    }
}
