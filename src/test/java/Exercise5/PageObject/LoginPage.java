package Exercise5.PageObject;

import Exercise5.Base.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    By txtLoginUsername = By.id("username");
    By txtLoginPassword = By.id("password");
    By btnLogin = By.xpath("//input[@type='submit']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void submitLoginForm(String username, String password){
        Config.driver.findElement(txtLoginUsername).sendKeys(username);
        Config.driver.findElement(txtLoginPassword).sendKeys(password);
        Config.driver.findElement(btnLogin).click();
    }
}
