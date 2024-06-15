package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static base.DriverManagement.driver;

public class BasePage {
    private String stab = "//div[@id='menu']//li/a[span[text()='%s']]";

    protected WebElement getTabElement(String tab){
        By byTab = By.xpath(String.format(stab, tab));
        return driver.findElement(byTab);
    }

    public void gotoTab(String tab){
        this.getTabElement(tab).click();
    }

    public boolean isLogoutTabPresent(){
        try {
            By tabLogout = By.xpath(String.format(stab, "Log out"));
            driver.findElement(tabLogout).isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
