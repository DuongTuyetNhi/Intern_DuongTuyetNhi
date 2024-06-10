package exercise10;

import BaseTest.BaseTest;
import pageObject.LoginPage;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static base.DriverManagement.driver;


public class LoginTest extends BaseTest {

    @Test(description = "User can log into Railway with valid username and password")
    public void TC01(){
        homePage.open();
        homePage.gotoTab("Login");
        LoginPage loginPage = new LoginPage();
        loginPage.submitLoginForm(username, password);
        Assert.assertTrue(homePage.checkWelcomeMsg("Welcome to Safe Railway"));
    }

    @Test(description = "User cannot login with blank Username textbox")
    public void TC02(){
        homePage.open();
        homePage.gotoTab("Login");
        loginPage.submitLoginForm("",password);
        String expectedMsg = "There was a problem with your login and/or errors exist in your form.";
        String actualMsg = loginPage.getErrorMsg();

        Assert.assertEquals(actualMsg, expectedMsg, "Failed");
    }

    @Test(description = "User cannot log into Railway with invalid password")
    public void TC03(){
        homePage.open();
        homePage.gotoTab("Login");
        loginPage.submitLoginForm(username, "11111111");
        String actualMsg = loginPage.getErrorMsg();
        String expectedMsg = "There was a problem with your login and/or errors exist in your form.";

        Assert.assertEquals(actualMsg, expectedMsg, "Failed");
    }

    @Test(description = "System shows message when user enters wrong password many times")
    public void TC04(){
        homePage.open();
        homePage.gotoTab("Login");
        for(int i=0; i<=3; i++){
            loginPage.submitLoginForm(username, "11111111");
        }
        String actualMsg = loginPage.getErrorMsg();
        String expectedMsg = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";

        Assert.assertEquals(actualMsg, expectedMsg, "Failed");
    }

    @Test(description = "User cannot login with an account hasn't been activated")
    public void TC05(){
        mailPage.openMailPage();
        String email = mailPage.getEmail();

        driver.switchTo().newWindow(WindowType.TAB);

        homePage.open();
        homePage.gotoTab("Register");
        registerPage.fillRegisterForm(email, password, password, pid);
        registerPage.clickBtnRegister();

        registerPage.gotoTab("Login");
        loginPage.submitLoginForm(email,password);

        String actualMsg = loginPage.getErrorMsg();
        String expectedMsg = "Invalid username or password. Please try again.";
        Assert.assertEquals(actualMsg, expectedMsg, "Failed");
    }


}
