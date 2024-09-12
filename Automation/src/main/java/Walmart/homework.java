package Walmart;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class homework {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver","/Users/priyankaguliani/eclipse-workspace/SeleniumProject/chromedriver");
		
		ChromeDriver driver = new ChromeDriver();
		
		driver.get("https://www.roicians.com/career/");
		
		driver.findElement(By.id("text-248418663108")).sendKeys("Vaibhav");
		
		driver.findElement(By.id("text-58969147196")).sendKeys("Guliani");
		
		driver.findElement(By.id("text-25931186823")).sendKeys("vaibhavguliani23@gmail.com");
		
		driver.findElement(By.id("field-LfRVhp7sisH0kRP")).sendKeys("6475516015");
		
		Select obj=new Select(driver.findElement(By.id("field-jHhehcNamzHL47m")));
		
		obj.selectByVisibleText("Automation Testing Trainer"); 
		
		driver.findElement(By.id("field-U7H2lPxWxSPqWeq")).sendKeys("/Users/priyankaguliani/Downloads/Vaibhav/Resume_Vaibhav Guliani.pdf");
		
		driver.findElement(By.id("textarea-27447478041")).sendKeys("Job application");
		
		// driver.findElement(By.id("button-23567600038")).click();
		
		driver.quit();

	}

}