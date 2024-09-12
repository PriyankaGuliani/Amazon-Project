package amazon.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YourAccountPage {
    WebDriver driver;
    WebDriverWait wait;

    By accountList = By.id("nav-link-accountList");
    By yourPaymentsLink = By.linkText("Your Payments");
    By loginSecurityLink = By.linkText("Login & security");
    By yourAddressesLink = By.xpath("//a[contains(text(), 'Your Addresses')]");


    public YourAccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void goToYourPayments() {
        WebElement accountListElement = wait.until(ExpectedConditions.elementToBeClickable(accountList));
        accountListElement.click();
        WebElement yourPaymentsLinkElement = wait.until(ExpectedConditions.elementToBeClickable(yourPaymentsLink));
        yourPaymentsLinkElement.click();
    }

    public void goToLoginSecurity() {
        WebElement accountListElement = wait.until(ExpectedConditions.elementToBeClickable(accountList));
        accountListElement.click();
        WebElement loginSecurityLinkElement = wait.until(ExpectedConditions.elementToBeClickable(loginSecurityLink));
        loginSecurityLinkElement.click();
    }

    public void goToYourAddresses() {
        WebElement accountListElement = wait.until(ExpectedConditions.elementToBeClickable(accountList));
        accountListElement.click();

        WebElement yourAddressesLinkElement = wait.until(ExpectedConditions.elementToBeClickable(yourAddressesLink));
        yourAddressesLinkElement.click();
    }

}
