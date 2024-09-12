package project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import amazon.pages.AccountPage;
import amazon.pages.HomePage;

import org.openqa.selenium.By;

public class YourAccountTest {

    WebDriver driver;
    WebDriverWait wait;
    HomePage homePage;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/priyankaguliani/eclipse-workspace/amazon/chromedriver");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = new HomePage(driver);

        homePage.navigateToHomePage();
        homePage.login("amazonproject@myyahoo.com", "RoiciansTest");
    }

    @Test
    public void VerifyMessage() {
        HomePage homePage = new HomePage(driver);
        String expectedMessage = "Hello, Priyanka Account & Lists";
        String actualMessage = homePage.getAccountMessage();
        
        Assert.assertEquals(actualMessage, expectedMessage, "The account message did not match the expected value!");
    }

    @Test
    public void Sublist() {
        HomePage homePage = new HomePage(driver);
        Actions actions = new Actions(driver);
        homePage.hoverOverAccountMessage(actions);
        
        AccountPage accountPage = new AccountPage(driver);
        WebElement ordersLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Your Orders']")));
        ordersLink.click();

        Assert.assertTrue(accountPage.isYourOrdersHeaderDisplayed(), "The 'Your Orders' page did not load successfully.");
    }

    @Test
    public void VerifyClickOnAccountMessage() {
        HomePage homePage = new HomePage(driver);
        homePage.clickSignIn();

        AccountPage accountPage = new AccountPage(driver);
        Assert.assertTrue(accountPage.isYourAccountHeaderDisplayed(), "The 'Your Account' page did not load successfully.");
    }

    @AfterMethod
    public void closebrowser() {
        driver.quit();
    }
}
