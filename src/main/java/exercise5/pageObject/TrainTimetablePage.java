package exercise5.pageObject;

import exercise5.base.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TrainTimetablePage extends BasePage{

    String bookTicketLink = "//tr[td[text()='%d' and following-sibling::td[text()='%a']]]//a[contains(@href, 'BookTicketPage')]";

    public TrainTimetablePage(WebDriver driver){
        super(driver);
    }

    public void selectTicketToBook(String departFrom, String arriveAt){
        By linkBookTicket = By.xpath(String.format(bookTicketLink, departFrom, arriveAt));
        Config.driver.findElement(linkBookTicket).click();
    }

}
