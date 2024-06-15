package exercise10;

import BaseTest.BaseTest;
import base.DriverManagement;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.*;

import static base.DriverManagement.driver;
import static base.DriverManagement.switchToTab;

public class ResetPasswordTest extends BaseTest {
    protected HomePage homePage = new HomePage();
    protected LoginPage loginPage = new LoginPage();
    protected MailPage mailPage = new MailPage();
    protected ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
    protected String username = "nhiagest@grr.la";
    protected String password = "12345678";
    String[] str = username.split("@");
    protected String mailName = str[0];
    protected String domainName = str[1];


    @Test(description = "Reset password shows error if the new password is same as current")
    public void ResetPasswordSameOldPassword(){
        DriverManagement.open();
        homePage.gotoTab("Login");
        loginPage.clickForgotPassword();
        String RailwayWindow = driver.getWindowHandle();

        forgotPasswordPage.enterEmailAddress(username);
        forgotPasswordPage.clickSendInstructionsBtn();

        driver.switchTo().newWindow(WindowType.TAB);
        DriverManagement.openMailPage();
        String MailWindow = driver.getWindowHandle();
        mailPage.loginToEmail(mailName, domainName);
        mailPage.resetPassword();

        DriverManagement.switchToTab(MailWindow, RailwayWindow);

        Assert.assertTrue(loginPage.checkTokenIsDisplay());
        loginPage.fillResetPasswordForm(password, password);
        loginPage.clickResetPasswordBtn();

        String actualMsg = loginPage.getResetMsg();
        String expectedMsg = "The new password cannot be the same with the current password";
        Assert.assertEquals(actualMsg, expectedMsg, "The actual message is not the same as expected.");
    }

    @Test(description = "Reset password shows error if the new password and confirm password doesn't match")
    public void ResetPasswordDoesNotMatch(){
        DriverManagement.open();
        homePage.gotoTab("Login");
        loginPage.clickForgotPassword();
        String RailwayWindow = driver.getWindowHandle();

        forgotPasswordPage.enterEmailAddress(username);
        forgotPasswordPage.clickSendInstructionsBtn();

        driver.switchTo().newWindow(WindowType.TAB);
        DriverManagement.openMailPage();
        String MailWindow = driver.getWindowHandle();
        mailPage.loginToEmail(mailName, domainName);
        mailPage.resetPassword();

        switchToTab(MailWindow, RailwayWindow);

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
