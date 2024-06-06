package exercise5.pageObject;

import exercise5.base.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    public String tab = "//div[@id='menu']//li/a[span[text()='%s']]";

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public <T extends BasePage> T gotoTab(String tabName, Class<T> pageClass) {
        String dynamicXpath = String.format(tab, tabName);
        By tabNameLocator = By.xpath(dynamicXpath);
        WebElement tabElement = driver.findElement(tabNameLocator);
        tabElement.click();

        try {
            return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(driver);
        } catch (Exception e) {
            throw new RuntimeException("Không thể khởi tạo trang: " + pageClass.getSimpleName(), e);
        }
    }
    public void waitElement(String xpath){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(xpath))));
    }

    public void scrollToFindElement(String xpath){
        JavascriptExecutor js = (JavascriptExecutor) Config.driver;
        WebElement Element = Config.driver.findElement(By.xpath(xpath));
        js.executeScript("arguments[0].scrollIntoView();", Element);
    }
}
