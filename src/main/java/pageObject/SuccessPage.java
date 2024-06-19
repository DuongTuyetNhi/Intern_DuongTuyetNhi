package pageObject;

import models.Ticket;
import org.openqa.selenium.By;

import static base.DriverManagement.driver;
import static base.DriverManagement.getText;

public class SuccessPage extends BasePage{
    private String xpathInforTicket = "//table//tr[td[text()='%s' and following-sibling::td[text()='%s' and following-sibling::td[text()='%s' and following-sibling::td[text()='%s' and following-sibling::td[text()='%s']]]]]]";

    public String getSuccessfulMsg(){
        By msgSuccessful = By.xpath("//*[@id='content']/h1");
        return getText(msgSuccessful);
    }

    public boolean checkInforTicket(Ticket ticket){
        By inforTicket = By.xpath(String.format(xpathInforTicket, ticket.getDepartFrom().getValueLocation(), ticket.getArriveAt().getValueLocation(),
                ticket.getSeatType().getValueSeatType(), ticket.getDepartDate(), ticket.getAmount()));
        return driver.findElement(inforTicket).isDisplayed();
    }

    public String getTicketId(){
        String urlBookTicket = driver.getCurrentUrl();
        String idTicket = urlBookTicket.split("id=")[1];
        return idTicket;
    }
}
