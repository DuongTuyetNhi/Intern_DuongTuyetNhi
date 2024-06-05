package exercise5.pageObject;

import exercise5.base.Config;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver) {
        super(driver);
    }
    public HomePage open(){
        Config.driver.get(Config.railway_url);
        return this;
    }

}
