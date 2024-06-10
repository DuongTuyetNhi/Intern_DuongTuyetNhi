package exercise10;

import BaseTest.BaseTest;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;

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

        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not same");
    }

    @Test(description = "User cannot create account while password and PID fields are empty")
    public void TC08(){
        homePage.open();
        homePage.gotoTab("Register");
        registerPage.fillRegisterForm("helloselenium@gmail.com", "","","");
        registerPage.clickBtnRegister();

        String actualMsg = registerPage.getErrorMsg();
        String expectedMsg = "There're errors in the form. Please correct the errors and try again.";

        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not same");

        String passwordActualMsg = registerPage.getValidationPasswordError();
        String passwordExpectedMsg = "Invalid password length.";
        Assert.assertEquals(passwordActualMsg, passwordExpectedMsg, "Password error message is not same");

        String pidActualMsg = registerPage.getValidationPIDError();
        String pidExpectedMsg = "Invalid ID length.";
        Assert.assertEquals(pidActualMsg, pidExpectedMsg, "PID error message is not same");
    }

    @Test(description = "User create and active account")
    public void TC09(){
        //not finished
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
        //driver.switchTo().window(RailwayWindow);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        registerPage.checkMessageConfirmDisplay();
        registerPage.gotoTab("Login");
        loginPage.submitLoginForm(mail, password);


    }
}
