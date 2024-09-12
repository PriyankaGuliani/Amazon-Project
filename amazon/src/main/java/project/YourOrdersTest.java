package project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import amazon.pages.HomePage;
import amazon.pages.YourOrders;

public class YourOrdersTest {
    WebDriver driver;
    HomePage homePage;
    YourOrders yourOrders;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/priyankaguliani/eclipse-workspace/amazon/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        homePage = new HomePage(driver);
        yourOrders = new YourOrders(driver);

        homePage.navigateToHomePage();
        homePage.login("amazonproject@myyahoo.com", "RoiciansTest");

        homePage.clickSignIn();
        yourOrders = new YourOrders(driver); 
    }

    @Test(priority = 1)
    public void testNavigateToOrderHistoryPage() {
        Assert.assertTrue(yourOrders.isOrderHistoryHeaderDisplayed(), "YO_TC1: Fail - Did not navigate to the Order History page");
        System.out.println("YO_TC1: Pass - Navigated to the Order History page successfully");
    }

    @Test(priority = 2)
    public void testViewOrdersForPast3Months() {
        yourOrders.selectTimeRange("Past 3 months");
        Assert.assertTrue(yourOrders.isOrdersTableDisplayed(), "YO_TC2: Fail - Orders for the past 3 months are not displayed");
        System.out.println("YO_TC2: Pass - Orders for the past 3 months are displayed correctly");
    }

    @Test(priority = 3)
    public void testViewOrdersForThisYear() {
        yourOrders.selectTimeRange("This year");
        Assert.assertTrue(yourOrders.isOrdersTableDisplayed(), "YO_TC3: Fail - Orders for this year are not displayed");
        System.out.println("YO_TC3: Pass - Orders for this year are displayed correctly");
    }

    @Test(priority = 4)
    public void testViewArchivedOrders() {
        yourOrders.selectTimeRange("Archived orders");
        Assert.assertTrue(yourOrders.isOrdersTableDisplayed(), "YO_TC4: Fail - Archived orders are not displayed");
        System.out.println("YO_TC4: Pass - Archived orders are displayed correctly");
    }

    @Test(priority = 5)
    public void testBuyAgainOptionPresence() {
        Assert.assertTrue(yourOrders.isRecommendationsWindowDisplayed(), "YO_TC5: Fail - 'Buy Again' option is not present");
        System.out.println("YO_TC5: Pass - 'Buy Again' option is present");
    }

    @Test(priority = 6)
    public void testBuyAgainFunctionality() {
        yourOrders.clickBuyAgain();
        Assert.assertTrue(yourOrders.isRecommendationsWindowDisplayed(), "YO_TC6: Fail - Recommendations window did not open");
        System.out.println("YO_TC6: Pass - Recommendations window opened successfully");
    }

    @Test(priority = 7)
    public void testBuyAgainRecommendations() {
        yourOrders.clickBuyAgain();
        Assert.assertTrue(yourOrders.areRecommendationsDisplayed(), "YO_TC7: Fail - Recommendations are not displayed based on purchase history");
        System.out.println("YO_TC7: Pass - Recommendations are shown based on purchase history");
    }

    @Test(priority = 8)
    public void testNotYetShippedOptionPresence() {
        Assert.assertTrue(yourOrders.isPendingOrdersTableDisplayed(), "YO_TC8: Fail - 'Not yet shipped' option is not present");
        System.out.println("YO_TC8: Pass - 'Not yet shipped' option is present");
    }

    @Test(priority = 9)
    public void testNotYetShippedFunctionality() {
        yourOrders.clickNotYetShipped();
        Assert.assertTrue(yourOrders.isPendingOrdersTableDisplayed(), "YO_TC9: Fail - Pending orders are not displayed");
        System.out.println("YO_TC9: Pass - Pending orders are displayed correctly");
    }

    @Test(priority = 10)
    public void testCancelledOrdersOptionPresence() {
        Assert.assertTrue(yourOrders.isCancelledOrdersTableDisplayed(), "YO_TC10: Fail - 'Cancelled order' option is not present");
        System.out.println("YO_TC10: Pass - 'Cancelled order' option is present");
    }

    @Test(priority = 11)
    public void testCancelledOrdersFunctionality() {
        yourOrders.clickCancelledOrders();
        Assert.assertTrue(yourOrders.isCancelledOrdersTableDisplayed(), "YO_TC11: Fail - Cancelled orders are not displayed");
        System.out.println("YO_TC11: Pass - Cancelled orders from the last 6 months are displayed correctly");
    }

    @AfterMethod
    public void closeBrowser() {
    	driver.quit();
    }
}
