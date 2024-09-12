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

public class YourPaymentsTest {
	WebDriver driver;
	WebDriverWait wait;
	
    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/priyankaguliani/eclipse-workspace/amazon/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.ca");
        
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
        WebElement yourPaymentsLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Your Payments")));
        yourPaymentsLink.click();
    }

    @Test(priority = 1)
    public void testAddPaymentMethod() {
        // Test Case 1: Adding a payment method
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize the wait object

        WebElement addPaymentMethodButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Add a Payment Method")));
        addPaymentMethodButton.click();

        WebElement addCardButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='submit'][class='a-button-input']")));
        addCardButton.click();

        WebElement cardNumberInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[data-testid='card-text-input']")));
        cardNumberInput.sendKeys("5123456789012346"); 

        WebElement expiryDateInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='MM/YY']"))); 
        expiryDateInput.sendKeys("0524");

        WebElement securityCodeInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Security code']"))); 
        securityCodeInput.sendKeys("100");

        WebElement nameOnCardInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Name on card']"))); 
        nameOnCardInput.sendKeys("John Doe");

        WebElement addAndContinueButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[data-testid='text']")));
        addAndContinueButton.click();

        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("success_message")));

        Assert.assertTrue(successMessage.isDisplayed(), "YP_TC1: Fail - Payment method was not added successfully");
        System.out.println("YP_TC1: Pass - Payment method was added successfully");
    }

    @Test(priority = 2)
    public void testAddSecondaryPaymentMethod() {
        // Test Case 2: Adding a secondary payment method
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement addPaymentMethodButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Add a Payment Method")));
        addPaymentMethodButton.click();

        WebElement addCardButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='submit'][class='a-button-input']")));
        addCardButton.click();

        WebElement cardNumberInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[data-testid='card-text-input']")));
        cardNumberInput.sendKeys("5555555555554444"); 

        WebElement expiryDateInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='MM/YY']"))); 
        expiryDateInput.sendKeys("0599");

        WebElement securityCodeInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Security code']"))); 
        securityCodeInput.sendKeys("100");

        WebElement nameOnCardInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Name on card']"))); 
        nameOnCardInput.sendKeys("Jane Doe");

        WebElement addAndContinueButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[data-testid='text']")));
        addAndContinueButton.click();

        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("success_message")));

        Assert.assertTrue(successMessage.isDisplayed(), "YP_TC2: Fail - Secondary payment method was not added successfully");
        System.out.println("YP_TC2: Pass - Secondary payment method was added successfully");
    }

    @Test(priority = 3)
    public void testAddInvalidPaymentMethod() {
        // Test Case 3: Adding an invalid payment method
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement addPaymentMethodButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Add a Payment Method")));
        addPaymentMethodButton.click();

        WebElement addCardButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='submit'][class='a-button-input']")));
        addCardButton.click();

        WebElement cardNumberInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[data-testid='card-text-input']")));
        cardNumberInput.sendKeys("1234567890123456"); // Invalid card number

        WebElement expiryDateInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='MM/YY']"))); 
        expiryDateInput.sendKeys("0020"); // Invalid expiry date (past date)

        WebElement securityCodeInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Security code']"))); 
        securityCodeInput.sendKeys("abc"); // Invalid security code (non-numeric)

        WebElement nameOnCardInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Name on card']"))); 
        nameOnCardInput.sendKeys(""); // Empty name on card field

        WebElement addAndContinueButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[data-testid='text']")));
        addAndContinueButton.click();

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[role='alert']")));
        
        Assert.assertTrue(errorMessage.getText().contains("Invalid card number"), "YP_TC3: Fail - Invalid payment method was accepted");
        System.out.println("YP_TC3: Pass - Invalid payment method was not accepted and appropriate error message displayed");
    }

    @Test(priority = 4)
    public void testSetNewDefaultPaymentMethod() {
        // Test Case 4: Setting a new payment method as default
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement addPaymentMethodButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Add a Payment Method")));
        addPaymentMethodButton.click();

        WebElement addCardButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='submit'][class='a-button-input']")));
        addCardButton.click();

        WebElement cardNumberInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[data-testid='card-text-input']")));
        cardNumberInput.sendKeys("4111111111111111");

        // Verify that the option to set as default is present
        WebElement setDefaultLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Set as default')]")));
        WebElement setDefaultCheckbox = setDefaultLabel.findElement(By.xpath("preceding-sibling::input[@type='checkbox']"));

        // Verify that the checkbox is present and clickable
        Assert.assertTrue(setDefaultCheckbox.isDisplayed());
       
        setDefaultCheckbox.click();

        WebElement addAndContinueButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[data-testid='text']")));
        addAndContinueButton.click();

        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("success_message"))); // Adjust this selector as needed
        Assert.assertTrue(successMessage.isDisplayed(), "YP_TC4: Fail - Default payment method was not set successfully");

        System.out.println("YP_TC4: Pass - Default payment method was set successfully");
    }

    @Test(priority = 5)
    public void testSetExistingPaymentMethodAsDefault() {
        // Test Case 5: Setting an existing payment method as default
        WebElement existingPaymentMethod = driver.findElement(By.xpath("//div[@class='payment-method'][1]"));
        existingPaymentMethod.click();

        WebElement setDefaultLabel = driver.findElement(By.xpath("//label[contains(text(),'Set as default')]"));
        WebElement setDefaultCheckbox = setDefaultLabel.findElement(By.xpath("preceding-sibling::input[@type='checkbox']"));

        setDefaultCheckbox.click();
        WebElement saveButton = driver.findElement(By.id("save_button"));
        saveButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("success_message")));

        if (successMessage.isDisplayed()) {
            System.out.println("YP_TC5: Pass - Existing payment method was set as default");
        } else {
            System.out.println("YP_TC5: Fail - Option to set payment method as default was not available");
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
