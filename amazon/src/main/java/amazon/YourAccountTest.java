package amazon;

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
import org.openqa.selenium.By;
import java.time.Duration;

public class YourAccountTest {

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
    }

    @Test
    public void VerifyMessage() {
        WebElement accountMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-link-accountList")));
        String expectedMessage = "Hello, Priyanka Account & Lists";
        String actualMessage = accountMessage.getText();
        
        Assert.assertEquals(actualMessage, expectedMessage, "The account message did not match the expected value!");
    }

    @Test
    public void Sublist() {
        Actions actions = new Actions(driver);
        WebElement accountMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-link-accountList")));
        actions.moveToElement(accountMessage).perform();

        WebElement ordersLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Your Orders']")));
        ordersLink.click();

        WebElement ordersPageHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Your Orders')]")));
        Assert.assertTrue(ordersPageHeader.isDisplayed(), "The 'Your Orders' page did not load successfully.");
    }

    @Test
    public void VerifyClickOnAccountMessage() {
        WebElement accountMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-link-accountList")));
        accountMessage.click();

        WebElement yourAccountHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Your Account')]")));
        Assert.assertTrue(yourAccountHeader.isDisplayed(), "The 'Your Account' page did not load successfully.");
    }

    @AfterMethod
    public void closebrowser() {
        driver.quit();
    }
}
