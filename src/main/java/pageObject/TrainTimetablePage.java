package pageObject;

import base.DriverManagement;
import org.openqa.selenium.By;

import static base.DriverManagement.click;
import static base.DriverManagement.driver;

public class TrainTimetablePage extends BasePage{

    private String bookTicketLink = "//tr[td[text()='%s' and following-sibling::td[text()='%s']]]//a[contains(@href, 'BookTicketPage')]";
    private String checkPriceLink = "//tr[td[text()='%s' and following-sibling::td[text()='%s']]]//a[contains(@href, 'TicketPricePage')]";


    public void selectTicketToBook(String departFrom, String arriveAt){
        By linkBookTicket = By.xpath(String.format(bookTicketLink, departFrom, arriveAt));
        DriverManagement.scrollToFindElement(String.format(bookTicketLink, departFrom, arriveAt));
        click(linkBookTicket);
    }

    public void selectTicketToCheck(String departFrom, String arriveAt){
        By linkCheckPrice = By.xpath(String.format(checkPriceLink, departFrom, arriveAt));
        DriverManagement.scrollToFindElement(String.format(checkPriceLink, departFrom, arriveAt));
        click(linkCheckPrice);
    }

}
