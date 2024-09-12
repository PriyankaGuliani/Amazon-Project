package amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddressPage {
    WebDriver driver;

    // Elements related to Address Module
    @FindBy(id = "addAddressButton") WebElement addAddressBtn;
    @FindBy(id = "country") WebElement countryDropdown;
    @FindBy(id = "fullName") WebElement fullNameField;
    @FindBy(id = "phoneNumber") WebElement phoneNumberField;
    @FindBy(id = "addressField") WebElement addressField;
    @FindBy(id = "cityField") WebElement cityField;
    @FindBy(id = "provinceDropdown") WebElement provinceDropdown;
    @FindBy(id = "postalCodeField") WebElement postalCodeField;
    @FindBy(id = "defaultAddressCheckbox") WebElement defaultAddressCheckbox;
    @FindBy(id = "additionalInstructions") WebElement additionalInstructionsField;
    @FindBy(id = "saveAddressBtn") WebElement saveAddressBtn;

    public AddressPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickAddAddress() {
        addAddressBtn.click();
    }

    public void enterFullName(String fullName) {
        fullNameField.sendKeys(fullName);
    }

    public void enterPhoneNumber(String phoneNumber) {
        phoneNumberField.sendKeys(phoneNumber);
    }

    public void enterAddress(String address) {
        addressField.sendKeys(address);
    }

    public void enterCity(String city) {
        cityField.sendKeys(city);
    }

    public void selectProvince(String province) {
        provinceDropdown.sendKeys(province);
    }

    public void enterPostalCode(String postalCode) {
        postalCodeField.sendKeys(postalCode);
    }

    public void setAsDefaultAddress() {
        defaultAddressCheckbox.click();
    }

    public void addAdditionalInstructions(String instructions) {
        additionalInstructionsField.sendKeys(instructions);
    }

    public void saveAddress() {
        saveAddressBtn.click();
    }

    public boolean isCountryAutoSelected(String expectedCountry) {
        return countryDropdown.getText().equals(expectedCountry);
    }
 
    public String getSelectedCountry() {
        WebElement countryDropdown = driver.findElement(By.id("country"));
        return new Select(countryDropdown).getFirstSelectedOption().getText();
    }

    public String getAddressFieldValue() {
        return driver.findElement(By.id("addressField")).getAttribute("value");
    }

    public String getCityValue() {
        return driver.findElement(By.id("cityField")).getAttribute("value");
    }

    public String getProvinceValue() {
        return driver.findElement(By.id("provinceDropdown")).getText();
    }

    public String getPostalCodeValue() {
        return driver.findElement(By.id("postalCodeField")).getAttribute("value");
    }

    public boolean isDefaultAddressChecked() {
        return driver.findElement(By.id("defaultAddressCheckbox")).isSelected();
    }

    public String getAdditionalInstructionsValue() {
        return driver.findElement(By.id("additionalInstructions")).getAttribute("value");
    }

    public boolean isAddressSaved() {
        // Logic to verify that the address is saved (could be a confirmation message or a check in the address list)
        return driver.findElement(By.id("addressSavedConfirmation")).isDisplayed();
    }

}
