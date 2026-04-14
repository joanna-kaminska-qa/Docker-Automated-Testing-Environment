package seleniumwithcompose;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;

public class HooksCompose {

    @BeforeEach
    public void setup() throws Exception {
        // Łączymy się z robotem, który stoi w Docker Compose
        URL url = new URL("http://przegladarka-robot:4444/wd/hub");
        RemoteWebDriver driver = new RemoteWebDriver(url, new ChromeOptions());

        MyTestContextCompose.setDriver(driver);
    }

    @AfterEach
    public void tearDown() {
        if (MyTestContextCompose.getDriver() != null) {
            // 1. Robimy zdjęcie
            var camera = (org.openqa.selenium.TakesScreenshot) MyTestContextCompose.getDriver();
            java.io.File screenshot = camera.getScreenshotAs(org.openqa.selenium.OutputType.FILE);

            // 2. Zapisujemy je w folderze "build" (on zawsze istnieje w kontenerze)
            try {
                java.nio.file.Files.copy(screenshot.toPath(),
                        new java.io.File("screenshot_adminer.png").toPath(),
                        java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Zrobiono screenshot: screenshot_adminer.png");
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }

            MyTestContextCompose.getDriver().quit();
        }
    }
}