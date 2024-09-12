package amazon.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaymentMethodsPage {
    WebDriver driver;
    WebDriverWait wait;

    By addPaymentMethodButton = By.linkText("Add a Payment Method");
    By addCardButton = By.cssSelector("input[type='submit'][class='a-button-input']");
    By cardNumberInput = By.cssSelector("input[data-testid='card-text-input']");
    By expiryDateInput = By.cssSelector("input[placeholder='MM/YY']");
    By securityCodeInput = By.cssSelector("input[placeholder='Security code']");
    By nameOnCardInput = By.cssSelector("input[placeholder='Name on card']");
    By addAndContinueButton = By.cssSelector("div[data-testid='text']");
    By successMessage = By.id("success_message");
    By errorMessage = By.cssSelector("div[role='alert']");
    By setDefaultCheckbox = By.xpath("//label[contains(text(),'Set as default')]/preceding-sibling::input[@type='checkbox']");

    public PaymentMethodsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void addPaymentMethod(String cardNumber, String expiryDate, String securityCode, String nameOnCard) {
        WebElement addPaymentMethodButtonElement = wait.until(ExpectedConditions.elementToBeClickable(addPaymentMethodButton));
        addPaymentMethodButtonElement.click();
        
        WebElement addCardButtonElement = wait.until(ExpectedConditions.elementToBeClickable(addCardButton));
        addCardButtonElement.click();

        WebElement cardNumberInputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(cardNumberInput));
        cardNumberInputElement.sendKeys(cardNumber);

        WebElement expiryDateInputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(expiryDateInput));
        expiryDateInputElement.sendKeys(expiryDate);

        WebElement securityCodeInputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(securityCodeInput));
        securityCodeInputElement.sendKeys(securityCode);

        WebElement nameOnCardInputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(nameOnCardInput));
        nameOnCardInputElement.sendKeys(nameOnCard);

        WebElement addAndContinueButtonElement = wait.until(ExpectedConditions.elementToBeClickable(addAndContinueButton));
        addAndContinueButtonElement.click();
    }

    public boolean isSuccessMessageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).isDisplayed();
    }

    public boolean isErrorMessageDisplayed(String expectedMessage) {
        WebElement errorMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return errorMessageElement.getText().contains(expectedMessage);
    }

    public void setDefaultPaymentMethod() {
        WebElement setDefaultCheckboxElement = wait.until(ExpectedConditions.visibilityOfElementLocated(setDefaultCheckbox));
        if (setDefaultCheckboxElement != null && !setDefaultCheckboxElement.isSelected()) {
            setDefaultCheckboxElement.click();
        }
    }
}
