package amazon.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {
    WebDriver driver;
    WebDriverWait wait;

    By signInPrompt = By.id("auth-signin-button");  // Adjust ID as needed
    By shippingAddress = By.id("address");  // Update with correct locator
    By paymentInfo = By.id("payment-info");  // Update with correct locator
    By placeOrderButton = By.id("place-order-button");  // Update with correct locator

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isSignInPromptDisplayed() {
        return driver.findElement(signInPrompt).isDisplayed();
    }

    public void enterShippingAddress(String address) {
        driver.findElement(shippingAddress).sendKeys(address);
    }

    public void enterPaymentInfo(String paymentDetails) {
        driver.findElement(paymentInfo).sendKeys(paymentDetails);
    }

    public void placeOrder() {
        driver.findElement(placeOrderButton).click();
    }
}
