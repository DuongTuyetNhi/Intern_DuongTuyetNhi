package pageObject;

import base.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static base.DriverManagement.driver;

public class LoginPage extends BasePage{

    By txtLoginUsername = By.id("username");
    By txtLoginPassword = By.id("password");
    By btnLogin = By.xpath("//input[@type='submit']");
    By msgError = By.xpath("//*[@id='content']/p[@class='message error LoginForm']");


    public void submitLoginForm(String username, String password){
        driver.findElement(txtLoginUsername).sendKeys(username);
        driver.findElement(txtLoginPassword).sendKeys(password);
        scrollToFindElement("//input[@type='submit']");
        driver.findElement(btnLogin).click();
    }

    public String getErrorMsg(){
        return driver.findElement(msgError).getText();
    }

}
