package amazon.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YourOrders {
    WebDriver driver;
    WebDriverWait wait;

    By orderHistoryHeader = By.id("orderHistoryHeader");
    By timeRangeDropdown = By.id("timeRangeDropdown");
    By ordersTable = By.id("ordersTable");
    By buyAgainOption = By.linkText("Buy Again");
    By recommendationsWindow = By.id("recommendationsWindow");
    By recommendationsList = By.id("recommendationsList");
    By notYetShippedOption = By.linkText("Not yet shipped");
    By pendingOrdersTable = By.id("pendingOrdersTable");
    By cancelledOrderOption = By.linkText("Cancelled order");
    By cancelledOrdersTable = By.id("cancelledOrdersTable");

    public YourOrders(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public boolean isOrderHistoryHeaderDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderHistoryHeader)).isDisplayed();
    }

    public void selectTimeRange(String timeRange) {
        driver.findElement(timeRangeDropdown).click();
        By timeRangeOption = By.xpath("//option[text()='" + timeRange + "']");
        driver.findElement(timeRangeOption).click();
    }

    public boolean isOrdersTableDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(ordersTable)).isDisplayed();
    }

    public void clickBuyAgain() {
        driver.findElement(buyAgainOption).click();
    }

    public boolean isRecommendationsWindowDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(recommendationsWindow)).isDisplayed();
    }

    public boolean areRecommendationsDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(recommendationsList)).isDisplayed();
    }

    public void clickNotYetShipped() {
        driver.findElement(notYetShippedOption).click();
    }

    public boolean isPendingOrdersTableDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pendingOrdersTable)).isDisplayed();
    }

    public void clickCancelledOrders() {
        driver.findElement(cancelledOrderOption).click();
    }

    public boolean isCancelledOrdersTableDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cancelledOrdersTable)).isDisplayed();
    }
}

