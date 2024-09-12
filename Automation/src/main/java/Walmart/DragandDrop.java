package Walmart;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DragandDrop {

	public static void main(String[] args) {
		
	System.setProperty("webdriver.chrome.driver","/Users/priyankaguliani/eclipse-workspace/SeleniumProject/chromedriver");
	
	// open the url
	ChromeDriver driver = new ChromeDriver();
	driver.get("https://demoqa.com/droppable");
	
	Actions action = new Actions(driver);
	action.dragAndDrop(driver.findElement(By.id("draggable")), driver.findElement(By.id("droppable"))).build().perform();
	
	// driver.quit();
	
	}

}
