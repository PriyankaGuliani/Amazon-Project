package amazon;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginSecurityTest {
	WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/priyankaguliani/eclipse-workspace/amazon/chromedriver");
        
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("https://www.amazon.ca");
        
        WebElement signInButton = driver.findElement(By.id("nav-link-accountList-nav-line-1"));
        signInButton.click();

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_email")));
        emailField.sendKeys("priyankaguliani18@gmail.com");

        WebElement continueButton = driver.findElement(By.id("continue"));
        continueButton.click();

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_password")));
        passwordField.sendKeys("Guliani123");

        WebElement loginButton = driver.findElement(By.id("signInSubmit"));
        loginButton.click();
        
        WebElement accountList = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-link-accountList")));
        accountList.click();
        WebElement yourPaymentsLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Login & security")));
        yourPaymentsLink.click();
    }


    @Test(priority = 1)
    public void testUpdateNameWithValidValue() {
        WebElement editNameButton = driver.findElement(By.linkText("editNameButton"));
        editNameButton.click();

        WebElement nameField = driver.findElement(By.linkText("nameField"));
        nameField.clear();
        nameField.sendKeys("New Valid Name");

        WebElement saveChangesButton = driver.findElement(By.linkText("saveChangesButton"));
        saveChangesButton.click();

        WebElement updatedName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("nameField")));
        Assert.assertEquals(updatedName.getAttribute("value"), "New Valid Name", "LS_TC1: Fail - Name was not updated correctly");
        System.out.println("LS_TC1: Pass - Name was updated to the new name entered");
    }
    @Test(priority = 2)
    public void testUpdatePhoneNumberWithValidValue() {
        WebElement editPhoneButton = driver.findElement(By.linkText("editPhoneButton"));
        editPhoneButton.click();

        WebElement phoneField = driver.findElement(By.linkText("phoneField"));
        phoneField.clear();
        phoneField.sendKeys("1234567890");

        WebElement saveChangesButton = driver.findElement(By.linkText("saveChangesButton"));
        saveChangesButton.click();

        WebElement updatedPhone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("phoneField")));
        Assert.assertEquals(updatedPhone.getAttribute("value"), "1234567890", "LS_TC2: Fail - Phone number was not updated correctly");
        System.out.println("LS_TC2: Pass - Phone number was updated successfully");
    }

    @Test(priority = 3)
    public void testUpdateNameWithInvalidEntry() {
        WebElement editNameButton = driver.findElement(By.id("edit Name"));
        editNameButton.click();

        WebElement nameField = driver.findElement(By.id("nameField"));
        nameField.clear();
        nameField.sendKeys("Invalid@Name!");
        
        WebElement saveChangesButton = driver.findElement(By.id("saveChangesButton"));
        saveChangesButton.click();

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameError")));
        Assert.assertTrue(errorMessage.isDisplayed(), "LS_TC3: Fail - Error message was not displayed for invalid name");
        System.out.println("LS_TC3: Pass - Valid error message displayed for invalid name");
    }
    @Test(priority = 4)
    public void testUpdatePhoneNumberWithInvalidEntry() {
        WebElement editPhoneButton = driver.findElement(By.id("editPhoneButton"));
        editPhoneButton.click();

        WebElement phoneField = driver.findElement(By.id("phoneField"));
        phoneField.clear();
        phoneField.sendKeys("InvalidPhone");

        WebElement saveChangesButton = driver.findElement(By.id("saveChangesButton"));
        saveChangesButton.click();

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phoneError")));
        Assert.assertTrue(errorMessage.isDisplayed(), "LS_TC4: Fail - Error message was not displayed for invalid phone number");
        System.out.println("LS_TC4: Pass - Valid error message displayed for invalid phone number");
    }
    @Test(priority = 5)
    public void testAddNewPhoneNumber() {
        WebElement addPhoneNumberButton = driver.findElement(By.id("addPhoneNumberButton"));
        addPhoneNumberButton.click();
        
        WebElement newPhoneField = driver.findElement(By.id("newPhoneField"));
        newPhoneField.clear();
        newPhoneField.sendKeys("0987654321");

        WebElement saveChangesButton = driver.findElement(By.id("saveChangesButton"));
        saveChangesButton.click();

        WebElement updatedPhone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("newPhoneField")));
        Assert.assertEquals(updatedPhone.getAttribute("value"), "0987654321", "LS_TC5: Fail - New phone number was not added correctly");
        System.out.println("LS_TC5: Pass - New phone number added successfully");
    }
    @Test(priority = 6)
    public void testAddNewEmailAddress() {
        WebElement addEmailButton = driver.findElement(By.id("addEmailButton"));
        addEmailButton.click();

        WebElement newEmailField = driver.findElement(By.id("newEmailField"));
        newEmailField.clear();
        newEmailField.sendKeys("newemail@example.com");

        WebElement saveChangesButton = driver.findElement(By.id("saveChangesButton"));
        saveChangesButton.click();

        WebElement updatedEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("newEmailField")));
        Assert.assertEquals(updatedEmail.getAttribute("value"), "newemail@example.com", "LS_TC6: Fail - New email address was not added correctly");
        System.out.println("LS_TC6: Pass - New email address added successfully");
    }
    @Test(priority = 7)
    public void testAddPhoneNumberWithInvalidEntry() {
        WebElement addPhoneNumberButton = driver.findElement(By.id("addPhoneNumberButton"));
        addPhoneNumberButton.click();

        WebElement newPhoneField = driver.findElement(By.id("newPhoneField"));
        newPhoneField.clear();
        newPhoneField.sendKeys("InvalidPhone");

        WebElement saveChangesButton = driver.findElement(By.id("saveChangesButton"));
        saveChangesButton.click();

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phoneError")));
        Assert.assertTrue(errorMessage.isDisplayed(), "LS_TC7: Fail - Error message was not displayed for invalid phone number");
        System.out.println("LS_TC7: Pass - Valid error message displayed for invalid phone number");
    }
    @Test(priority = 8)
    public void testAddEmailIDWithInvalidEntry() {
        WebElement addEmailButton = driver.findElement(By.id("addEmailButton"));
        addEmailButton.click();

        WebElement newEmailField = driver.findElement(By.id("newEmailField"));
        newEmailField.clear();
        newEmailField.sendKeys("invalidemail@");

        WebElement saveChangesButton = driver.findElement(By.id("saveChangesButton"));
        saveChangesButton.click();

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailError")));
        Assert.assertTrue(errorMessage.isDisplayed(), "LS_TC8: Fail - Error message was not displayed for invalid email ID");
        System.out.println("LS_TC8: Pass - Valid error message displayed for invalid email ID");
    }
    @Test(priority = 9)
    public void testDeletePhoneNumber() {
        WebElement deletePhoneButton = driver.findElement(By.id("deletePhoneButton"));
        deletePhoneButton.click();

        WebElement confirmDeleteButton = driver.findElement(By.id("confirmDeleteButton"));
        confirmDeleteButton.click();

        Boolean deletedPhone = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("phoneField")));
        Assert.assertTrue(deletedPhone, "LS_TC9: Fail - Phone number was not deleted");
        System.out.println("LS_TC9: Pass - Phone number deleted successfully");
    }
    @Test(priority = 10)
    public void testDeleteEmailAddress() {
        WebElement deleteEmailButton = driver.findElement(By.id("deleteEmailButton"));
        deleteEmailButton.click();

        WebElement confirmDeleteButton = driver.findElement(By.id("confirmDeleteButton"));
        confirmDeleteButton.click();

        Boolean deletedEmail = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("emailField")));
        Assert.assertTrue(deletedEmail, "LS_TC10: Fail - Email address was not deleted");
        System.out.println("LS_TC10: Pass - Email address deleted successfully");
    }
    @Test(priority = 11)
    public void testDeletePrimaryEmailAddress() {
        WebElement deletePrimaryEmailButton = driver.findElement(By.id("deletePrimaryEmailButton"));
        deletePrimaryEmailButton.click();
        
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("primaryEmailError")));
        Assert.assertTrue(errorMessage.isDisplayed(), "LS_TC11: Fail - Error message was not displayed when deleting the primary email address");
        System.out.println("LS_TC11: Pass - Error message displayed when attempting to delete the primary email address");
    }

    @AfterMethod
    public void closebrowser() {
        driver.quit();
    }
}

