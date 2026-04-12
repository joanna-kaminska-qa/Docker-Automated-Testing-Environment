package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static Properties properties = new Properties();

    static {
        try (InputStream is = ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(is);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Nie udało się załadować pliku config.properties!");
        }
    }

    public static String getBaseUri() {
        return properties.getProperty("baseUri");
    }
}