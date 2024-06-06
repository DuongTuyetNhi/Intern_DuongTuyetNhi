package exercise5.pageObject;

import exercise5.base.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BookTicketPage extends BasePage{
    public BookTicketPage(WebDriver driver){
        super(driver);
    }

    String infor = "//*[@id='content']//select[@name='%s']";
    By btnBookTicket = By.xpath("//form//input[@type = 'submit']");

    public void selectInfor(String item, String information){
        By selectItem = By.xpath(String.format(infor, item));
        Config.driver.findElement(selectItem).sendKeys(information);
    }

    public void bookTicket(String departDate, String departFrom, String arriveAt, String seatType, String ticketAmount){
        selectInfor("Date", departDate);
        selectInfor("DepartStation", departFrom);
        selectInfor("ArriveStation", arriveAt);
        selectInfor("SeatType", seatType);
        selectInfor("TicketAmount", ticketAmount);
        Config.driver.findElement(btnBookTicket).click();
    }

    public void clickBookTicketButton(){
        scrollToFindElement("//form//input[@type = 'submit']");
        Config.driver.findElement(btnBookTicket).click();
    }

}
