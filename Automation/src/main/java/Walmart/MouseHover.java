package Walmart;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MouseHover {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver","/Users/priyankaguliani/eclipse-workspace/SeleniumProject/chromedriver");
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.roicians.com/");
		
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.linkText("Courses"))).build().perform();
		
		driver.findElement(By.partialLinkText("SOFTWARE TESTING")).click();
		
		driver.quit(); //closes all windows tabs which are open
		}

}
