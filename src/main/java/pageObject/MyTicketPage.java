package pageObject;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static base.DriverManagement.driver;

public class MyTicketPage extends BasePage{
    String xpathCancelBtn = "//table[@class='MyTable']//input[@value='Cancel' and @onclick='DeleteTicket(%s);']";

    public void cancelTicket(String id){
        By cancelBtn = By.xpath(String.format(xpathCancelBtn, id));
        driver.findElement(cancelBtn).click();
    }

    public void confirmCancel(){
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public boolean checkTicketDisappear(String id){
        By cancelBtn = By.xpath(String.format(xpathCancelBtn, id));
        List<WebElement> tickets = driver.findElements(cancelBtn);
        return tickets.isEmpty();
    }

}
