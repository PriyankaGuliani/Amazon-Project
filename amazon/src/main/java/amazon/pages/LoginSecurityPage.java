package amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginSecurityPage {
    WebDriver driver;
    WebDriverWait wait;

    By nameField = By.id("nameField");
    By phoneField = By.id("phoneField");
    By emailField = By.id("emailField");
    By nameError = By.id("nameError");
    By phoneError = By.id("phoneError");
    By emailError = By.id("emailError");
    By primaryEmailError = By.id("primaryEmailError");

    public LoginSecurityPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void updateName(String newName) {
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
        nameInput.clear();
        nameInput.sendKeys(newName);
    }

    public String getName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).getAttribute("value");
    }

    public void updatePhoneNumber(String newPhoneNumber) {
        WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneField));
        phoneInput.clear();
        phoneInput.sendKeys(newPhoneNumber);
    }

    public String getPhoneNumber() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(phoneField)).getAttribute("value");
    }

    public void addNewEmailAddress(String newEmail) {
        WebElement addEmailButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("addEmailButton")));
        addEmailButton.click();

        WebElement newEmailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("newEmailField")));
        newEmailField.clear();
        newEmailField.sendKeys(newEmail);

        WebElement saveChangesButton = driver.findElement(By.id("saveChangesButton"));
        saveChangesButton.click();
    }

    public String getEmailField() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).getAttribute("value");
    }

    public void addPhoneNumber(String newPhoneNumber) {
        WebElement addPhoneNumberButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("addPhoneNumberButton")));
        addPhoneNumberButton.click();

        WebElement newPhoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("newPhoneField")));
        newPhoneField.clear();
        newPhoneField.sendKeys(newPhoneNumber);

        WebElement saveChangesButton = driver.findElement(By.id("saveChangesButton"));
        saveChangesButton.click();
    }

    public void deletePhoneNumber() {
        WebElement deletePhoneButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("deletePhoneButton")));
        deletePhoneButton.click();

        WebElement confirmDeleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("confirmDeleteButton")));
        confirmDeleteButton.click();
    }

    public void deleteEmailAddress() {
        WebElement deleteEmailButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("deleteEmailButton")));
        deleteEmailButton.click();

        WebElement confirmDeleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("confirmDeleteButton")));
        confirmDeleteButton.click();
    }

    public void deletePrimaryEmailAddress() {
        WebElement deletePrimaryEmailButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("deletePrimaryEmailButton")));
        deletePrimaryEmailButton.click();
    }

    public boolean isErrorMessageDisplayed(String errorType) {
        By errorLocator;
        switch (errorType) {
            case "nameError":
                errorLocator = nameError;
                break;
            case "phoneError":
                errorLocator = phoneError;
                break;
            case "emailError":
                errorLocator = emailError;
                break;
            case "primaryEmailError":
                errorLocator = primaryEmailError;
                break;
            default:
                throw new IllegalArgumentException("Invalid error type: " + errorType);
        }
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(errorLocator));
        return errorMessage.isDisplayed();
    }

    public boolean isFieldInvisible(String fieldId) {
        By fieldLocator;
        switch (fieldId) {
            case "phoneField":
                fieldLocator = phoneField;
                break;
            case "emailField":
                fieldLocator = emailField;
                break;
            default:
                throw new IllegalArgumentException("Invalid field id: " + fieldId);
        }
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(fieldLocator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
