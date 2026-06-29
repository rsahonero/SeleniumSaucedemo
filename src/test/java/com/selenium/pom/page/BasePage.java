package com.selenium.pom.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class BasePage {

    public static WebDriver driver;

    public void setDriver(WebDriver driver){
        BasePage.driver = driver;
    }

//    public BasePage(){
//        WebDriver driver = new ChromeDriver();
//    }
//
//    public WebDriver chromeDriverConnection(){
//        driver = new ChromeDriver();
//        return driver;
//    }
    public WebElement find(By locator){
        return driver.findElement(locator);
    }

    public List<WebElement> findElements(By locator){
        return driver.findElements(locator);
    }

    public void type(String text, By locator){
        find(locator).clear();
        find(locator).sendKeys(text);
    }

    public String getText(WebElement element){
        return element.getText();
    }

    public void click(By locator){
        find(locator).click();
    }

    public void visit(String url){
        driver.get(url);
    }

}
