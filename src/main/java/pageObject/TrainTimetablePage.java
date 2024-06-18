package pageObject;

import base.DriverManagement;
import org.openqa.selenium.By;

import static base.DriverManagement.click;

public class TrainTimetablePage extends BasePage{

    private String functionLink = "//tr[td[text()='%s' and following-sibling::td[text()='%s']]]//a[contains(@href, '%s')]";


    public void selectFunction(String departFrom, String arriveAt, String function){
        By linkFunction = By.xpath(String.format(functionLink, departFrom, arriveAt, function));
        DriverManagement.scrollToFindElement(linkFunction);
        click(linkFunction);
    }

}
