package exercise5.pageObject;

import exercise5.base.Config;
import org.openqa.selenium.By;
import static exercise5.base.Config.driver;
import static exercise5.base.Config.email_url;

public class MailPage {
    public By checkbox = By.id("use-alias");
    public By registerEmail = By.id("email-widget");
    By txtEmailConfirm = By.xpath("//table[@id='email_table']//tr[contains(@class, 'mail')]//td[contains(.,'Please confirm your account')]//span");
    By txtLinkConfirm = By.xpath("//*[@id='display_email']//a[contains(@href,'Confirm')]");



    public MailPage openMailPage(){
        Config.driver.get(email_url);
        return this;
    }

    public String getEmail(){
        Config.driver.findElement(checkbox).click();
        String email = Config.driver.findElement(registerEmail).getText();
        return email;
    }

    public void confirmAccount(){
        BasePage basePage = new BasePage(driver);
        basePage.waitElement("//table[@id='email_table']//tr[contains(@class, 'mail')]//td[contains(.,'Please confirm your account')]//span");
        driver.findElement(txtEmailConfirm).click();

        basePage.waitElement("//*[@id='display_email']//a[contains(@href,'Confirm')]");
        driver.findElement(txtLinkConfirm).click();
    }

}
