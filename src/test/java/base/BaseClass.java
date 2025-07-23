package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import listeners.ExtentTestListener;
import pages.SignUpPage;

@Listeners(ExtentTestListener.class)
public class BaseClass {
    
    protected WebDriver driver;
    protected SignUpPage signUpPage;

    
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
}