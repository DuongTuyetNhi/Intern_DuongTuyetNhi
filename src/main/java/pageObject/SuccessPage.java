package pageObject;

import base.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static base.DriverManagement.driver;

public class SuccessPage extends BasePage{
//    public SuccessPage (WebDriver driver){
//        super(driver);
//    }

    String xpathInforTicket = "//table//tr[td[text()='%s' and following-sibling::td[text()='%s' and following-sibling::td[text()='%s' and following-sibling::td[text()='%s' and following-sibling::td[text()='%s']]]]]]";

    public String getSuccessfulMsg(){
        By msgSuccessful = By.xpath("//*[@id='content']/h1");
        String actualMsg = driver.findElement(msgSuccessful).getText();
        return actualMsg;
    }

    public boolean checkInforTicket(String departFrom, String arriveAt, String seatType, String departDate, String amountTicket){
        By inforTicket = By.xpath(String.format(xpathInforTicket, departFrom, arriveAt, seatType, departDate, amountTicket));
        boolean i = driver.findElement(inforTicket).isDisplayed();
        return i;
    }
}
