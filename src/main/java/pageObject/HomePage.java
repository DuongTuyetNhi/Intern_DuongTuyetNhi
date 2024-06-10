package pageObject;

import base.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static base.DriverManagement.driver;

public class HomePage extends BasePage{
    public HomePage open(){
        driver.get(Constants.railway_url);
        return this;
    }

    String xpathWelcomeMsg = "//*[@id='content']/h1[text()='%s']";

    By linkCreateAccount = By.xpath("//*[@id='content']//a[contains(@href,'Register')]");

    public boolean checkWelcomeMsg(String expectedMsg){
        By msgWelcome = By.xpath(String.format(xpathWelcomeMsg,expectedMsg));
        return driver.findElement(msgWelcome).isDisplayed();
    }

    public void clickCreateAnAccountLink(){
        driver.findElement(linkCreateAccount).click();
    }
}
