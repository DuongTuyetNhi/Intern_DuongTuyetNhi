package Exercise5.PageObject;

import Exercise5.Base.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

        JavascriptExecutor js = (JavascriptExecutor) Config.driver;
        WebElement Element = Config.driver.findElement(By.xpath("//input[@type='submit']"));

        js.executeScript("arguments[0].scrollIntoView();", Element);

        Config.driver.findElement(btnRegister).click();
    }
}
