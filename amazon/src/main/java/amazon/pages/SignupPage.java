package amazon.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupPage {
    WebDriver driver;
    WebDriverWait wait;

    By nameField = By.id("ap_customer_name");
    By emailField = By.id("ap_email");
    By passwordField = By.id("ap_password");
    By passwordCheckField = By.id("ap_password_check");
    By createAccountButton = By.id("continue");
    By verifyMobileNumberButton = By.id("verify_mobile_number"); 
    By verifyEmailButton = By.id("verify_email"); 
    By errorMessage = By.className("a-alert-content"); 

    public SignupPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void enterName(String name) {
        WebElement nameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
        nameElement.sendKeys(name);
    }

    public void enterEmail(String email) {
        WebElement emailElement = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        emailElement.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passwordElement.sendKeys(password);
    }

    public void enterPasswordCheck(String password) {
        WebElement passwordCheckElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordCheckField));
        passwordCheckElement.sendKeys(password);
    }

    public void clickCreateAccount() {
        WebElement createButton = wait.until(ExpectedConditions.elementToBeClickable(createAccountButton));
        createButton.click();
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }

    public boolean isVerifyMobileNumberButtonVisible() {
        try {
            return driver.findElement(verifyMobileNumberButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isVerifyEmailButtonVisible() {
        try {
            return driver.findElement(verifyEmailButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
