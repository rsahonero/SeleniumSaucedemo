package com.selenium.pom.test;

import com.selenium.pom.model.LoginData;
import com.selenium.pom.page.BasePage;
import com.selenium.pom.page.LoginPage;
import com.selenium.pom.utils.ExcelReader;
import com.selenium.pom.utils.JsonReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginJsonTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private BasePage basePage;


    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        basePage = new BasePage();
        basePage.setDriver(driver);
        loginPage = new LoginPage(driver);
        loginPage.visit("https://www.saucedemo.com/");
        driver.manage().window().maximize();
    }

    @DataProvider(name = "loginData")
    public Object[] getLoginData() {
        return JsonReader.readUsersSafe("files/Usuarios.json").toArray();
    }

    @Test(dataProvider = "loginData")
    public void LoginSuccessfulJson(LoginData user){
        loginPage.loginUser(user.getUsername(), user.getPassword());
        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://www.saucedemo.com/inventory.html",
                "El login a fallado para el usuario: " + user.getUsername());

    }
    @AfterMethod
    public void tearDown(){
        if(driver != null) {
            driver.quit();
        }

    }
}
