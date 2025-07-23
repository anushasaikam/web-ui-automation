package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage {

    private WebDriver driver;
    private WebDriverWait wait;

    //-NAVIGATION-
    private By signUpNavLink            = By.cssSelector("a[href='/login']");
    private By usernameField            = By.name("name");
    private By signUpEmailField         = By.cssSelector("input[data-qa='signup-email']");
    private By signupNameEmailButton    = By.cssSelector("button[data-qa='signup-button']");

    private By consentButton            = By.xpath("//button[p[text()='Consent']]");

    //ACCOUNT INFORMATION

    private By accountInfoHeader        = By.xpath("//h2[contains(.,'ENTER ACCOUNT INFORMATION')]");
    private By titleRadioButton         = By.cssSelector("input[type = 'radio'][value = 'Mrs']");
    private By passwordField            = By.name("password");
    private By dayDropdown              = By.name("days");
    private By monthDropdown            = By.name("months");
    private By yearDropdown             = By.name("years");
    private By firstNameField           = By.id("first_name");
    private By lastNameField            = By.id("last_name");
    private By companyField             = By.id("company");
    private By address1Field            = By.id("address1");       
    private By address2Field            = By.id("address2");
    private By countryDropdown          = By.id("country");
    private By stateField               = By.id("state");
    private By cityField                = By.id("city");
    private By zipCodeField             = By.id("zipcode");
    private By mobileNumberField        = By.id("mobile_number");
    private By createAccountButton      = By.xpath("//button[@type='submit' and contains(text(), 'Create Account')]");


    //Constructor
    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }

    //Methods to interact with the page elements

    public void clickSignUpNavLink() {
       WebElement nav = wait.until(ExpectedConditions.elementToBeClickable((signUpNavLink)));
                    nav.click();
    }

    public void enterNameAndEmail(String username, String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);
        driver.findElement(signUpEmailField).sendKeys(email);
    }

    public void clicksignupNameEmailButton() {
        wait.until(ExpectedConditions.elementToBeClickable(signupNameEmailButton)).click();
        // Wait for either the expected URL or a unique element on the next page
        wait.until(ExpectedConditions.or(
            ExpectedConditions.urlToBe("https://www.automationexercise.com/signup"),
            ExpectedConditions.visibilityOfElementLocated(accountInfoHeader)
        ));
    }

    public void waitForSignUpUrl() {
        wait.until(ExpectedConditions.urlToBe("https://www.automationexercise.com/signup"));
    }



    /** Fill out all Account Information & Create Account */
    public void fillAccountInformation(
        String password,
        String day, String month, String year,
        String firstName, String lastName,
        String company, String address1, String address2,
        String country, String state, String city,
        String zipCode, String mobileNumber
    ) {
        //waitForAccountInfo();

        driver.findElement(titleRadioButton).click();
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(dayDropdown).sendKeys(day);
        driver.findElement(monthDropdown).sendKeys(month);
        driver.findElement(yearDropdown).sendKeys(year);

        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(companyField).sendKeys(company);
        driver.findElement(address1Field).sendKeys(address1);
        driver.findElement(address2Field).sendKeys(address2);

        driver.findElement(countryDropdown).sendKeys(country);
        driver.findElement(stateField).sendKeys(state);
        driver.findElement(cityField).sendKeys(city);
        driver.findElement(zipCodeField).sendKeys(zipCode);
        driver.findElement(mobileNumberField).sendKeys(mobileNumber);

        clickCreateAccountButton();
    }

    public void clickCreateAccountButton() {
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(createAccountButton));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", button);
        wait.until(ExpectedConditions.elementToBeClickable(createAccountButton)).click();
    }
}







































































    /*public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }
    public void clickSignupButton() {
        driver.findElement(signupNameEmailButton).click();
    }
    public void selectTitle() {
        driver.findElement(titleRadioButton).click();
    }   
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }
    public void selectDay(String day) {
        driver.findElement(dayDropdown).sendKeys(day);
    }
    public void selectMonth(String month) {
        driver.findElement(monthDropdown).sendKeys(month);
    }
    public void selectYear(String year) {
        driver.findElement(yearDropdown).sendKeys(year);
    }
    public void enterFirstName(String firstName) {
        driver.findElement(this.firstName).sendKeys(firstName);
    }
    public void enterLastName(String lastName) {
        driver.findElement(this.lastName).sendKeys(lastName);
    }
    public void enterCompanyName(String company) {
        driver.findElement(companyName).sendKeys(company);
    }
    public void enterAddress1(String address) {
        driver.findElement(address1).sendKeys(address);
    }
    public void enterAddress2(String address) {
        driver.findElement(address2).sendKeys(address);
    }
    public void selectCountry(String country) {
        driver.findElement(countryDropdown).sendKeys(country);
    }
    public void selectState(String state) {
        driver.findElement(stateDropdown).sendKeys(state);
    }
    public void enterCity(String city) {
        driver.findElement(cityField).sendKeys(city);
    }
    public void enterZipCode(String zipCode) {
        driver.findElement(zipCodeField).sendKeys(zipCode);
    }
    public void enterMobileNumber(String mobileNumber) {
        driver.findElement(mobileNumberField).sendKeys(mobileNumber);
    }
    public void clickCreateAccountButton() {
        driver.findElement(createAccountButton).click();
    }

 

    public void fillSignUpForm(String password, String day, String month, String year,
                                String firstName, String lastName, String company, String address1, String address2,
                                String country, String state, String city, String zipCode, String mobileNumber) {

        clickSignupButton();
        selectTitle();
        enterPassword(password);
        selectDay(day);
        selectMonth(month);
        selectYear(year);
        enterFirstName(firstName);
        enterLastName(lastName);
        enterCompanyName(company);
        enterAddress1(address1);
        enterAddress2(address2);
        selectCountry(country);
        selectState(state);
        enterCity(city);
        enterZipCode(zipCode);
        enterMobileNumber(mobileNumber);
        clickCreateAccountButton();
    }

}*/