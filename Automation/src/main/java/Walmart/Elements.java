package Walmart;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Elements {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver","/Users/priyankaguliani/eclipse-workspace/SeleniumProject/chromedriver");
		
		// open the url
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.roicians.com/career/");
		
		Select obj=new Select(driver.findElement(By.id("field-jHhehcNamzHL47m")));
		
		obj.selectByVisibleText("Automation Testing Trainer"); // method 1
		
		obj.selectByValue("Automation Testing Trainer"); // method 2
		
		obj.selectByIndex(3); //method 3
		
		driver.quit();
	}

}
