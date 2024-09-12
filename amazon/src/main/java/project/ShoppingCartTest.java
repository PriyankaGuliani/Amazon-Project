package project;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import amazon.pages.CartPage;
import amazon.pages.HomePage;
import amazon.pages.LoginSecurityPage;
import amazon.pages.ProductPage;
import amazon.pages.YourAccountPage;

public class ShoppingCartTest {

    WebDriver driver;
    WebDriverWait wait;
    HomePage homePage;
    YourAccountPage accountPage;
    LoginSecurityPage loginSecurityPage;
    ProductPage productPage = new ProductPage(driver);
    CartPage cartPage = new CartPage(driver);

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/priyankaguliani/eclipse-workspace/amazon/chromedriver");
        
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        homePage = new HomePage(driver);
        accountPage = new YourAccountPage(driver);
        loginSecurityPage = new LoginSecurityPage(driver, wait);

        homePage.navigateToHomePage();
        homePage.login("amazonproject@myyahoo.com", "RoiciansTest");
        accountPage.goToLoginSecurity();
    }
    
    @Test
    public void testAddItemToCart() {
        productPage.addItemToCart();

        CartPage cartPage = new CartPage(driver);
        List<String> cartItemNames = cartPage.getCartItemNames();
        List<String> cartItemPrices = cartPage.getCartItemPrices();

        Assert.assertTrue(cartItemNames.contains(productPage.getProductName()));
        Assert.assertTrue(cartItemPrices.contains(productPage.getProductPrice()));
    }
    @Test
    public void testIncreaseItemQuantity() {
        productPage.addItemToCart();

        cartPage.setItemQuantity(3);

        String expectedTotalPrice = "calculated_price"; 
        Assert.assertEquals(cartPage.getTotalPrice(), expectedTotalPrice);
    }
    @Test
    public void testMultipleItemsInCart() {
        productPage.addItemToCart();
        productPage.addItemToCart();

        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.getCartItemNames().size(), 2);
    }
    @Test
    public void testRemoveItemsFromCart() {
        productPage.addItemToCart();
        productPage.addItemToCart();

        Assert.assertEquals(cartPage.getCartItemNames().size(), 2, "There should be 2 items in the cart.");

        cartPage.removeItem();

        Assert.assertFalse(cartPage.isCartEmpty(), "Cart should not be empty after removing one item.");

        cartPage.clearCart(); 

        Assert.assertTrue(cartPage.isCartEmpty(), "Cart should be empty after clearing all items.");
    }

    @Test
    public void testApplyCoupon() {
        productPage.addItemToCart();

        cartPage.applyCoupon("VALID_COUPON_CODE");

        String expectedDiscountedPrice = "calculated_discount_price"; 
        Assert.assertEquals(cartPage.getTotalPrice(), expectedDiscountedPrice);
    }
    @Test
    public void testCartPersistenceAcrossSessions() {
        productPage.addItemToCart();
        
        homePage.logout();
        
        homePage.login("priyankaguliani18@gmail.com", "Guliani123");
        List<String> cartItems = cartPage.getCartItemNames();
        
        Assert.assertTrue(cartItems.contains(productPage.getProductName()));
    }
    @Test
    public void testTotalPriceWithMultipleQuantities() {
        productPage.addItemToCart();

        cartPage.setItemQuantity(3);

        String unitPrice = productPage.getProductPrice();
        String expectedTotalPrice = String.valueOf(Double.parseDouble(unitPrice) * 3);

        Assert.assertEquals(cartPage.getTotalPrice(), expectedTotalPrice);
    }
    @Test
    public void testEmptyCartMessage() {
   
        cartPage.removeItem();

        Assert.assertTrue(cartPage.isCartEmpty());

        String emptyCartMessage = cartPage.getEmptyCartMessage();
        Assert.assertEquals(emptyCartMessage, "Your cart is currently empty.");
    }
    @Test
    public void testInvalidCoupon() {
        productPage.addItemToCart();


        cartPage.applyCoupon("INVALID_COUPON");

        String couponErrorMessage = cartPage.getCouponErrorMessage();
        Assert.assertEquals(couponErrorMessage, "Invalid coupon code.");
    }

    @Test
    public void testQuantityAdjustmentInCart() {
        productPage.addItemToCart();

        cartPage.setItemQuantity(2);

        String unitPrice = productPage.getProductPrice();
        String expectedTotalPrice = String.valueOf(Double.parseDouble(unitPrice) * 2);
        Assert.assertEquals(cartPage.getTotalPrice(), expectedTotalPrice);
    }
    @Test
    public void testCartIconCounter() {
        productPage.addItemToCart();

        String cartIconCount = cartPage.getCartIconCount();
        
        Assert.assertEquals(cartIconCount, "1");
    }
    @Test
    public void testClearAllItemsFromCart() {
        productPage.addItemToCart();
        productPage.addItemToCart();

        cartPage.clearCart();

        Assert.assertTrue(cartPage.isCartEmpty());
    }
    @Test
    public void testCartPersistenceAfterRefresh() {
        productPage.addItemToCart();

        driver.navigate().refresh();

        List<String> cartItems = cartPage.getCartItemNames();
        Assert.assertTrue(cartItems.contains(productPage.getProductName()));
        }
    
    @AfterMethod
    public void closeBrowser() {
    	driver.quit();
    	}
    }
