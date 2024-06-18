package pageObject;

import base.DriverManagement;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

import static base.DriverManagement.*;

public class LoginPage extends BasePage{

    private By txtLoginUsername = By.id("username");
    private By txtLoginPassword = By.id("password");
    private By btnLogin = By.xpath("//input[@type='submit']");
    private By msgError = By.xpath("//*[@id='content']/p[@class='message error LoginForm']");
    private By linkForgotPassword = By.xpath("//*[@id='content']//a[contains(@href,'ForgotPassword')]");
    private By txtNewPassword = By.id("newPassword");
    private By txtConfirmPassword = By.id("confirmPassword");
    private By txtPasswordResetToken = By.id("resetToken");
    private By btnResetPassword = By.xpath("//form//input[@type='submit']");
    private By msgReset = By.xpath("//*[@id='content']/p[contains(@class,'message')]");
    private By msgConfirmPassword = By.xpath("//*[@id='content']//label[@class='validation-error' and @for='confirmPassword']");

    public void submitLoginForm(User user){
        enter(txtLoginUsername, user.getUsername());
        enter(txtLoginPassword, user.getPassword());
        DriverManagement.scrollToFindElement(btnLogin);
        click(btnLogin);
    }

    public void loginInSeveralTime(User user, int times){
        for (int i = 1; i <= times; i++){
            submitLoginForm(user);
        }
    }

    public String getErrorMsg(){
        return getText(msgError);
    }

    public void clickForgotPassword(){
        click(linkForgotPassword);
    }
    public void fillResetPasswordForm(String newPassword, String confirmPassword){
        enter(txtNewPassword, newPassword);
        enter(txtConfirmPassword, confirmPassword);
    }
    public void clickResetPasswordBtn(){
        click(btnResetPassword);
    }
    public boolean checkTokenIsDisplay(){
        List<WebElement> token = driver.findElements(txtPasswordResetToken);
        return !token.isEmpty();
    }
    public String getResetMsg(){
        return getText(msgReset);
    }
    public String getConfirmPasswordMsg(){
        return getText(msgConfirmPassword);
    }

}
