package pageObject;

import org.openqa.selenium.By;

import static base.DriverManagement.driver;

public class BookTicketPage extends BasePage{
    String infor = "//*[@id='content']//select[@name='%s']";
    By btnBookTicket = By.xpath("//form//input[@type = 'submit']");

    public void selectInfor(String item, String information){
        By selectItem = By.xpath(String.format(infor, item));
        driver.findElement(selectItem).sendKeys(information);
    }

    public void bookTicket(String departDate, String departFrom, String arriveAt, String seatType, String ticketAmount){
        selectInfor("Date", departDate);
        selectInfor("DepartStation", departFrom);
        selectInfor("ArriveStation", arriveAt);
        selectInfor("SeatType", seatType);
        selectInfor("TicketAmount", ticketAmount);

    }

    public void clickBookTicketButton(){
        scrollToFindElement("//form//input[@type = 'submit']");
        driver.findElement(btnBookTicket).click();
    }

}
