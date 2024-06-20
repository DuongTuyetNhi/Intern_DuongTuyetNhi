package pageObject;

import org.openqa.selenium.By;
import static base.DriverManagement.*;

public class TicketPricePage extends BasePage{

    private String typeSeat = "//table[@class='NoBorder']//tr[td[text()='%s']]//a";
    private String price = "//table[@class='MyTable MedTable']//th[normalize-space()='Price (VND)']//following-sibling::td[count(//td[text()='%s']/preceding-sibling::td)+1]";
    private By xpathTitle = By.xpath("//*[@id='content']/h1[text()='Ticket Price']");
    private By xpathTicketInfor = By.xpath("//table[@class='MyTable MedTable']//tr[@class='TableSmallHeader']/th");
    public void selectSeatType(String seatType){
        By txtSeatType = By.xpath(String.format(typeSeat, seatType));
        click(txtSeatType);
    }
    public boolean doesTitleExist(){
        return driver.findElement(xpathTitle).isDisplayed();
    }

    public String getTicketInfor(){
        return getText(xpathTicketInfor);
    }
    public String getPriceBySeatType(String seatType){
        By ticketPrice = By.xpath(String.format(price, seatType));
        return getText(ticketPrice);
    }
}
