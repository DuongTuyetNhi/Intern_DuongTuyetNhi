package Exercise3;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterBookticket {
    public static void main(String[] args) {
        WebDriver webDriver = new ChromeDriver();

        // get email

        webDriver.get("https://www.guerrillamail.com/inbox");
        By checkboxEmail = By.id("use-alias");
        webDriver.findElement(checkboxEmail).click();

        By registerEmail = By.id("email-widget");
        String email = webDriver.findElement(registerEmail).getText();
        //get current Window
        String originalWindow = webDriver.getWindowHandle();
        System.out.println("originalWindow ->  " + originalWindow);

        System.out.println(email);

        // register

        webDriver.switchTo().newWindow(WindowType.TAB);
        webDriver.get("http://saferailway.somee.com/Page/HomePage.cshtml");
        By tabRegister = By.xpath("//div[@id='menu']//li/a[span[text()='Register']]");

        webDriver.findElement(tabRegister).click();

        By txtEmail = By.id("email");
        By txtPassword = By.id("password");
        By txtConfirmPassword = By.id("confirmPassword");
        By txtPID = By.id("pid");
        By btnRegister = By.xpath("//*[@id='RegisterForm']//p/input[@type='submit']");

        webDriver.findElement(txtEmail).sendKeys(email);
        webDriver.findElement(txtPassword).sendKeys("12345678");
        webDriver.findElement(txtConfirmPassword).sendKeys("12345678");
        webDriver.findElement(txtPID).sendKeys("12345678");
        webDriver.findElement(btnRegister).click();

        webDriver.switchTo().window(originalWindow);

        //confirm email

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='email_table']//tr[contains(@class, 'mail')]//td[contains(.,'Please confirm your account')]//span")));

        By txtEmailConfirm = By.xpath("//table[@id='email_table']//tr[contains(@class, 'mail')]//td[contains(.,'Please confirm your account')]//span");
        webDriver.findElement(txtEmailConfirm).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='display_email']//a[contains(@href,'Confirm')]")));

        By txtLinkConfirm = By.xpath("//*[@id='display_email']//a[contains(@href,'Confirm')]");

        webDriver.findElement(txtLinkConfirm).click();

        //login

        webDriver.switchTo().newWindow(WindowType.TAB);
        webDriver.get("http://saferailway.somee.com/Page/HomePage.cshtml");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='menu']//li/a[span[text()='Login']]")));

        By tabLogin = By.xpath("//div[@id='menu']//li/a[span[text()='Login']]");

        webDriver.findElement(tabLogin).click();

        By txtLoginUsername = By.id("username");
        By txtLoginPassword = By.id("password");
        By btnLogin = By.xpath("//input[@type='submit']");

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        //Locating element by link text and store in variable "Element"
        WebElement Element = webDriver.findElement(By.xpath("//input[@type='submit']"));

        // Scrolling down the page till the element is found
        js.executeScript("arguments[0].scrollIntoView();", Element);

        webDriver.findElement(txtLoginUsername).sendKeys(email);
        webDriver.findElement(txtLoginPassword).sendKeys("12345678");
        webDriver.findElement(btnLogin).click();

        // book ticket

        By tabBookticket = By.xpath("//div[@id='menu']//li/a[span[text()='Book ticket']]");
        webDriver.findElement(tabBookticket).click();

        By selectArriveAt = By.xpath("//*[@id='content']//select[@name='ArriveStation']");
        By selectAmount = By.xpath("//*[@id='content']//select[@name='TicketAmount']");
        By btnBookTicket = By.xpath("//*[@id='content']//input[@type='submit']");

        webDriver.findElement(selectArriveAt).sendKeys("Phan Thiết");
        webDriver.findElement(selectAmount).sendKeys("2");
        webDriver.findElement(btnBookTicket).click();

        webDriver.quit();
    }
}
