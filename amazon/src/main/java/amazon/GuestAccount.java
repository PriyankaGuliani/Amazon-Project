package amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class GuestAccount {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "/Users/priyankaguliani/eclipse-workspace/amazon/chromedriver");
		WebDriver driver = new ChromeDriver();
        driver.get("https://www.amazon.ca");
        
        String pageSource = driver.getPageSource();
        Assert.assertFalse(pageSource.contains("Sign In"), "GA_TC1: Fail - Sign-in prompt appeared");
        System.out.println("GA_TC1: Pass - Guest user can browse without sign-in prompt");
        
        driver.findElement(By.linkText("Electronics")).click(); 

	}

}
