package exercise8.pageObject;

import exercise8.base.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage{
    public final By txtEmail = By.id("email");
    public final By txtPassword = By.id("password");
    public final By txtConfirmPassword = By.id("confirmPassword");
    public final By txtPID = By.id("pid");
    public final By btnRegister = By.xpath("//*[@id='RegisterForm']//p/input[@type='submit']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }
    public void submitRegisterForm(String email, String password, String confirmPassword, String pid){
        Config.driver.findElement(txtEmail).sendKeys(email);
        Config.driver.findElement(txtPassword).sendKeys(password);
        Config.driver.findElement(txtConfirmPassword).sendKeys(confirmPassword);
        Config.driver.findElement(txtPID).sendKeys(pid);
    }

    public String getUsername(){
        String txtUsernameLogin = Config.driver.findElement(txtEmail).getAttribute("value");
        return txtUsernameLogin;
    }
    public String getPassword(){
        String txtPasswordLogin = Config.driver.findElement(txtPassword).getAttribute("value");
        return txtPasswordLogin;
    }

    public void clickBtnRegister(){
        scrollToFindElement("//input[@type='submit']");
        Config.driver.findElement(btnRegister).click();
    }
}
