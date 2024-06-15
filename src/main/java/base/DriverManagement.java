package base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

import static base.Constants.getProperty;

public class DriverManagement {
    public static WebDriver driver;
    public static void open(){
        driver.get(getProperty("railway_url"));
    }

    public static void openMailPage(){
        driver.get(getProperty("email_url"));
    }

    public static void enter(By element, String information){
        driver.findElement(element).sendKeys(information);
    }

    public static void click(By element){
        driver.findElement(element).click();
    }

    public static String getText(By element){
        return driver.findElement(element).getText();
    }

    public static void waitElement(String xpath){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(xpath))));
    }

    public static void scrollToFindElement(String xpath){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement Element = driver.findElement(By.xpath(xpath));
        js.executeScript("arguments[0].scrollIntoView();", Element);
    }

    public static void switchToTab(String tab1, String tab2){
        Set<String> allTabs = driver.getWindowHandles();

        for (String tab : allTabs) {
            if (!tab.equals(tab1) && !tab.equals(tab2)) {
                driver.switchTo().window(tab);
                break;
            }
        }
    }
}
