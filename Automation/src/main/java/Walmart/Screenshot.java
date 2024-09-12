package Walmart;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.io.Files;
import java.io.IOException;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Screenshot {

	public static void main(String[] args) throws IOException {
		System.setProperty("webdriver.chrome.driver","/Users/priyankaguliani/eclipse-workspace/SeleniumProject/chromedriver");
		
		ChromeDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("https://www.roicians.com/training-service/");
		
		driver.manage().window().maximize();	
		
		driver.navigate().to("https://www.roicians.com/contact-us/");
		
		File reference = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Files.copy(reference, new File("/Users/priyankaguliani/eclipse-workspace/SeleniumProject/screenshots/screenshot.png"));
		
		driver.quit();

	}

}
