package seleniumwithhooks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;

public class Hooks {

    // 1. Tworzymy wspólną sieć, żeby kontenery mogły ze sobą "rozmawiać"
    public static Network network = Network.newNetwork();

    // 2. Definiujemy kontener z Adminerem
    public GenericContainer<?> adminer =
            new GenericContainer<>("adminer:latest")
                    .withNetwork(network) // Dołączamy do sieci
                    .withNetworkAliases("moj-adminer") // Nadajemy mu "imię" (host)
                    .withExposedPorts(8080);

    // 3. Definiujemy kontener z Chrome
    public BrowserWebDriverContainer<?> chrome =
            new BrowserWebDriverContainer<>("selenium/standalone-chrome:latest")
                    .withCapabilities(new ChromeOptions())
                    .withNetwork(network); // Dołączamy do tej samej sieci

    @BeforeEach
    public void setup() {
        // Startujemy oba kontenery
        adminer.start();
        chrome.start();

        // Łączymy się z Chrome w Dockerze
        RemoteWebDriver driver = new RemoteWebDriver(chrome.getSeleniumAddress(), new ChromeOptions());

        // Wkładamy drivera do naszego "schowka"
        MyTestContext.setDriver(driver);
    }

    @AfterEach
    public void tearDown() {
        // Sprzątamy po testach
        if (MyTestContext.getDriver() != null) {
            MyTestContext.getDriver().quit();
        }

        // Gasimy oba kontenery
        chrome.stop();
        adminer.stop();
    }
}