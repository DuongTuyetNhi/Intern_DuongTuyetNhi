package pageObject;

import base.DriverManagement;
import models.User;
import org.openqa.selenium.By;

import static base.DriverManagement.*;

public class RegisterPage extends BasePage{
    private String validationError = "//*[@id='RegisterForm']//label[@for='%s' and @class='validation-error']";
    private By txtEmail = By.id("email");
    private By txtPassword = By.id("password");
    private By txtConfirmPassword = By.id("confirmPassword");
    private By txtPID = By.id("pid");
    private By btnRegister = By.xpath("//*[@id='RegisterForm']//p/input[@type='submit']");
    private By msgError = By.xpath("//*[@id='content']/p[@class='message error']");
    private By msgSuccess = By.xpath("//*[@id='content']/h1[@align='center']");
    private By msgConfirmSuccess = By.xpath("//*[@id='content']/p");

    public void fillRegisterForm(User user){
        enter(txtEmail, user.getUsername());
        enter(txtPassword, user.getPassword());
        enter(txtConfirmPassword, user.getPassword());
        enter(txtPID, user.getPid());
    }

    public String getUsername(){
        String txtUsernameLogin = driver.findElement(txtEmail).getAttribute("value");
        return txtUsernameLogin;
    }
    public String getPassword(){
        String txtPasswordLogin = driver.findElement(txtPassword).getAttribute("value");
        return txtPasswordLogin;
    }

    public void clickBtnRegister(){
        DriverManagement.scrollToFindElement(btnRegister);
        click(btnRegister);
    }


    public String getErrorMsg(){
        return getText(msgError);
    }

    public String getValidationPasswordError(){
        By passwordError = By.xpath(String.format(validationError, "password"));
        return getText(passwordError);
    }

    public String getValidationPIDError(){
        By pidError = By.xpath(String.format(validationError, "pid"));
        return getText(pidError);
    }

    public boolean checkMessageDisplay(){
        return driver.findElement(msgSuccess).isDisplayed();
    }
    public boolean checkMessageConfirmDisplay(){
        return driver.findElement(msgConfirmSuccess).isDisplayed();
    }
}
