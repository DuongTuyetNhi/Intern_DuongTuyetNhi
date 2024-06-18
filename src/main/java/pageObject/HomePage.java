package pageObject;

import org.openqa.selenium.By;

import static base.DriverManagement.click;
import static base.DriverManagement.driver;

public class HomePage extends BasePage{
    private String xpathWelcomeMsg = "//div[@id='content']/h1[text()='%s']";
    private By msgWelcome = By.xpath("//div[@id='banner']/div[@class='account']/strong");
    private By linkCreateAccount = By.xpath("//div[@id='content']//a[contains(@href,'Register')]");

    public String getWelcomeMsg(){
        return driver.findElement(msgWelcome).getText();
    }

    public boolean checkHomepageIsDisplay(){
        String welcomeTitle = "Welcome to Safe Railway";
        By welcome = By.xpath(String.format(xpathWelcomeMsg, welcomeTitle));
        return driver.findElement(welcome).isDisplayed();
    }

    public void clickCreateAnAccountLink(){
        click(linkCreateAccount);
    }
}
