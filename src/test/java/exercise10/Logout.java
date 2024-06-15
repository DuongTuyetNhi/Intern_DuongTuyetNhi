package exercise10;

import BaseTest.BaseTest;
import base.DriverManagement;
import models.User;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import pageObject.HomePage;
import pageObject.LoginPage;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Logout extends BaseTest {
    protected String username = "nhiagest@grr.la";
    protected String password = "12345678";
    User validUser = new User(username, password);
    @Test(description = "User is redirected to Home page after logging out")
    public void LogOut(){

        HomePage homePage = new HomePage();
        DriverManagement.open();
        homePage.gotoTab("Login");
        LoginPage loginPage = new LoginPage();
        loginPage.submitLoginForm(validUser);
        homePage.gotoTab("Log out");

        Assert.assertTrue(homePage.checkHomepageIsDisplay());
        Assert.assertFalse(homePage.isLogoutTabPresent(), "Logout tab is exist");

        if (homePage.isLogoutTabPresent()) {
            Assert.fail("Logout tab exists");
        } else {
            Assert.assertTrue(true, "Logout tab does not exist as expected");
        }
    }
}
