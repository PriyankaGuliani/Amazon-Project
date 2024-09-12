package project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import amazon.pages.HomePage;
import amazon.pages.PaymentMethodsPage;
import amazon.pages.YourAccountPage;

public class YourPaymentsTest {
    WebDriver driver;
    HomePage homePage;
    YourAccountPage yourAccountPage;
    PaymentMethodsPage paymentMethodsPage;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/priyankaguliani/eclipse-workspace/amazon/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        homePage = new HomePage(driver);
        yourAccountPage = new YourAccountPage(driver);
        paymentMethodsPage = new PaymentMethodsPage(driver);

        homePage.navigateToHomePage();
        homePage.login("amazonproject@myyahoo.com", "RoiciansTest");

        yourAccountPage.goToYourPayments();
    }

    @Test(priority = 1)
    public void testAddPaymentMethod() {
        paymentMethodsPage.addPaymentMethod("5123456789012346", "0524", "100", "John Doe");
        Assert.assertTrue(paymentMethodsPage.isSuccessMessageDisplayed(), "YP_TC1: Fail - Payment method was not added successfully");
        System.out.println("YP_TC1: Pass - Payment method was added successfully");
    }

    @Test(priority = 2)
    public void testAddSecondaryPaymentMethod() {
        paymentMethodsPage.addPaymentMethod("5555555555554444", "0599", "100", "Jane Doe");
        Assert.assertTrue(paymentMethodsPage.isSuccessMessageDisplayed(), "YP_TC2: Fail - Secondary payment method was not added successfully");
        System.out.println("YP_TC2: Pass - Secondary payment method was added successfully");
    }

    @Test(priority = 3)
    public void testAddInvalidPaymentMethod() {
        paymentMethodsPage.addPaymentMethod("1234567890123456", "0020", "abc", "");
        Assert.assertTrue(paymentMethodsPage.isErrorMessageDisplayed("Invalid card number"), "YP_TC3: Fail - Invalid payment method was accepted");
        System.out.println("YP_TC3: Pass - Invalid payment method was not accepted and appropriate error message displayed");
    }

    @Test(priority = 4)
    public void testSetNewDefaultPaymentMethod() {
        paymentMethodsPage.addPaymentMethod("4111111111111111", "0524", "100", "John Doe");
        paymentMethodsPage.setDefaultPaymentMethod();
        Assert.assertTrue(paymentMethodsPage.isSuccessMessageDisplayed(), "YP_TC4: Fail - Default payment method was not set successfully");
        System.out.println("YP_TC4: Pass - Default payment method was set successfully");
    }

    @Test(priority = 5)
    public void testSetExistingPaymentMethodAsDefault() {
        // Assuming existing payment methods are already present
        WebElement existingPaymentMethod = driver.findElement(By.xpath("//div[@class='payment-method'][1]"));
        existingPaymentMethod.click();

        paymentMethodsPage.setDefaultPaymentMethod();

        WebElement successMessage = driver.findElement(By.id("success_message"));
        Assert.assertTrue(successMessage.isDisplayed(), "YP_TC5: Fail - Existing payment method was not set as default");
        System.out.println("YP_TC5: Pass - Existing payment method was set as default");
    }

    @AfterMethod
    public void closeBrowser() {
    	driver.quit();
    	}
}
