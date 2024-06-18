package pageObject;

import base.DriverManagement;
import org.openqa.selenium.By;

import static base.DriverManagement.*;

public class MailPage extends BasePage{
    private By checkbox = By.id("use-alias");
    private By registerEmail = By.id("email-widget");
    private By txtEmailConfirm = By.xpath("//table[@id='email_table']//tr[contains(@class, 'mail')]//td[contains(.,'Please confirm your account')]//span");
    private By txtLinkConfirm = By.xpath("//*[@id='display_email']//a[contains(@href,'Confirm')]");
    private By btnMail = By.xpath("//*[@id='inbox-id']");
    private By txtMailName = By.xpath("//*[@id='inbox-id']/input");
    private By btnSet = By.xpath("//*[@id='inbox-id']/button[@class='save button small']");
    private By sltDomainName = By.xpath("//select[@id='gm-host-select']");
    private By txtEmailReset = By.xpath("//table[@id='email_table']//tr[contains(@class, 'mail')]//td[contains(.,'Please reset your password')]//span");
    private By txtLinkReset = By.xpath("//*[@id='display_email']//a[contains(@href,'PasswordReset')]");

    public String getEmail(){
        driver.findElement(checkbox).click();
        String email = driver.findElement(registerEmail).getText();
        return email;
    }

    public void confirmAccount(){
        DriverManagement.waitElement(txtEmailConfirm, 25);
        click(txtEmailConfirm);

        DriverManagement.waitElement(txtLinkConfirm, 25);
        click(txtLinkConfirm);
    }

    public void loginToEmail(String mailName, String domainName){
        click(btnMail);
        enter(txtMailName, mailName);
        click(btnSet);
        enter(sltDomainName, domainName);
    }
    public void resetPassword(){
        DriverManagement.waitElement(txtEmailReset, 25);
        click(txtEmailReset);

        DriverManagement.waitElement(txtLinkReset, 25);
        click(txtLinkReset);
    }
}
