package Walmart;

import org.openqa.selenium.chrome.ChromeDriver;

public class Navigation {

	public static void main(String[] args) {
		//setting up the browser
				System.setProperty("webdriver.chrome.driver","/Users/priyankaguliani/eclipse-workspace/SeleniumProject/chromedriver");

				//open the url
				ChromeDriver driver = new ChromeDriver();
				driver.get("https://www.roicians.com/contact-us/");
				
				//maximize the window
				driver.manage().window().maximize();
				
				//navigate to a different page
				driver.navigate().to("https://www.google.com/");
				
				//navigate to the back page
				driver.navigate().back();
				
				//navigate to the forward page
				driver.navigate().forward();
				
				//refresh page
				driver.navigate().refresh();
				
				//close the page
				driver.close();
				
				// to quit the page
				driver.quit();
	}

}
