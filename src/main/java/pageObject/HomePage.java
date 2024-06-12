package pageObject;

import base.Constants;
import org.openqa.selenium.By;

import static base.DriverManagement.driver;

public class HomePage extends BasePage{
    public HomePage open(){
        driver.get(Constants.railway_url);
        return this;
    }

    String xpathWelcomeMsg = "//*[@id='content']/h1[text()='%s']";
    By msgWelcome = By.xpath("//*[@id='banner']/div[@class='account']/strong");
    By linkCreateAccount = By.xpath("//*[@id='content']//a[contains(@href,'Register')]");

    public String getWelcomeMsg(){
        return driver.findElement(msgWelcome).getText();
    }

    public boolean checkHomepageIsDisplay(){
        String welcomeTitle = "Welcome to Safe Railway";
        By welcome = By.xpath(String.format(xpathWelcomeMsg, welcomeTitle));
        return driver.findElement(welcome).isDisplayed();
    }

    public void clickCreateAnAccountLink(){
        driver.findElement(linkCreateAccount).click();
    }
}
