package pageObject;

import org.openqa.selenium.By;
import static base.DriverManagement.driver;

public class RegisterPage extends BasePage{
    public final By txtEmail = By.id("email");
    public final By txtPassword = By.id("password");
    public final By txtConfirmPassword = By.id("confirmPassword");
    public final By txtPID = By.id("pid");
    public final By btnRegister = By.xpath("//*[@id='RegisterForm']//p/input[@type='submit']");
    public final By msgError = By.xpath("//*[@id='content']/p[@class='message error']");
    public final By msgSuccess = By.xpath("//*[@id='content']/h1[@align='center']");
    public final By msgConfirmSuccess = By.xpath("//*[@id='content']/p");
    String validationError = "//*[@id='RegisterForm']//label[@for='%s' and @class='validation-error']";

    public void fillRegisterForm(String email, String password, String confirmPassword, String pid){
        driver.findElement(txtEmail).sendKeys(email);
        driver.findElement(txtPassword).sendKeys(password);
        driver.findElement(txtConfirmPassword).sendKeys(confirmPassword);
        driver.findElement(txtPID).sendKeys(pid);
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
        scrollToFindElement("//input[@type='submit']");
        driver.findElement(btnRegister).click();
    }

    public void createAnAccount(String username, String password, String pid){
        driver.findElement(txtEmail).sendKeys(username);
        driver.findElement(txtPassword).sendKeys(password);
        driver.findElement(txtConfirmPassword).sendKeys(password);
        driver.findElement(txtPID).sendKeys(pid);
    }

    public String getErrorMsg(){
        return driver.findElement(msgError).getText();
    }

    public String getValidationPasswordError(){
        By passwordError = By.xpath(String.format(validationError, "password"));
        return driver.findElement(passwordError).getText();
    }

    public String getValidationPIDError(){
        By pidError = By.xpath(String.format(validationError, "pid"));
        return driver.findElement(pidError).getText();
    }

    public boolean checkMessageDisplay(){
        return driver.findElement(msgSuccess).isDisplayed();
    }
    public boolean checkMessageConfirmDisplay(){
        return driver.findElement(msgConfirmSuccess).isDisplayed();
    }
}
