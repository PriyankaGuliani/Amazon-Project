package project;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import amazon.pages.AddressPage;
import amazon.pages.HomePage;
import amazon.pages.LoginSecurityPage;
import amazon.pages.YourAccountPage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;

public class YourAddressTest {

    WebDriver driver;
    AddressPage addressPage;
    WebDriverWait wait;
    HomePage homePage;
    YourAccountPage accountPage;
    LoginSecurityPage loginSecurityPage;

    @BeforeMethod
    public void setUp() {
    	System.setProperty("webdriver.chrome.driver", "/Users/priyankaguliani/eclipse-workspace/amazon/chromedriver");
        
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        accountPage = new YourAccountPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        loginSecurityPage = new LoginSecurityPage(driver, wait);

        homePage.navigateToHomePage();
        homePage.login("amazonproject@myyahoo.com", "RoiciansTest");
        accountPage.goToYourAddresses();
    }

    @Test(priority = 1)
    public void testNavigateToAddAddress() {
        addressPage.clickAddAddress();
        Assert.assertTrue(driver.getCurrentUrl().contains("addAddress"), "Navigation to add address page failed.");
    }

    @Test(priority = 2)
    public void testAutoSelectCountry() {
        addressPage.clickAddAddress();
        String selectedCountry = addressPage.getSelectedCountry();
        Assert.assertEquals(selectedCountry, "Canada", "Canada should be auto-selected in the country field.");
    }

    @Test(priority = 3)
    public void testEmptyFullName() {
        addressPage.clickAddAddress();
        addressPage.enterFullName(""); 
        WebElement fullNameError = driver.findElement(By.id("fullNameErrorMsg")); 
        Assert.assertTrue(fullNameError.isDisplayed(), "Error message for empty full name should be displayed.");
    }

    @Test(priority = 4)
    public void testValidPhoneNumber() {
        addressPage.clickAddAddress();
        addressPage.enterPhoneNumber("1234567890");
        WebElement phoneNumberError = driver.findElement(By.id("phoneNumberErrorMsg"));
        Assert.assertFalse(phoneNumberError.isDisplayed(), "Phone number should be valid, but error message is displayed.");
    }

    @Test(priority = 5)
    public void testAddressField() {
        addressPage.clickAddAddress();
        addressPage.enterAddress("123 Test Street");
        Assert.assertEquals(addressPage.getAddressFieldValue(), "123 Test Street", "Address should be correctly entered.");
    }

    @Test(priority = 6)
    public void testCityAndProvince() {
        addressPage.clickAddAddress();
        addressPage.enterCity("Toronto");
        addressPage.selectProvince("Ontario");
        Assert.assertEquals(addressPage.getCityValue(), "Toronto", "City should be Toronto.");
        Assert.assertEquals(addressPage.getProvinceValue(), "Ontario", "Province should be Ontario.");
    }

    @Test(priority = 7)
    public void testPostalCode() {
        addressPage.clickAddAddress();
        addressPage.enterPostalCode("M5H 2N2");
        Assert.assertEquals(addressPage.getPostalCodeValue(), "M5H 2N2", "Postal code should be correctly entered.");
    }

    @Test(priority = 8)
    public void testSetAsDefaultAddress() {
        addressPage.clickAddAddress();
        addressPage.setAsDefaultAddress();
        Assert.assertTrue(addressPage.isDefaultAddressChecked(), "The default address option should be checked.");
    }

    @Test(priority = 9)
    public void testAdditionalInstructions() {
        addressPage.clickAddAddress();
        addressPage.addAdditionalInstructions("Leave at front desk");
        Assert.assertEquals(addressPage.getAdditionalInstructionsValue(), "Leave at front desk", "Additional instructions should be correctly entered.");
    }

    @Test(priority = 10)
    public void testSaveAddress() {
        addressPage.clickAddAddress();
        addressPage.enterFullName("John Doe");
        addressPage.enterPhoneNumber("1234567890");
        addressPage.enterAddress("123 Test Street");
        addressPage.enterCity("Toronto");
        addressPage.selectProvince("Ontario");
        addressPage.enterPostalCode("M5H 2N2");
        addressPage.setAsDefaultAddress();
        addressPage.addAdditionalInstructions("Leave at front desk");
        addressPage.saveAddress();

        Assert.assertTrue(addressPage.isAddressSaved(), "Address should be saved successfully.");
    }
    @AfterMethod
    public void closeBrowser() {
    	driver.quit();
    	}
}
