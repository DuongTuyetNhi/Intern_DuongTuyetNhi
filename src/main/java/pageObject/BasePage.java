package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static base.DriverManagement.driver;

public class BasePage {
    public String stab = "//div[@id='menu']//li/a[span[text()='%s']]";

    protected WebElement getTabElement(String tab){
        By byTab = By.xpath(String.format(stab, tab));
        return driver.findElement(byTab);
    }

    public void gotoTab(String tab){
        this.getTabElement(tab).click();
    }

    public void waitElement(String xpath){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(xpath))));
    }

    public void scrollToFindElement(String xpath){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement Element = driver.findElement(By.xpath(xpath));
        js.executeScript("arguments[0].scrollIntoView();", Element);
    }

    public boolean checkLogoutTab(){
        By tabLogout = By.xpath(String.format(stab, "Log out"));
        return driver.findElement(tabLogout).isDisplayed();
    }
}
