package amazon;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class YourOrdersTest {
	WebDriver driver;
	WebDriverWait wait;
	
    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/priyankaguliani/eclipse-workspace/amazon/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.ca");
        
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement signInButton = driver.findElement(By.id("nav-link-accountList-nav-line-1"));
        signInButton.click();
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_email")));
        emailField.sendKeys("priyankaguliani18@gmail.com");
        WebElement continueButton = driver.findElement(By.id("continue"));
        continueButton.click();
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_password")));
        passwordField.sendKeys("Guliani123");
        WebElement loginButton = driver.findElement(By.id("signInSubmit"));
        loginButton.click();
        
        WebElement accountList = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-link-accountList")));
        accountList.click();
        WebElement yourPaymentsLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Your Orders")));
        yourPaymentsLink.click();
    }

    @Test(priority = 1)
    public void testNavigateToOrderHistoryPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginSecurityPage")));
        WebElement orderHistoryHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("orderHistoryHeader")));
        Assert.assertTrue(orderHistoryHeader.isDisplayed(), "YO_TC1: Fail - Did not navigate to the Order History page");
        System.out.println("YO_TC1: Pass - Navigated to the Order History page successfully");
    }

    @Test(priority = 2)
    public void testViewOrdersForPast3Months() {
        WebElement dropdown = driver.findElement(By.id("timeRangeDropdown"));
        dropdown.click();

        WebElement past3MonthsOption = driver.findElement(By.xpath("//option[text()='Past 3 months']"));
        past3MonthsOption.click();

        WebElement ordersDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ordersTable")));
        Assert.assertTrue(ordersDisplayed.isDisplayed(), "YO_TC2: Fail - Orders for the past 3 months are not displayed");
        System.out.println("YO_TC2: Pass - Orders for the past 3 months are displayed correctly");
    }

    @Test(priority = 3)
    public void testViewOrdersForThisYear() {
        WebElement dropdown = driver.findElement(By.id("timeRangeDropdown"));
        dropdown.click();

        WebElement thisYearOption = driver.findElement(By.xpath("//option[text()='This year']"));
        thisYearOption.click();

        WebElement ordersDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ordersTable")));
        Assert.assertTrue(ordersDisplayed.isDisplayed(), "YO_TC3: Fail - Orders for this year are not displayed");
        System.out.println("YO_TC3: Pass - Orders for this year are displayed correctly");
    }

    @Test(priority = 4)
    public void testViewArchivedOrders() {
        WebElement dropdown = driver.findElement(By.id("timeRangeDropdown"));
        dropdown.click();

        WebElement archivedOption = driver.findElement(By.xpath("//option[text()='Archived orders']"));
        archivedOption.click();

        WebElement ordersDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ordersTable")));
        Assert.assertTrue(ordersDisplayed.isDisplayed(), "YO_TC4: Fail - Archived orders are not displayed");
        System.out.println("YO_TC4: Pass - Archived orders are displayed correctly");
    }
 
    @Test(priority = 5)
    public void testBuyAgainOptionPresence() {
        WebElement buyAgainOption = driver.findElement(By.linkText("Buy Again"));
        Assert.assertTrue(buyAgainOption.isDisplayed(), "YO_TC5: Fail - 'Buy Again' option is not present");
        System.out.println("YO_TC5: Pass - 'Buy Again' option is present");
    }

    @Test(priority = 6)
    public void testBuyAgainFunctionality() {
        WebElement buyAgainOption = driver.findElement(By.linkText("Buy Again"));
        buyAgainOption.click();

        WebElement recommendationsWindow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("recommendationsWindow")));
        Assert.assertTrue(recommendationsWindow.isDisplayed(), "YO_TC6: Fail - Recommendations window did not open");
        System.out.println("YO_TC6: Pass - Recommendations window opened successfully");
    }

    @Test(priority = 7)
    public void testBuyAgainRecommendations() {
        WebElement buyAgainOption = driver.findElement(By.linkText("Buy Again"));
        buyAgainOption.click();

        WebElement recommendations = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("recommendationsList")));
        Assert.assertTrue(recommendations.isDisplayed(), "YO_TC7: Fail - Recommendations are not displayed based on purchase history");
        System.out.println("YO_TC7: Pass - Recommendations are shown based on purchase history");
    }
    
    @Test(priority = 8)
    public void testNotYetShippedOptionPresence() {
        WebElement notYetShippedOption = driver.findElement(By.linkText("Not yet shipped"));
        Assert.assertTrue(notYetShippedOption.isDisplayed(), "YO_TC8: Fail - 'Not yet shipped' option is not present");
        System.out.println("YO_TC8: Pass - 'Not yet shipped' option is present");
    }

    @Test(priority = 9)
    public void testNotYetShippedFunctionality() {
        WebElement notYetShippedOption = driver.findElement(By.linkText("Not yet shipped"));
        notYetShippedOption.click();

        WebElement pendingOrders = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pendingOrdersTable")));
        Assert.assertTrue(pendingOrders.isDisplayed(), "YO_TC9: Fail - Pending orders are not displayed");
        System.out.println("YO_TC9: Pass - Pending orders are displayed correctly");
    }
    @Test(priority = 10)
    public void testCancelledOrdersOptionPresence() {
        WebElement cancelledOrderOption = driver.findElement(By.linkText("Cancelled order"));
        Assert.assertTrue(cancelledOrderOption.isDisplayed(), "YO_TC10: Fail - 'Cancelled order' option is not present");
        System.out.println("YO_TC10: Pass - 'Cancelled order' option is present");
    }

    @Test(priority = 11)
    public void testCancelledOrdersFunctionality() {
        WebElement cancelledOrderOption = driver.findElement(By.linkText("Cancelled order"));
        cancelledOrderOption.click();

        WebElement cancelledOrders = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cancelledOrdersTable")));
        Assert.assertTrue(cancelledOrders.isDisplayed(), "YO_TC11: Fail - Cancelled orders are not displayed");
        System.out.println("YO_TC11: Pass - Cancelled orders from the last 6 months are displayed correctly");
    }

    @AfterMethod
    public void closebrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
