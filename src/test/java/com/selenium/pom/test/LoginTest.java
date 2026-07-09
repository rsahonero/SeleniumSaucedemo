package com.selenium.pom.test;

import com.selenium.pom.page.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser){
        loginPage = new LoginPage(driver);
        //driver = loginPage.WebDriverConnection();
        driver = loginPage.driverConnection(browser);
        //driver = loginPage.driverConnectionCloud();
        loginPage.visit("https://www.saucedemo.com/");
    }
    @Test
    public void LoginSuccessful(){
        loginPage.loginUser("standard_user","secret_sauce" );
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");

    }
    @AfterMethod
    public void close(){
        if(driver != null){
            driver.quit();
        }
    }
}
