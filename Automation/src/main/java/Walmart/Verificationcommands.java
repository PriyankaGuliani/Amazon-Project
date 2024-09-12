package Walmart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Verificationcommands {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver","/Users/priyankaguliani/eclipse-workspace/SeleniumProject/chromedriver");
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com/");
		
		boolean result = driver.findElement(By.linkText("Gmail")).isDisplayed();
		
		System.out.println(result);
		
		boolean result2 = driver.findElement(By.cssSelector(".gb_x")).isEnabled();
		
		System.out.println(result2);
		driver.quit();

	}

}
