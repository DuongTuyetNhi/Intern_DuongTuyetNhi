package exercise2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HelloSelenium {
    public static void main(String[] args) {
        runOnChrome();
        runOnFirefox();
    }

    public static void runOnChrome(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/documentation/");

        String title = "The Selenium Browser Automation Project | Selenium";

        if (driver.getTitle().equals(title)){
            System.out.println("Pass");
        }
        else {
            System.out.println("Fail");
        }

        driver.quit();
    }

    public static void runOnFirefox(){
        WebDriver firefox = new FirefoxDriver();
        firefox.get("https://www.selenium.dev/documentation/");

        String title = "The Selenium Browser Automation Project | Selenium";

        if (firefox.getTitle().equals(title)){
            System.out.println("Pass");
        }
        else {
            System.out.println("Fail");
        }

        firefox.quit();
    }

}
