package amazon.pages;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    WebDriver driver;
    WebDriverWait wait;

    By proceedToCheckoutButton = By.name("proceedToRetailCheckout");
    @FindBy(id = "cart-item-name") List<WebElement> cartItemNames;
    @FindBy(id = "cart-item-price") List<WebElement> cartItemPrices;
    @FindBy(id = "quantity") WebElement quantity;
    @FindBy(id = "total-price") WebElement totalPrice;
    @FindBy(id = "remove-item") WebElement removeItemButton;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void proceedToCheckout() {
        WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutButton));
        checkoutButton.click();
    }

    public List<String> getCartItemNames() {
        return cartItemNames.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> getCartItemPrices() {
        return cartItemPrices.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void setItemQuantity(int qty) {
        quantity.clear();
        quantity.sendKeys(String.valueOf(qty));
    }

    public String getTotalPrice() {
        return totalPrice.getText();
    }

    public void removeItem() {
        removeItemButton.click();
    }

    public boolean isCartEmpty() {
        return cartItemNames.size() == 0;
    }
    public void applyCoupon(String couponCode) {
        WebElement couponField = driver.findElement(By.id("coupon-code"));
        WebElement applyButton = driver.findElement(By.id("apply-coupon"));
        couponField.clear();
        couponField.sendKeys(couponCode);
        applyButton.click();
    }

    public String getCouponErrorMessage() {
        return driver.findElement(By.id("coupon-error")).getText();
    }

    public void clearCart() {
        // Logic to clear all items from the cart
        List<WebElement> removeButtons = driver.findElements(By.className("remove-item"));
        for (WebElement removeButton : removeButtons) {
            removeButton.click();
        }
    }

    public String getCartIconCount() {
        return driver.findElement(By.id("cart-icon-count")).getText();
    }

    public String getEmptyCartMessage() {
        return driver.findElement(By.id("empty-cart-message")).getText();
    }
}
