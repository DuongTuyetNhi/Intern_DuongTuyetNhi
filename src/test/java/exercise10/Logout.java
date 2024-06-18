package exercise10;

import BaseTest.BaseTest;
import base.DriverManagement;
import models.User;
import org.testng.Assert;
import pageObject.HomePage;
import pageObject.LoginPage;
import org.testng.annotations.Test;

public class Logout extends BaseTest {
    String username = "nhiagest@grr.la";
    String password = "12345678";
    User validUser = new User(username, password);
    @Test(description = "User is redirected to Home page after logging out")
    public void LogOut(){

        HomePage homePage = new HomePage();
        DriverManagement.open();
        homePage.openLoginTab();
        LoginPage loginPage = new LoginPage();
        loginPage.submitLoginForm(validUser);
        homePage.openTab("Log out");

        Assert.assertTrue(homePage.checkHomepageIsDisplay());
        Assert.assertFalse(homePage.isLogoutTabPresent(), "Logout tab is exist");

    }
}
