package pageObject;

import org.openqa.selenium.By;

import static base.DriverManagement.driver;

public class ForgotPasswordPage extends BasePage{
    By xpathEmail = By.id("email");
    By btnSend = By.xpath("//input[@value='Send Instructions']");

    public void enterEmailAddress(String email){
        driver.findElement(xpathEmail).sendKeys(email);
    }
    public void clickSendInstructionsBtn(){
        driver.findElement(btnSend).click();
    }

}
