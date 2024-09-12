package Walmart;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver; //parent class

public class Signup {

	public static void main(String[] args) {
		// step 1- setting up the browser
		System.setProperty("webdriver.chrome.driver","/Users/priyankaguliani/eclipse-workspace/SeleniumProject/chromedriver");
 
		//open the url (walmart website)
		WebDriver driver = new ChromeDriver(); // top casting, borrowing method from parent class
		driver.get("https://www.walmart.ca/en/sign-in?vid=oaoh&tid=0&returnUrl=%2Fen");
		
		//maximize the window
		driver.manage().window().maximize();
				
		//navigate and enter text in the webelements
		driver.findElement(By.id("react-aria-1")).sendKeys("roicians@gmail.com");
		
		driver.findElement(By.id("login-continue-button")).click();
		
		driver.quit();
		
	}

}
