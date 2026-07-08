package com.selenium.pom.test;

import com.selenium.pom.model.LoginData;
import com.selenium.pom.page.LoginPage;
import com.selenium.pom.utils.JsonReader;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginJsonTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(String browser){
        loginPage = new LoginPage(driver);
        //driver = loginPage.WebDriverConnection();
        driver = loginPage.driverConnection(browser);
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
    public void close(){
        if(driver != null){
            driver.quit();
        }
    }
}
