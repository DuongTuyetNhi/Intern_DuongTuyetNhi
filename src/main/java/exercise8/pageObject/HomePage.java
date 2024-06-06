package exercise8.pageObject;

import exercise8.base.Config;
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
