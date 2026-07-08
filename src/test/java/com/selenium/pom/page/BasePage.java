package com.selenium.pom.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class BasePage {

    private WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver WebDriverConnection(){
        try {
            driver = new RemoteWebDriver(
                    new URL("http://192.168.26.2:4444"),
                    new ChromeOptions()
            );
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return driver;
    }

    // CONFIGURACION CON MULTIPLES NAVEGADORES
    public WebDriver driverConnection(String browser) {
        try {
            switch (browser.toLowerCase()) {
                case "firefox":
                    driver = new RemoteWebDriver(
                            new URL("http://localhost:4444"),
                            new FirefoxOptions());
                    break;
                case "edge":
                    driver = new RemoteWebDriver(
                            new URL("http://localhost:4444"),
                            new EdgeOptions());
                    break;
                default:
                    driver = new RemoteWebDriver(
                            new URL("http://localhost:4444"),
                            new ChromeOptions());
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return driver;
    }


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
