package seleniumwithcompose;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSeleniumCompose extends HooksCompose {

    @Test
    public void powinienZalogowacSiePrzezCompose() {
        var driver = MyTestContextCompose.getDriver();

        // 1. Wchodzimy na Adminera używając nazwy serwisu z Compose
        driver.get("http://panel-adminer:8080");

        // 2. Prosta weryfikacja
        String tytul = driver.getTitle();
        System.out.println("Zalogowano do: " + tytul);
        assertTrue(tytul.contains("Adminer"));

        // 3. Wpisujemy dane (robot już wie co robić)
        driver.findElement(By.name("auth[username]")).sendKeys("joanna");
        driver.findElement(By.name("auth[password]")).sendKeys("tajneHaslo123");

        System.out.println("Test w Docker Compose zakończony sukcesem!");
    }
}