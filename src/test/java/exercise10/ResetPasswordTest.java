package exercise10;

import BaseTest.BaseTest;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Set;

import static base.DriverManagement.driver;

public class ResetPasswordTest extends BaseTest {

    @Test(description = "Reset password shows error if the new password is same as current")
    public void TC10(){
        homePage.open();
        homePage.gotoTab("Login");
        loginPage.clickForgotPassword();
        String RailwayWindow = driver.getWindowHandle();

        forgotPasswordPage.enterEmailAddress(username);
        forgotPasswordPage.clickSendInstructionsBtn();

        driver.switchTo().newWindow(WindowType.TAB);
        mailPage.openMailPage();
        String MailWindow = driver.getWindowHandle();
        mailPage.loginToEmail(mailName, domainName);
        mailPage.resetPassword();

        Set<String> allTabs = driver.getWindowHandles();
        for (String tab : allTabs) {
            if (!tab.equals(MailWindow) && !tab.equals(RailwayWindow)) {
                driver.switchTo().window(tab);
                break;
            }
        }
        pause(5000);

        Assert.assertTrue(loginPage.checkTokenIsDisplay());
        loginPage.fillResetPasswordForm(password, password);
        loginPage.clickResetPasswordBtn();

        String actualMsg = loginPage.getResetMsg();
        String expectedMsg = "The new password cannot be the same with the current password";
        Assert.assertEquals(actualMsg, expectedMsg, "The actual message is not the same as expected.");
    }

    @Test(description = "Reset password shows error if the new password and confirm password doesn't match")
    public void TC11(){
        homePage.open();
        homePage.gotoTab("Login");
        loginPage.clickForgotPassword();
        String RailwayWindow = driver.getWindowHandle();

        forgotPasswordPage.enterEmailAddress(username);
        forgotPasswordPage.clickSendInstructionsBtn();

        driver.switchTo().newWindow(WindowType.TAB);
        mailPage.openMailPage();
        String MailWindow = driver.getWindowHandle();
        mailPage.loginToEmail(mailName, domainName);
        mailPage.resetPassword();

        Set<String> allTabs = driver.getWindowHandles();
        for (String tab : allTabs) {
            if (!tab.equals(MailWindow) && !tab.equals(RailwayWindow)) {
                driver.switchTo().window(tab);
                break;
            }
        }
        pause(5000);

        Assert.assertTrue(loginPage.checkTokenIsDisplay());
        loginPage.fillResetPasswordForm(password, "11111111");
        loginPage.clickResetPasswordBtn();

        String actualMsg = loginPage.getResetMsg();
        String expectedMsg = "Could not reset password. Please correct the errors and try again.";
        Assert.assertEquals(actualMsg, expectedMsg, "The reset error message is not the same as expected.");

        String actualConfirmMsg = loginPage.getConfirmPasswordMsg();
        String expectedConfirmMsg = "The password confirmation did not match the new password.";
        Assert.assertEquals(actualConfirmMsg, expectedConfirmMsg,"The confirm password error message is not the same as expected.");
    }
}
