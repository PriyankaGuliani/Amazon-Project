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

public class GuestAccountTest {
	WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/priyankaguliani/eclipse-workspace/amazon/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.ca"); 
    }

    @Test(priority = 1)
    public void testGuestUserNavigation() {
        String pageSource = driver.getPageSource();
        Assert.assertFalse(pageSource.contains("Sign In"), "GA_TC1: Fail - Sign-in prompt appeared");
        System.out.println("GA_TC1: Pass - Guest user can browse without sign-in prompt");
    }

    @Test(priority = 2)
    public void testGuestUserViewProductDetails() {
        // Test Case 2: View product details as a guest user
        driver.findElement(By.linkText("Electronics")).click(); 
        driver.findElement(By.linkText("Fashion")).click();  

        String pageSource = driver.getPageSource();
        Assert.assertFalse(pageSource.contains("Sign In"), "GA_TC2: Fail - Sign-in prompt appeared");
        System.out.println("GA_TC2: Pass - Guest user can view product details without sign-in prompt");
    }

    @Test(priority = 3)
    public void testGuestUserAddToCart() {
        // Test Case 3: Add product to cart as a guest user
    	WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("laptop"); 
        searchBox.submit();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement firstProduct = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("div.s-main-slot div[data-component-type='s-search-result'] h2 a"))
            );

        firstProduct.click();
            
        WebElement addToCartButton = wait.until( ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button")));
        addToCartButton.click();
                
        WebElement cartConfirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("huc-v2-order-row-confirm-text")));
                
        Assert.assertTrue(cartConfirmation.getText().contains("Added to Cart"), "GA_TC3: Fail - Unable to add product to cart");
        System.out.println("GA_TC3: Pass - Guest user can add product to cart");
      
    }
    
    @Test(priority = 4)
    public void testGuestUserProceedToCheckout() {
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("laptop"); 
        searchBox.submit();
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.s-main-slot div[data-component-type='s-search-result'] h2 a")));
        firstProduct.click();
        
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button")));
        addToCartButton.click();
        
        WebElement proceedToCheckoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.name("proceedToRetailCheckout")));
        proceedToCheckoutButton.click();
 
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains("Create an account or Sign In"), "GA_TC4: Fail - Guest user was not prompted to sign in or create an account");
        System.out.println("GA_TC4: Pass - Guest user prompted to sign in or create an account");
    }

    @Test(priority = 5)
    public void testGuestUserCompletePurchase() {
        // Repeat the steps to add a product to the cart
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("laptop");
        searchBox.submit();
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.s-main-slot div[data-component-type='s-search-result'] h2 a")));
        firstProduct.click();
        
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button")));
        addToCartButton.click();
        
        WebElement proceedToCheckoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.name("proceedToRetailCheckout")));
        proceedToCheckoutButton.click();
        
        WebElement shippingAddress = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("address"))); // Update ID
        shippingAddress.sendKeys("123 John Doe Road");
        
        WebElement paymentInfo = driver.findElement(By.id("payment-info")); // Update ID
        paymentInfo.sendKeys("51234567890123461"); 
        
        WebElement placeOrderButton = driver.findElement(By.id("place-order-button")); // Update ID
        placeOrderButton.click();
        
        WebElement orderConfirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("order-confirmation"))); // Update ID
        
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains("Thank you for your purchase"), "GA_TC5: Fail - Unable to complete purchase as guest user");
        System.out.println("GA_TC5: Pass - Guest user can complete purchase");
    }



    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
