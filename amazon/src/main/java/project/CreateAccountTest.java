package project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import amazon.pages.HomePage;
import amazon.pages.SignupPage;

public class CreateAccountTest {
    WebDriver driver;
    SignupPage signupPage;
    HomePage homePage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        signupPage = new SignupPage(driver);
        homePage = new HomePage(driver);
        homePage.navigateToHomePage();
        Actions actions = new Actions(driver);
        homePage.CreateAccount(actions);
        }

    @Test
    public void testNameFieldCannotBeEmpty() {
        signupPage.enterName(""); 
        signupPage.clickCreateAccount();
        Assert.assertTrue(signupPage.getErrorMessage().contains("Enter your name"));
    }
    
    @Test
    public void testEmailFieldCannotBeEmpty() {
        signupPage.enterEmail("");
        signupPage.clickCreateAccount();
        Assert.assertTrue(signupPage.getErrorMessage().contains("Enter your mobile number or email address"));
    }

    @Test
    public void testVerifyMobileNumberButtonAppears() {
        signupPage.enterEmail("1234567890"); 
        signupPage.clickCreateAccount();
        Assert.assertTrue(signupPage.isVerifyMobileNumberButtonVisible());
    }

    @Test
    public void testVerifyEmailButtonAppears() {
        signupPage.enterEmail("test@example.com");
        signupPage.clickCreateAccount();
        Assert.assertTrue(signupPage.isVerifyEmailButtonVisible());
    }

    @Test
    public void testPasswordMinLength() {
        signupPage.enterPassword("12345");
        signupPage.clickCreateAccount();
        Assert.assertTrue(signupPage.getErrorMessage().contains("Passwords must be at least 6 characters."));
    }

    @Test
    public void testPasswordMatch() {
        signupPage.enterPassword("123456");
        signupPage.enterPasswordCheck("654321");
        signupPage.clickCreateAccount();
        Assert.assertTrue(signupPage.getErrorMessage().contains("Passwords do not match."));
    }

    @Test
    public void testReturningCustomerMessage() {
        signupPage.enterEmail("amazonproject@myyahoo.com"); 
        signupPage.clickCreateAccount();
        Assert.assertTrue(signupPage.getErrorMessage().contains("Are you a returning customer?"));
    }

    @Test
    public void testOTPSentForNewAccount() {
        signupPage.enterEmail("amazon@google.com"); 
        signupPage.clickCreateAccount();
        
        WebElement otpMessageElement = driver.findElement(By.id("otpMessage"));
        Assert.assertTrue(otpMessageElement.isDisplayed(), "OTP message should be displayed.");
        
    }

    @Test
    public void testInvalidEmailError() {
        signupPage.enterName("New User");
        signupPage.enterEmail("invalid-email");
        signupPage.enterPassword("Password123");
        signupPage.enterPasswordCheck("Password123");
        signupPage.clickCreateAccount();
        
        Assert.assertTrue(signupPage.getErrorMessage().contains("Enter a valid email address"), "Error message for invalid email should be displayed.");
    }
    @Test
    public void testEmptyPasswordError() {
        signupPage.enterName("New User");
        signupPage.enterEmail("testuser@example.com");
        signupPage.enterPassword(""); 
        signupPage.enterPasswordCheck("");
        signupPage.clickCreateAccount();
        
        Assert.assertTrue(signupPage.getErrorMessage().contains("Enter your password"), "Error message for empty password should be displayed.");
    }

    @Test
    public void testPasswordMismatchError() {
        signupPage.enterName("Test User");
        signupPage.enterEmail("testuser@example.com");
        signupPage.enterPassword("Password123");
        signupPage.enterPasswordCheck("Password321");

        signupPage.clickCreateAccount();
        
        Assert.assertTrue(signupPage.getErrorMessage().contains("Passwords do not match"), "Error message for password mismatch should be displayed.");
    }

    @AfterMethod
    public void closeBrowser() {
    	driver.quit();
    }
}
