package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {


    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties not found in src/test/resources");
            }
            properties.load(input);
        } catch (IOException e) {

            throw new RuntimeException("Could not read config.properties", e);
        }
    }

    private ConfigReader() {
    }


    public static String get(String key) {

        String override = System.getProperty(key);
        if (override != null) {
            return override;
        }
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Missing key in config.properties: " + key);
        }

        return value;
    }
}
