package seleniumwithhooks;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSelenium extends Hooks {

    @Test
    public void powinienOtworzycAdmineraWDockerze() throws Exception {
        // 1. Pobieramy drivera z naszego schowka
        var driver = MyTestContext.getDriver();

        // 2. Wchodzimy na Adminera
        System.out.println("Otwieram Adminera...");
        driver.get("http://moj-adminer:8080");

        // 3. Sprawdzamy tytuł strony
        String tytul = driver.getTitle();
        System.out.println("Tytuł strony: " + tytul);
        assertTrue(tytul.contains("Adminer"));

        // 4. Znajdujemy pola i wpisujemy dane
        WebElement loginField = driver.findElement(By.name("auth[username]"));
        loginField.sendKeys("joanna");

        WebElement passwordField = driver.findElement(By.name("auth[password]"));
        passwordField.sendKeys("tajneHaslo123");

        // 5. Weryfikujemy, czy tekst został wpisany
        String wpisanyLogin = loginField.getAttribute("value");
        System.out.println("Wpisano login: " + wpisanyLogin);
        assertTrue(wpisanyLogin.equals("joanna"));

        // 6. ROBIMY ZDJĘCIE (Screenshot)
        System.out.println("Robienie zrzutu ekranu...");
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Kopiujemy plik z pamięci tymczasowej do folderu projektu
        Files.copy(screenshot.toPath(), Path.of("adminer_login.png"), java.nio.file.StandardCopyOption.REPLACE_EXISTING);

        System.out.println("Sukces! Zobacz plik adminer_login.png w głównym folderze projektu.");
    }
}