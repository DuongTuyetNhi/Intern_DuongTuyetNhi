package exercise8.pageObject;

import exercise8.base.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SuccessPage extends BasePage{
    public SuccessPage (WebDriver driver){
        super(driver);
    }

    String xpathInforTicket = "//table//tr[td[text()='%s' and following-sibling::td[text()='%s' and following-sibling::td[text()='%s' and following-sibling::td[text()='%s' and following-sibling::td[text()='%s']]]]]]";

    public String getSuccessfulMsg(){
        By msgSuccessful = By.xpath("//*[@id='content']/h1");
        String actualMsg = Config.driver.findElement(msgSuccessful).getText();
        return actualMsg;
    }

    public boolean checkInforTicket(String departFrom, String arriveAt, String seatType, String departDate, String amountTicket){
        By inforTicket = By.xpath(String.format(xpathInforTicket, departFrom, arriveAt, seatType, departDate, amountTicket));
        boolean i = Config.driver.findElement(inforTicket).isDisplayed();
        return i;
    }
}
