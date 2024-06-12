package exercise10;

import BaseTest.BaseTest;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Set;

import static base.DriverManagement.driver;

public class RegisterTest extends BaseTest {

    @Test(description = "User cannot create account with an already in use email")
    public void TC07(){
        homePage.open();
        homePage.gotoTab("Register");
        registerPage.fillRegisterForm(username, password, password, pid);
        registerPage.clickBtnRegister();

        String actualMsg = registerPage.getErrorMsg();
        String expectedMsg = "This email address is already in use.";

        Assert.assertEquals(actualMsg, expectedMsg, "The actual message is not the same as expected.");
    }

    @Test(description = "User cannot create account while password and PID fields are empty")
    public void TC08(){
        homePage.open();
        homePage.gotoTab("Register");
        registerPage.fillRegisterForm("helloselenium@gmail.com", "","","");
        registerPage.clickBtnRegister();

        SoftAssert softAssert = new SoftAssert();

        String actualMsg = registerPage.getErrorMsg();
        String expectedMsg = "There're errors in the form. Please correct the errors and try again.";
        softAssert.assertEquals(actualMsg, expectedMsg, "The error message is not the same as expected.");

        String passwordActualMsg = registerPage.getValidationPasswordError();
        String passwordExpectedMsg = "Invalid password length.";
        softAssert.assertEquals(passwordActualMsg, passwordExpectedMsg, "The password error message is not the same as expected.");

        String pidActualMsg = registerPage.getValidationPIDError();
        String pidExpectedMsg = "Invalid ID length.";
        softAssert.assertEquals(pidActualMsg, pidExpectedMsg, "The PID error message is not the same as expected.");

        softAssert.assertAll();
    }

    @Test(description = "User create and active account")
    public void TC09(){
        mailPage.openMailPage();
        String mail = mailPage.getEmail();
        String MailWindow = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);

        homePage.open();
        homePage.clickCreateAnAccountLink();

        String RailwayWindow = driver.getWindowHandle();

        registerPage.fillRegisterForm(mail, password, password, pid);
        registerPage.clickBtnRegister();
        Assert.assertTrue(registerPage.checkMessageDisplay());

        driver.switchTo().window(MailWindow);
        driver.navigate().refresh();

        mailPage.confirmAccount();

        Set<String> allTabs = driver.getWindowHandles();

        for (String tab : allTabs) {
            if (!tab.equals(MailWindow) && !tab.equals(RailwayWindow)) {
                driver.switchTo().window(tab);
                break;
            }
        }
        pause(5000);

        Assert.assertTrue(registerPage.checkMessageConfirmDisplay());
    }
}
