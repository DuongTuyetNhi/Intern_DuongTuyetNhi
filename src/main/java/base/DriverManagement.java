package base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

import static config.ConfigReader.getProperty;

public class DriverManagement {

    public static WebDriver driver;
    public static void initDriver() throws Exception {
        String browser = getProperty("browser").toLowerCase();
        switch (browser){
            case "chrome":{
                driver = new ChromeDriver();
                break;
            }
            case "firefox":{
                driver = new FirefoxDriver();
                break;
            }
            default:
                throw new Exception("Browser "+browser+" was not supported.");
        }
    }
    public static void openRailwayPage(){
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

    public static void waitForElementVisible(By xpath, int timeout){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
    }

    public static void scrollToView(By xpath){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement Element = driver.findElement(xpath);
        js.executeScript("arguments[0].scrollIntoView();", Element);
    }

    public static void switchToTab(String mailTab, String railwayTab){
        Set<String> allTabs = driver.getWindowHandles();

        for (String tab : allTabs) {
            if (!tab.equals(mailTab) && !tab.equals(railwayTab)) {
                driver.switchTo().window(tab);
                break;
            }
        }
    }
}
