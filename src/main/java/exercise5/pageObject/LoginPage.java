package exercise5.pageObject;

import exercise5.base.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static exercise5.base.Config.driver;

public class LoginPage extends BasePage{

    By txtLoginUsername = By.id("username");
    By txtLoginPassword = By.id("password");
    By btnLogin = By.xpath("//input[@type='submit']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    BasePage basePage = new BasePage(driver);

    // get information from register

    public void submitLoginForm(String username, String password){
        Config.driver.findElement(txtLoginUsername).sendKeys(username);
        Config.driver.findElement(txtLoginPassword).sendKeys(password);
        scrollToFindElement("//input[@type='submit']");
        Config.driver.findElement(btnLogin).click();
    }
}
