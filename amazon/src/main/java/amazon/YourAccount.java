package amazon;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class YourAccount {

	public static void main(String[] args) {
		WebDriver driver;
		WebDriverWait wait;
		System.setProperty("webdriver.chrome.driver", "/Users/priyankaguliani/eclipse-workspace/amazon/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.amazon.ca");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
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
        
        WebElement accountMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-link-accountList")));
       
        String expectedMessage = "Hello, Priyanka Account & Lists";
        String actualMessage = accountMessage.getText();
        System.out.println(actualMessage);
        if (actualMessage.equals(expectedMessage)) {
            System.out.println("Message verified successfully!");
        } else {
            System.out.println("Message verification failed!");
        }
        
        Actions actions = new Actions(driver);
    	actions.moveToElement(accountMessage).perform();
    	
    	WebElement ordersLink = driver.findElement(By.xpath("//span[text()='Your Orders']"));
    	ordersLink.click();
    	
    	WebElement ordersPageHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Your Orders')]")));
        Assert.assertTrue(ordersPageHeader.isDisplayed(), "The 'Your Orders' page did not load successfully.");
        
        //checking click on the message
        accountMessage.click();
        WebElement yourAccountHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Your Account')]")));
        Assert.assertTrue(yourAccountHeader.isDisplayed(), "The 'Your Account' page did not load successfully.");

	}

}
