package seleniumwithhooks;

import org.openqa.selenium.WebDriver;

public class MyTestContext {
    private static WebDriver driver;

    public static void setDriver(WebDriver d) {
        driver = d;
    }

    public static WebDriver getDriver() {
        return driver;
    }
}