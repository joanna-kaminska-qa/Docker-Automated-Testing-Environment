package database;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
public class TestDatabaseContainer {

    @Container
    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine")
            .withDatabaseName("testdb")
            .withUsername("joanna")
            .withPassword("tajnehaslo");

    @Test
    void testCzyBazaDziala() {
        assertTrue(postgres.isRunning(), "Kontener PostgreSQL powinien działać!");

        System.out.println("\n--- TESTCONTAINERS REPORT ---");
        System.out.println("Status: Baza danych jest ONLINE");
        System.out.println("Dynamiczny URL: " + postgres.getJdbcUrl());
        System.out.println("Dynamiczny Port: " + postgres.getMappedPort(5432));
        System.out.println("Użytkownik: " + postgres.getUsername());
        System.out.println("------------------------------\n");
    }
}