package pageObject;

import base.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static base.DriverManagement.driver;

public class TrainTimetablePage extends BasePage{

    String bookTicketLink = "//tr[td[text()='%s' and following-sibling::td[text()='%s']]]//a[contains(@href, 'BookTicketPage')]";
    String checkPriceLink = "//tr[td[text()='%s' and following-sibling::td[text()='%s']]]//a[contains(@href, 'TicketPricePage')]";

//    public TrainTimetablePage(WebDriver driver){
//        super(driver);
//    }

    public void selectTicketToBook(String departFrom, String arriveAt){
        By linkBookTicket = By.xpath(String.format(bookTicketLink, departFrom, arriveAt));
        System.out.println(linkBookTicket);
        driver.findElement(linkBookTicket).click();
    }

    public void selectTicketToCheck(String departFrom, String arriveAt){
        By linkCheckPrice = By.xpath(String.format(checkPriceLink, departFrom, arriveAt));

        scrollToFindElement(String.format(checkPriceLink, departFrom, arriveAt));

        driver.findElement(linkCheckPrice).click();
    }

}
