package exercise5;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;


public class BookTicket {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        String railway_url = "http://saferailway.somee.com/";
        String email_url = "https://www.guerrillamail.com/inbox";
        String tab = "//div[@id='menu']//li/a[span[text()='%s']]";
        String email;
        String password = "12345678";
        String pid = "12345678";
        String checkPriceLink = "//tr[td[text()='%s' and following-sibling::td[text()='%s']]]//a[contains(@href, 'TicketPricePage')]";
        String typeSeat = "//table[@class='NoBorder']//tr[td[text()='%s']]//a";
        String infor = "//*[@id='content']//select[@name='%s']";

        By checkbox = By.id("use-alias");
        By registerEmail = By.id("email-widget");
        By txtEmailConfirm = By.xpath("//table[@id='email_table']//tr[contains(@class, 'mail')]//td[contains(.,'Please confirm your account')]//span");
        By txtLinkConfirm = By.xpath("//*[@id='display_email']//a[contains(@href,'Confirm')]");
        By tabRegister = By.xpath(String.format(tab, "Register"));
        By txtEmail = By.id("email");
        By txtPassword = By.id("password");
        By txtConfirmPassword = By.id("confirmPassword");
        By txtPID = By.id("pid");
        By btnRegister = By.xpath("//*[@id='RegisterForm']//p/input[@type='submit']");
        By tabLogin = By.xpath(String.format(tab, "Login"));
        By txtLoginUsername = By.id("username");
        By txtLoginPassword = By.id("password");
        By btnLogin = By.xpath("//input[@type='submit']");
        By msgWelcome = By.xpath("//*[@id='content']/h1[text()='Welcome to Safe Railway']");
        By tabTraiTimetable = By.xpath(String.format(tab,"Timetable"));
        By selectArriveAt = By.xpath("//*[@id='content']//select[@name='ArriveStation']");
        By selectAmount = By.xpath("//*[@id='content']//select[@name='TicketAmount']");
        By btnBookTicket = By.xpath("//*[@id='content']//input[@type='submit']");
        By msgSuccessful = By.xpath("//*[@id='content']/h1");



        //get email
        driver.get(email_url);
        driver.findElement(checkbox).click();
        email = driver.findElement(registerEmail).getText();
        System.out.println(email);
        String MailWindow = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(railway_url);

        //register
//        HomePage homePage = new HomePage(driver);
//        homePage.open();
        String RailwayWindow = driver.getWindowHandle();

        driver.findElement(tabRegister).click();


        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement Element = driver.findElement(btnRegister);
        js.executeScript("arguments[0].scrollIntoView();", Element);

        driver.findElement(txtEmail).sendKeys(email);
        driver.findElement(txtPassword).sendKeys(password);
        driver.findElement(txtConfirmPassword).sendKeys(password);
        driver.findElement(txtPID).sendKeys(pid);
        driver.findElement(btnRegister).click();

        //confirm email

        driver.switchTo().window(MailWindow);
        driver.navigate().refresh();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        wait.until(ExpectedConditions.visibilityOfElementLocated(txtEmailConfirm));
        driver.findElement(txtEmailConfirm).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(txtLinkConfirm));
        driver.findElement(txtLinkConfirm).click();

        //Login
        driver.switchTo().window(RailwayWindow);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        driver.findElement(tabLogin).click();
        WebElement buttonLogin = driver.findElement(btnLogin);
        js.executeScript("arguments[0].scrollIntoView();", buttonLogin);

        driver.findElement(txtLoginUsername).sendKeys(email);
        driver.findElement(txtLoginPassword).sendKeys(password);
        driver.findElement(btnLogin).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(msgWelcome));
        driver.findElement(tabTraiTimetable).click();

        By checkTicket = By.xpath(String.format(checkPriceLink,"Sài Gòn", "Đà Nẵng"));
        driver.findElement(checkTicket).click();

        By seatType = By.xpath(String.format(typeSeat, "Soft seat"));
        driver.findElement(seatType).click();

        WebElement buttonBookTicket = driver.findElement(btnBookTicket);
        js.executeScript("arguments[0].scrollIntoView();", buttonBookTicket);

        By departDate = By.xpath(String.format(infor, "Date"));
        driver.findElement(departDate).sendKeys("6/13/2024");
        By amount = By.xpath(String.format(infor,"TicketAmount"));
        driver.findElement(amount).sendKeys("2");
        driver.findElement(btnBookTicket).click();

        String expectedMsg = "Ticket booked successfully!";
        if(driver.findElement(msgSuccessful).getText().equals(expectedMsg)){
            System.out.println("Passed");
        }else {
            System.out.println("Failed");
        }

        driver.quit();


    }

}
