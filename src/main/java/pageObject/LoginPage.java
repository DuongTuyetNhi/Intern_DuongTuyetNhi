package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static base.DriverManagement.driver;

public class LoginPage extends BasePage{

    By txtLoginUsername = By.id("username");
    By txtLoginPassword = By.id("password");
    By btnLogin = By.xpath("//input[@type='submit']");
    By msgError = By.xpath("//*[@id='content']/p[@class='message error LoginForm']");
    By linkForgotPassword = By.xpath("//*[@id='content']//a[contains(@href,'ForgotPassword')]");


    By txtNewPassword = By.id("newPassword");
    By txtConfirmPassword = By.id("confirmPassword");
    By txtPasswordResetToken = By.id("resetToken");
    By btnResetPassword = By.xpath("//form//input[@type='submit']");
    By msgReset = By.xpath("//*[@id='content']/p[contains(@class,'message')]");
    By msgConfirmPassword = By.xpath("//*[@id='content']//label[@class='validation-error' and @for='confirmPassword']");

    public void submitLoginForm(String username, String password){
        driver.findElement(txtLoginUsername).sendKeys(username);
        driver.findElement(txtLoginPassword).sendKeys(password);
        scrollToFindElement("//input[@type='submit']");
        driver.findElement(btnLogin).click();
    }

    public String getErrorMsg(){
        return driver.findElement(msgError).getText();
    }

    public void clickForgotPassword(){
        driver.findElement(linkForgotPassword).click();
    }
    public void fillResetPasswordForm(String newPassword, String confirmPassword){
        driver.findElement(txtNewPassword).sendKeys(newPassword);
        driver.findElement(txtConfirmPassword).sendKeys(confirmPassword);
    }
    public void clickResetPasswordBtn(){
        driver.findElement(btnResetPassword).click();
    }
    public boolean checkTokenIsDisplay(){
        List<WebElement> token = driver.findElements(txtPasswordResetToken);
        return !token.isEmpty();
    }
    public String getResetMsg(){
        return driver.findElement(msgReset).getText();
    }
    public String getConfirmPasswordMsg(){
        return driver.findElement(msgConfirmPassword).getText();
    }

}
