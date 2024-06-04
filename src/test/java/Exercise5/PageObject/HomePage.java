package Exercise5.PageObject;

import Exercise5.Base.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver) {
        super(driver);
    }
    public HomePage open(){
        Config.driver.get(Config.railway_url);
        return this;
    }

}
