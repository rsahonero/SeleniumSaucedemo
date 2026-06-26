package com.selenium.pom.test;

import com.selenium.pom.page.BasePage;
import com.selenium.pom.page.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private BasePage basePage;


    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
        basePage = new BasePage();
        basePage.setDriver(driver);
        loginPage = new LoginPage(driver);
        loginPage.visit("https://www.saucedemo.com/");
        driver.manage().window().maximize();

    }
    @Test
    public void LoginSuccessful(){
        loginPage.loginUser("standard_user","secret_sauce" );
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");

    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
