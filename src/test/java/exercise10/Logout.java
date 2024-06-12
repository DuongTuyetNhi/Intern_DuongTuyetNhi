package exercise10;

import BaseTest.BaseTest;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import pageObject.HomePage;
import pageObject.LoginPage;
import org.testng.annotations.Test;

public class Logout extends BaseTest {

    @Test(description = "User is redirected to Home page after logging out")
    public void TC06(){

        HomePage homePage = new HomePage();
        homePage.open();
        homePage.gotoTab("Login");
        LoginPage loginPage = new LoginPage();
        loginPage.submitLoginForm(username, password);
        homePage.gotoTab("Log out");

        try {
            Assert.assertTrue(homePage.checkHomepageIsDisplay());
            homePage.checkLogoutTab();
            Assert.fail("Logout tab is exists");
        } catch (NoSuchElementException e) {
            Assert.assertTrue(e instanceof NoSuchElementException);
        }
    }
}
