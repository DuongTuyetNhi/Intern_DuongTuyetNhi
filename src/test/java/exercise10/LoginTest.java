package exercise10;

import BaseTest.BaseTest;
import base.DriverManagement;
import models.User;
import pageObject.*;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static base.DriverManagement.driver;

public class LoginTest extends BaseTest {
    HomePage homePage = new HomePage();
    LoginPage loginPage = new LoginPage();
    RegisterPage registerPage = new RegisterPage();
    MailPage mailPage = new MailPage();

    String username = "nhiagest@grr.la";
    String password = "12345678";
    String blankUsername = "";
    String invalidPassword = "11111111";
    String pid = "12345678";

    User validUser = new User(username, password);
    User blankUser = new User(blankUsername, password);
    User invalidUser = new User(username, invalidPassword);

    @Test(description = "User can log into Railway with valid username and password")
    public void LoginWithValidAccount(){
        DriverManagement.open();
        homePage.openLoginTab();
        LoginPage loginPage = new LoginPage();
        loginPage.submitLoginForm(validUser);

        String actualMsg = homePage.getWelcomeMsg();
        String expected = "Welcome " + username;
        Assert.assertEquals(actualMsg, expected, "The welcome message is not the same as expected.");
    }

    @Test(description = "User cannot login with blank Username textbox")
    public void LoginWithBlankUsername(){
        DriverManagement.open();
        homePage.openLoginTab();
        loginPage.submitLoginForm(blankUser);
        String expectedMsg = "There was a problem with your login and/or errors exist in your form.";
        String actualMsg = loginPage.getErrorMsg();

        Assert.assertEquals(actualMsg, expectedMsg, "The error message is not the same as expected.");
    }

    @Test(description = "User cannot log into Railway with invalid password")
    public void LoginWithInvalidPassword(){
        DriverManagement.open();
        homePage.openLoginTab();
        loginPage.submitLoginForm(invalidUser);
        String actualMsg = loginPage.getErrorMsg();
        String expectedMsg = "There was a problem with your login and/or errors exist in your form.";

        Assert.assertEquals(actualMsg, expectedMsg, "The error message is not the same as expected.");
    }

    @Test(description = "System shows message when user enters wrong password many times")
    public void LoginInSeveralTimes(){
        DriverManagement.open();
        homePage.openLoginTab();

        loginPage.loginInSeveralTime(invalidUser, 4);

        String actualMsg = loginPage.getErrorMsg();
        String expectedMsg = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";

        Assert.assertEquals(actualMsg, expectedMsg, "The error message is not the same as expected.");
    }

    @Test(description = "User cannot login with an account hasn't been activated")
    public void LoginWithInactiveAccount(){
        DriverManagement.openMailPage();
        String email = mailPage.getEmail();
        User newUser = new User(email, password, pid);

        driver.switchTo().newWindow(WindowType.TAB);

        DriverManagement.open();
        homePage.openTab("Register");
        registerPage.fillRegisterForm(newUser);
        registerPage.clickBtnRegister();

        registerPage.openLoginTab();
        loginPage.submitLoginForm(newUser);

        String actualMsg = loginPage.getErrorMsg();
        String expectedMsg = "Invalid username or password. Please try again.";
        Assert.assertEquals(actualMsg, expectedMsg, "The error message is not the same as expected.");
    }


}
