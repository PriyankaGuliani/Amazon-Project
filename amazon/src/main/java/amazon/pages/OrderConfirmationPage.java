package amazon.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderConfirmationPage {
    WebDriver driver;
    WebDriverWait wait;

    By confirmationMessage = By.id("order-confirmation");  // Update with correct locator

    public OrderConfirmationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isOrderConfirmed() {
        WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMessage));
        return confirmation.getText().contains("Thank you for your purchase");
    }
}
