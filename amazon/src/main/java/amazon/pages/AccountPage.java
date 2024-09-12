package amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage {
    WebDriver driver;
    
    By yourOrdersHeader = By.xpath("//h1[contains(text(), 'Your Orders')]");
    By yourAccountHeader = By.xpath("//h1[contains(text(), 'Your Account')]");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isYourOrdersHeaderDisplayed() {
        return driver.findElement(yourOrdersHeader).isDisplayed();
    }

    public boolean isYourAccountHeaderDisplayed() {
        return driver.findElement(yourAccountHeader).isDisplayed();
    }
}
