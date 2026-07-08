package com.selenium.pom.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class BasePage {

    private WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver WebDriverConnection(){
        try {
            driver = new RemoteWebDriver(
                    new URI("http://192.168.26.2:4444").toURL(),
                    new ChromeOptions()
            );
        } catch (Exception e) {
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
                            new URI("http://localhost:4444").toURL(),
                            new FirefoxOptions());
                    break;
                case "edge":
                    driver = new RemoteWebDriver(
                            new URI("http://localhost:4444").toURL(),
                            new EdgeOptions());
                    break;
                default:
                    driver = new RemoteWebDriver(
                            new URI("http://localhost:4444").toURL(),
                            new ChromeOptions());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al conectar con Selenium Grid", e);
        }
        return driver;
    }
    // Configurar la coneccion a una plataforma en la nube usando LambdaTest
    public WebDriver driverConnectionCloud() {
        // 1. Obtener credenciales copiadas anteriormente
        String username = "rosariomrsm11";
        String accessKey = "oQtPSveippR6PX24EjBoIRNC8CqhfvXu1kPEKgk3Ld1dvHMRaa";

        // 2. Configurar las capacidades (browser, versión, SO)
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10"); // Especifica el SO
        browserOptions.setBrowserVersion("latest"); // O una versión concreta

        // 3. Añadir opciones específicas de LambdaTest
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("build", "Mi Primer Build en la Nube"); // Identificador de la ejecución
        ltOptions.put("name", "Prueba de Login con DDT"); // Nombre de la prueba
        ltOptions.put("w3c", true);
        browserOptions.setCapability("LT:Options", ltOptions);

        // 4. Crear el driver remoto apuntando a LambdaTest
        // La URL usa las credenciales para la autenticación
        String hubURL = "https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub";

        try {
            driver = new RemoteWebDriver(new URL(hubURL), browserOptions);
        } catch (MalformedURLException e) {
            System.out.println("URL del grid inválida: " + e.getMessage());
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
