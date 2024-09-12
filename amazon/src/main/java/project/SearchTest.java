package project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import amazon.pages.HomePage;
import amazon.pages.SearchResultsPage;

import java.time.Duration;
import java.util.List;

public class SearchTest {
    WebDriver driver;
    WebDriverWait wait;
    HomePage homePage;
    SearchResultsPage searchResultsPage;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get("https://www.amazon.com/");
        homePage = new HomePage(driver);
        searchResultsPage = new SearchResultsPage(driver, wait);
    }

    @Test
    public void testSearchProduct() {
        homePage.searchForProduct("laptop");
        Assert.assertTrue(searchResultsPage.isProductListDisplayed(), "Product list is not displayed.");
    }

    @Test
    public void testSRPDisplaysRelevantItems() {
        homePage.searchForProduct("laptop");
        Assert.assertTrue(searchResultsPage.areProductDetailsDisplayed(), "Relevant items are not displayed.");
    }

    @Test
    public void testProductsPerPageIsSixty() {
        homePage.searchForProduct("laptop");
        Assert.assertEquals(searchResultsPage.getProductCountOnPage(), 60, "Product count is not 60.");
    }

    @Test
    public void testNextPageNavigation() {
        homePage.searchForProduct("laptop");
        searchResultsPage.clickNextPage();
        Assert.assertTrue(searchResultsPage.isNextPageLoaded(), "Next page did not load.");
    }

    @Test
    public void testNoDuplicatesOnNextPage() {
        homePage.searchForProduct("laptop");
        List<String> previousPageItems = searchResultsPage.getProductNamesOnPage();
        searchResultsPage.clickNextPage();
        List<String> nextPageItems = searchResultsPage.getProductNamesOnPage();
        Assert.assertTrue(searchResultsPage.areItemsUnique(previousPageItems, nextPageItems), "There are duplicate items on the next page.");
    }

    @Test
    public void testFilterProducts() {
        homePage.searchForProduct("laptop");
        searchResultsPage.applyFilter("brand", "Apple");
        Assert.assertTrue(searchResultsPage.isFilteredResultsDisplayed("Apple"), "Filtered results are not displayed.");
    }

    @Test
    public void testSortProducts() {
        homePage.searchForProduct("laptop");
        searchResultsPage.sortBy("Price: Low to High");
        Assert.assertTrue(searchResultsPage.isSortedByPriceLowToHigh(), "Products are not sorted by price.");
    }

    @Test
    public void testFilterAndSortProducts() {
        homePage.searchForProduct("laptop");
        searchResultsPage.applyFilter("brand", "Apple");
        searchResultsPage.sortBy("Price: Low to High");
        Assert.assertTrue(searchResultsPage.isFilteredAndSorted(), "Filtered and sorted results are not correct.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
