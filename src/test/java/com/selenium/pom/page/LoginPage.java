package com.selenium.pom.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

     By userName = By.id("user-name");
     By password = By.id("password");
     By loginBtn = By.id("login-button");
     //By errorMessage = By.cssSelector("#login_button_container h3");

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public void loginUser(String userNameText, String passwordText){
        type(userNameText, userName);
        type(passwordText, password);
        click(loginBtn);
    }

}
