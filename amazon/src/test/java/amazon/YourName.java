package amazon;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class YourName {
	ChromeDriver driver= new ChromeDriver();

@BeforeMethod
public void openbrowser() {
	System.setProperty("webdriver.chrome.driver","/Users/priyankaguliani/eclipse-workspace/amazon/chromedriver");
	driver.get("https://www.amazon.ca/ap/register?showRememberMe=true&openid.pape.max_auth_age=900&openid.return_to=https%3A%2F%2Fwww.amazon.ca%2Fgp%2Fyourstore%2Fhome%3Fpath%3D%252Fgp%252Fyourstore%252Fhome%26useRedirectOnSuccess%3D1%26signIn%3D1%26action%3Dsign-out%26ref_%3Dnav_AccountFlyout_signout&prevRID=EEC1KGXDY8RVGS1K526G&openid.assoc_handle=caflex&openid.mode=checkid_setup&prepopulatedLoginId=&failedSignInCount=0&pageId=caflex&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0/");	
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

@AfterMethod
public void closebrowser() {
driver.quit();

}
}
