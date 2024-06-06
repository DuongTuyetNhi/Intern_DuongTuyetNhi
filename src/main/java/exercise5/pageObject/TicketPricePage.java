package exercise5.pageObject;

import exercise5.base.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TicketPricePage extends BasePage{
    public TicketPricePage(WebDriver driver){
        super(driver);
    }

    String typeSeat = "//table[@class='NoBorder']//tr[td[text()='%s']]//a";

    public void selectSeatType(String seatType){
        By txtSeatType = By.xpath(String.format(typeSeat, seatType));
        Config.driver.findElement(txtSeatType).click();
    }
}
