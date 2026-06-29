package com.selenium.pom.test;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.selenium.pom.page.BasePage.driver;

public class ShadowDomTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private final By nombreInputText = By.id("txtNombre");
    private final By btnValidarNombre = By.id("btnValidarNombre");
    private final By nombreOutputText = By.id("outputNombre");

    private final By apellidoInputText = By.id("txtApellido");
    private final By btnValidarApellido = By.id("btnValidarApellido");
    private final By apellidoOutputText = By.id("outputApellido");

    private final By shadowHost = By.id("apellido-component");

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://fhmontes123.github.io/qaautomationlabs/");
    }

    @Test
    public void validarIngresoNombre() {
        WebElement nombre = driver.findElement(nombreInputText);
        nombre.sendKeys("Valeria");
        driver.findElement(btnValidarNombre).click();

        WebElement output = driver.findElement(nombreOutputText);
        System.out.println(output.getText());
        Assert.assertEquals(output.getText(), "Su nombre es Valeria");
    }

//    @Test
//    public void validarIngresoApellido() {
//        WebElement apellido = driver.findElement(apellidoInputText);
//        apellido.sendKeys("Perez");
//        driver.findElement(btnValidarApellido).click();
//
//        WebElement output = driver.findElement(apellidoOutputText);
//        System.out.println(output.getText());
//        Assert.assertEquals(output.getText(), "Su apellido es Perez");
//    }

    // SHADOW DOM
    @Test
    public void validarIngresoApellido() {
        WebElement host = driver.findElement(shadowHost);
        SearchContext shadowRoot = host.getShadowRoot();

        WebElement apellido = shadowRoot.findElement(apellidoInputText);
        apellido.sendKeys("Vargas");

        WebElement btnValidar = shadowRoot.findElement(btnValidarApellido);
        btnValidar.click();

        WebElement output = shadowRoot.findElement(apellidoOutputText);
        WebElement outputText = wait.until(ExpectedConditions.visibilityOf(output));
        System.out.println(outputText.getText());
        Assert.assertEquals(outputText.getText(), "Su apellido es Vargas",
                "El mensaje de validación del apellido no coincide");
    }

    @AfterMethod
    public void tearDown() {
        if(driver != null){
            driver.quit();
        }
    }
}