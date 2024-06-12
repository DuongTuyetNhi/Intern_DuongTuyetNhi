package pageObject;

import org.openqa.selenium.By;
import static base.Constants.email_url;
import static base.DriverManagement.driver;

public class MailPage extends BasePage{
    public By checkbox = By.id("use-alias");
    public By registerEmail = By.id("email-widget");
    String emailReset = "//table[@id='email_table']//tr[contains(@class, 'mail')]//td[contains(.,'Please reset your password')]//span";
    String linkReset = "//*[@id='display_email']//a[contains(@href,'PasswordReset')]";
    By txtEmailConfirm = By.xpath("//table[@id='email_table']//tr[contains(@class, 'mail')]//td[contains(.,'Please confirm your account')]//span");
    By txtLinkConfirm = By.xpath("//*[@id='display_email']//a[contains(@href,'Confirm')]");

    By btnMail = By.xpath("//*[@id='inbox-id']");
    By txtMailName = By.xpath("//*[@id='inbox-id']/input");
    By btnSet = By.xpath("//*[@id='inbox-id']/button[@class='save button small']");
    By sltDomainName = By.xpath("//select[@id='gm-host-select']");
    By txtEmailReset = By.xpath("//table[@id='email_table']//tr[contains(@class, 'mail')]//td[contains(.,'Please reset your password')]//span");
    By txtLinkReset = By.xpath("//*[@id='display_email']//a[contains(@href,'PasswordReset')]");


    public MailPage openMailPage(){
        driver.get(email_url);
        return this;
    }

    public String getEmail(){
        driver.findElement(checkbox).click();
        String email = driver.findElement(registerEmail).getText();
        return email;
    }

    public void confirmAccount(){
        waitElement("//table[@id='email_table']//tr[contains(@class, 'mail')]//td[contains(.,'Please confirm your account')]//span");
        driver.findElement(txtEmailConfirm).click();

        waitElement("//*[@id='display_email']//a[contains(@href,'Confirm')]");
        driver.findElement(txtLinkConfirm).click();
    }

    public void loginToEmail(String mailName, String domainName){
        driver.findElement(btnMail).click();
        driver.findElement(txtMailName).sendKeys(mailName);
        driver.findElement(btnSet).click();
        driver.findElement(sltDomainName).sendKeys(domainName);
    }
    public void resetPassword(){
        waitElement(emailReset);
        driver.findElement(txtEmailReset).click();

        waitElement(linkReset);
        driver.findElement(txtLinkReset).click();
    }
}
