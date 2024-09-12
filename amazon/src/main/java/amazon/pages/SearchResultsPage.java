package amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage {
    WebDriver driver;
    WebDriverWait wait;
    
    private By productList = By.cssSelector(".s-main-slot .s-result-item");
    private By productImage = By.cssSelector(".s-image");
    private By productName = By.cssSelector("span.a-text-normal");
    private By productPrice = By.cssSelector(".a-price-whole");
    private By nextPageButton = By.cssSelector("a.s-pagination-next");
    private By filterOption = By.xpath("//span[text()='Brand']//following::li[1]//a");
    private By sortDropdown = By.id("s-result-sort-select");

    By firstProduct = By.cssSelector("div.s-main-slot div[data-component-type='s-search-result'] h2 a");

    public SearchResultsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void clickFirstProduct() {
        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(firstProduct));
        product.click();
    }
    public boolean isProductListDisplayed() {
        return driver.findElements(productList).size() > 0;
    }

    public boolean areProductDetailsDisplayed() {
        List<WebElement> products = driver.findElements(productList);
        for (WebElement product : products) {
            if (!product.findElement(productImage).isDisplayed() ||
                !product.findElement(productName).isDisplayed() ||
                !product.findElement(productPrice).isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    public int getProductCountOnPage() {
        return driver.findElements(productList).size();
    }

    public void clickNextPage() {
        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(nextPageButton));
        nextButton.click();
    }

    public boolean isNextPageLoaded() {
        return driver.findElements(productList).size() > 0;
    }

    public List<String> getProductNamesOnPage() {
        List<String> productNames = new ArrayList<>();
        List<WebElement> products = driver.findElements(productName);
        for (WebElement product : products) {
            productNames.add(product.getText());
        }
        return productNames;
    }

    public boolean areItemsUnique(List<String> previousPageItems, List<String> nextPageItems) {
        for (String item : nextPageItems) {
            if (previousPageItems.contains(item)) {
                return false;
            }
        }
        return true;
    }

    public void applyFilter(String filterType, String filterValue) {
        WebElement filterElement = wait.until(ExpectedConditions.elementToBeClickable(filterOption));
        filterElement.click();
    }

    public boolean isFilteredResultsDisplayed(String expectedValue) {
        List<WebElement> products = driver.findElements(productName);
        for (WebElement product : products) {
            if (!product.getText().contains(expectedValue)) {
                return false;
            }
        }
        return true;
    }

    public void sortBy(String sortOption) {
        WebElement sortElement = driver.findElement(sortDropdown);
        sortElement.click();
        driver.findElement(By.xpath("//option[text()='" + sortOption + "']")).click();
    }

    public boolean isSortedByPriceLowToHigh() {
        List<WebElement> prices = driver.findElements(productPrice);
        int previousPrice = 0;
        for (WebElement priceElement : prices) {
            int currentPrice = Integer.parseInt(priceElement.getText().replace(",", ""));
            if (currentPrice < previousPrice) {
                return false;
            }
            previousPrice = currentPrice;
        }
        return true;
    }

    public boolean isFilteredAndSorted() {
        return isFilteredResultsDisplayed("Apple") && isSortedByPriceLowToHigh();
    }
}
