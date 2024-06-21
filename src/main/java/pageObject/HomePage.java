package pageObject;

import org.openqa.selenium.By;
import static base.DriverManagement.click;
import static base.DriverManagement.driver;

public class HomePage extends BasePage{
    private String msgWelcome = "//div[@id='content']/h1[text()='%s']";
    private By msgWelcomeUser = By.xpath("//div[@id='banner']/div[@class='account']/strong");
    private By lnkCreateAccount = By.xpath("//div[@id='content']//a[contains(@href,'Register')]");

    public String getWelcomeMsg(){
        return driver.findElement(msgWelcomeUser).getText();
    }

    public boolean isHomePageDisplayed(){
        String welcomeTitle = "Welcome to Safe Railway";
        By welcome = By.xpath(String.format(msgWelcome, welcomeTitle));
        return driver.findElement(welcome).isDisplayed();
    }

    public void clickCreateAnAccountLink(){
        click(lnkCreateAccount);
    }
}
