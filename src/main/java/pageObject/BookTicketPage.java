package pageObject;

import base.DriverManagement;
import models.Ticket;
import org.openqa.selenium.By;

import static base.DriverManagement.*;

public class BookTicketPage extends BasePage{
    private String infor = "//*[@id='content']//select[@name='%s']";
    private By btnBookTicket = By.xpath("//form//input[@type = 'submit']");

    public void selectInfor(String item, String information){
        By selectItem = By.xpath(String.format(infor, item));
        enter(selectItem, information);
    }

    public void bookTicket(Ticket ticket){
        selectInfor("DepartStation", ticket.getDepartFrom().getValueDepartFrom());
        selectInfor("Date", ticket.getDepartDate());
        selectInfor("SeatType", ticket.getSeatType().getValueSeatType());
        selectInfor("TicketAmount", ticket.getAmount().getValueAmount());
        selectInfor("ArriveStation", ticket.getArriveAt().getValueArriveAt());

    }

    public void clickBookTicketButton(){
        DriverManagement.scrollToFindElement("//form//input[@type = 'submit']");
        click(btnBookTicket);
    }

}
