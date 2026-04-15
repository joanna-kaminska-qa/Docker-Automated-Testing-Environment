package database;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
public class FullFlowTest {

    @Container
    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine")
            .withDatabaseName("habit_db")
            .withUsername("joanna")
            .withPassword("tajnehaslo")
            .withInitScript("init.sql"); // wrzucamy nasz plik z predefiniowaną tabelą z danymi testowymi, żeby baza danych nie była pusta

    @Test
    void testPelnyProcesDodawaniaNawyku() throws MalformedURLException {
        // 1. Dane bazy z Testcontainers
        String dbUrl = postgres.getJdbcUrl();
        String user = postgres.getUsername();
        String pass = postgres.getPassword();

        // 2. Startujemy Selenium (Robot) z konkretnymi opcjami. Można np. ustawić żeby otwierało się w incognito. Tu nie dajemy nic specjalnego, ale nie da się go zainicjować całkiem bez tego, bo RemoteWebDriver potrzebuje wiedzieć, czy ma otwierać Chrome'a, czy Firefoxa
        ChromeOptions options = new ChromeOptions();
        // Tutaj podajemy adres Twojego serwisu selenium-chrome z Docker Compose
        RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options); //  /wd/hub: To jest standardowa "końcówka" adresu w Selenium.
        // uzywam remotewebdriver a nie normlany bo to kontener i on właśnie wymaga jawnego pisania Options

        try {
            // 3. Sprawdzamy bazę na start (powinno być 0)
            int stanPrzed = DatabaseManager.countRecords(dbUrl, user, pass, "habits");
            System.out.println("Rekordów przed: " + stanPrzed);

            // 4. ROBOT KLIKA (Selenium)
            driver.get("http://panel-adminer:8080"); // Robot wchodzi do Adminera

            // --- TUTAJ TWOJE KLIKANIE ---
            // (Zaloguj się do Adminera i dodaj rekord w tabeli 'habits')
            // Na potrzeby przykładu symulujemy, że robot to zrobił, dodając rekord bezpośrednio przez JDBC:
            System.out.println("Robot dodaje rekord przez przeglądarkę (symulacja JDBC)...");
            try (Connection conn = DriverManager.getConnection(dbUrl, user, pass);
                 Statement stmt = conn.createStatement()) {
                stmt.execute("INSERT INTO habits (name, target_per_week) VALUES ('Nawyk od Robota', 5)"); // To jest bezpośredni rozkaz dla bazy: "Wstaw ten rekord teraz!" (zamiast próbować klikać "rękami" Selenium)
            } catch (Exception e) {
                System.out.println("Błąd symulacji: " + e.getMessage());
            }
            // Adminer służy nam głównie do podglądu, dlatego nie bawimy się w jego konfigurowanie, tylko używamy symulacji

            // 5. Sprawdzamy bazę po kliknięciu (powinno być 1)
            int stanPo = DatabaseManager.countRecords(dbUrl, user, pass, "habits");
            System.out.println("Rekordów po: " + stanPo);

            // 6. ASERCJA - Serce testu
            assertEquals(stanPrzed + 1, stanPo, "Baza powinna mieć o jeden rekord więcej po akcji Selenium!");

        } finally {
            driver.quit();
        }
    }
}