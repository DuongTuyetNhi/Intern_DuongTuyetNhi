package pageObject;

import base.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static base.DriverManagement.driver;

public class TicketPricePage extends BasePage{

    String typeSeat = "//table[@class='NoBorder']//tr[td[text()='%s']]//a";
    By xpathTitle = By.xpath("//*[@id='content']/h1[text()='Ticket Price']");
    By xpathTicketInfor = By.xpath("//table[@class='MyTable MedTable']//tr[@class='TableSmallHeader']/th");
    public void selectSeatType(String seatType){
        By txtSeatType = By.xpath(String.format(typeSeat, seatType));
        driver.findElement(txtSeatType).click();
    }
    public boolean checkTitleExist(){
        return driver.findElement(xpathTitle).isDisplayed();
    }

    public String getTicketInfor(){
        return driver.findElement(xpathTicketInfor).getText();
    }
}
