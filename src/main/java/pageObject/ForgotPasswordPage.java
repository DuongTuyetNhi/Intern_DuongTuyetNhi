package pageObject;

import org.openqa.selenium.By;
import static base.DriverManagement.*;

public class ForgotPasswordPage extends BasePage{
    private By xpathEmail = By.id("email");
    private By btnSend = By.xpath("//input[@value='Send Instructions']");

    public void enterEmailAddress(String email){
        enter(xpathEmail, email);
    }
    public void clickSendInstructionsBtn(){
        click(btnSend);
    }

}
