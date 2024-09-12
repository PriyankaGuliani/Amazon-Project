package amazon.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;
    
    By electronicsLink = By.linkText("Electronics");
    By fashionLink = By.linkText("Fashion");
    By searchBox = By.id("twotabsearchtextbox"); 
    By signInPrompt = By.id("signInPromptId"); 
    
    By accountListLink = By.id("nav-link-accountList-nav-line-1");
    By accountMessage = By.id("nav-link-accountList");
    By signInButton = By.id("nav-link-accountList-nav-line-1");
    By emailField = By.id("ap_email");
    By continueButton = By.id("continue");
    By passwordField = By.id("ap_password");
    By loginButton = By.id("signInSubmit");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickElectronics() {
        driver.findElement(electronicsLink).click();
    }

    public void clickFashion() {
        driver.findElement(fashionLink).click();
    }

    public void searchForProduct(String productName) {
        driver.findElement(searchBox).sendKeys(productName);
        driver.findElement(searchBox).submit();
    }

    public boolean isSignInPromptPresent() {
        return driver.findElements(signInPrompt).size() > 0;
    }
    public void clickSignIn() {
        driver.findElement(accountListLink).click();
    }

    public String getAccountMessage() {
        return driver.findElement(accountMessage).getText();
    }

    public void hoverOverAccountMessage(Actions actions) {
        actions.moveToElement(driver.findElement(accountMessage)).perform();
    }
    public void navigateToHomePage() {
        driver.get("https://www.amazon.ca");
    }

    public void login(String email, String password) {
        driver.findElement(signInButton).click();
        WebElement emailElement = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        emailElement.sendKeys(email);
        driver.findElement(continueButton).click();
        
        WebElement passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passwordElement.sendKeys(password);
        driver.findElement(loginButton).click();
    }
    public void logout() {
        WebElement accountList = driver.findElement(By.xpath("//a[@id='nav-link-accountList']"));
        accountList.click();
        
        WebElement signOutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Sign Out']")));
        signOutButton.click();
    }

    public void CreateAccount(Actions actions) {
        actions.moveToElement(driver.findElement(accountMessage)).perform();

        WebElement signInLink = driver.findElement(By.xpath("//span[text()='Sign in']"));
        signInLink.click();
        
        WebElement createAccountLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='createAccountSubmit']")));
        createAccountLink.click(); 
    }

}
