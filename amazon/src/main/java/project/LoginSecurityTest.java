package project;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import amazon.pages.HomePage;
import amazon.pages.LoginSecurityPage;
import amazon.pages.YourAccountPage;

public class LoginSecurityTest {
    WebDriver driver;
    WebDriverWait wait;
    HomePage homePage;
    YourAccountPage accountPage;
    LoginSecurityPage loginSecurityPage;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/priyankaguliani/eclipse-workspace/amazon/chromedriver");
        
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        homePage = new HomePage(driver);
        accountPage = new YourAccountPage(driver);
        loginSecurityPage = new LoginSecurityPage(driver, wait);

        homePage.navigateToHomePage();
        homePage.login("amazonproject@myyahoo.com", "RoiciansTest");
        accountPage.goToLoginSecurity();
    }

    @Test(priority = 1)
    public void testUpdateNameWithValidValue() {
        loginSecurityPage.updateName("New Valid Name");
        Assert.assertEquals(loginSecurityPage.getName(), "New Valid Name", "LS_TC1: Fail - Name was not updated correctly");
        System.out.println("LS_TC1: Pass - Name was updated to the new name entered");
    }

    @Test(priority = 2)
    public void testUpdatePhoneNumberWithValidValue() {
        loginSecurityPage.updatePhoneNumber("1234567890");
        Assert.assertEquals(loginSecurityPage.getPhoneNumber(), "1234567890", "LS_TC2: Fail - Phone number was not updated correctly");
        System.out.println("LS_TC2: Pass - Phone number was updated successfully");
    }

    @Test(priority = 3)
    public void testUpdateNameWithInvalidEntry() {
        loginSecurityPage.updateName("Invalid@Name!");
        Assert.assertTrue(loginSecurityPage.isErrorMessageDisplayed("nameError"), "LS_TC3: Fail - Error message was not displayed for invalid name");
        System.out.println("LS_TC3: Pass - Valid error message displayed for invalid name");
    }

    @Test(priority = 4)
    public void testUpdatePhoneNumberWithInvalidEntry() {
        loginSecurityPage.updatePhoneNumber("InvalidPhone");
        Assert.assertTrue(loginSecurityPage.isErrorMessageDisplayed("phoneError"), "LS_TC4: Fail - Error message was not displayed for invalid phone number");
        System.out.println("LS_TC4: Pass - Valid error message displayed for invalid phone number");
    }

    @Test(priority = 5)
    public void testDeletePhoneNumber() {
        loginSecurityPage.deletePhoneNumber();
        Assert.assertTrue(loginSecurityPage.isFieldInvisible("phoneField"), "LS_TC5: Fail - Phone number was not deleted");
        System.out.println("LS_TC5: Pass - Phone number deleted successfully");
    }

    @Test(priority = 6)
    public void testAddNewEmailAddress() {
        loginSecurityPage.addNewEmailAddress("newemail@example.com");
        Assert.assertEquals(loginSecurityPage.getEmailField(), "newemail@example.com", "LS_TC6: Fail - New email address was not added correctly");
        System.out.println("LS_TC6: Pass - New email address added successfully");
    }

    @Test(priority = 7)
    public void testAddPhoneNumberWithInvalidEntry() {
        loginSecurityPage.addPhoneNumber("InvalidPhone");
        Assert.assertTrue(loginSecurityPage.isErrorMessageDisplayed("phoneError"), "LS_TC7: Fail - Error message was not displayed for invalid phone number");
        System.out.println("LS_TC7: Pass - Valid error message displayed for invalid phone number");
    }

    @Test(priority = 8)
    public void testAddEmailIDWithInvalidEntry() {
        loginSecurityPage.addNewEmailAddress("invalidemail@");
        Assert.assertTrue(loginSecurityPage.isErrorMessageDisplayed("emailError"), "LS_TC8: Fail - Error message was not displayed for invalid email ID");
        System.out.println("LS_TC8: Pass - Valid error message displayed for invalid email ID");
    }

    @Test(priority = 9)
    public void testDeletePhoneNumber2() {
        loginSecurityPage.deletePhoneNumber();
        Assert.assertTrue(loginSecurityPage.isFieldInvisible("phoneField"), "LS_TC9: Fail - Phone number was not deleted");
        System.out.println("LS_TC9: Pass - Phone number deleted successfully");
    }

    @Test(priority = 10)
    public void testDeleteEmailAddress() {
        loginSecurityPage.deleteEmailAddress();
        Assert.assertTrue(loginSecurityPage.isFieldInvisible("emailField"), "LS_TC10: Fail - Email address was not deleted");
        System.out.println("LS_TC10: Pass - Email address deleted successfully");
    }

    @Test(priority = 11)
    public void testDeletePrimaryEmailAddress() {
        loginSecurityPage.deletePrimaryEmailAddress();
        Assert.assertTrue(loginSecurityPage.isErrorMessageDisplayed("primaryEmailError"), "LS_TC11: Fail - Error message was not displayed when deleting the primary email address");
        System.out.println("LS_TC11: Pass - Error message displayed when attempting to delete the primary email address");
    }

    @AfterMethod
    public void closebrowser() {
        driver.quit();
    }
}
