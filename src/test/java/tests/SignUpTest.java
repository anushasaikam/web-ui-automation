package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.SignUpPage; 

public class SignUpTest {
    
    WebDriver driver;
    SignUpPage signUpPage;

    String url = "https://www.automationexercise.com/";
    String username = "User20252";
    String email = "User20252@gmail.com";

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        signUpPage = new SignUpPage(driver); 
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void handleConsent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[p[text()='Consent']]")));
            driver.findElement(By.xpath("//button[p[text()='Consent']]")).click();
            System.out.println("Consent button clicked.");
        } catch (Exception e) {
            System.out.println("Consent button not found or already handled. Exception: " + e.getMessage());
        }
    }

    public void completeSignUpFlow(String password, String day, String month, String year,
                                       String firstName, String lastName, String company, String address1, String address2,
                                       String country, String state, String city, String zipCode, String mobileNumber) {
        driver.get(url);
        handleConsent();

        signUpPage.clickSignUpNavLink();
        signUpPage.enterNameAndEmail(username, email);
        signUpPage.clicksignupNameEmailButton();

        Assert.assertEquals(driver.getCurrentUrl(),   "https://www.automationexercise.com/signup",
            "Did not navigate to the signup page!");
        
        //signUpPage.waitForAccountInfo();
        signUpPage.fillAccountInformation(
            password, day, month, year,
            firstName, lastName, company, address1, address2,
            country, state, city, zipCode, mobileNumber
        );
         System.out.println("Sign-up flow completed successfully.");                               

    }

    @DataProvider(name = "signUpData")
    public Object[][] signUpData() {
        return new Object[][] {
            {
                "Password123", "01", "Jan", "2000",
                "John", "Doe", "Company", "Address1", "Address2", "Country", "State", "City", "12345", "0076543210"
            }
        };
    }

    @Test(dataProvider = "signUpData")
    public void testCompleteSignUpFlow(String password, String day, String month, String year,
                                              String firstName, String lastName, String company, String address1, String address2,
                                              String country, String state, String city, String zipCode, String mobileNumber) {
        completeSignUpFlow(password, day, month, year,
                                   firstName, lastName, company, address1, address2,
                                   country, state, city, zipCode, mobileNumber);
     
    
       
    }
}


