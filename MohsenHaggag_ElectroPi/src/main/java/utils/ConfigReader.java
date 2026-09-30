package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();
    private static final String CONFIG_PATH = "src/test/resources/config.properties";

    static {
        try (FileInputStream fis = new FileInputStream(CONFIG_PATH)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load config.properties at " + CONFIG_PATH, e);
        }
    }

    private ConfigReader() {
    }

    // ---- Generic accessors (escape hatch for one-off / future keys) ----

    public static String get(String key) {
        String value = System.getProperty(key, properties.getProperty(key));
        if (value == null) {
            throw new RuntimeException("Missing config key: " + key);
        }
        return value;
    }

    public static String get(String key, String defaultValue) {
        return System.getProperty(key, properties.getProperty(key, defaultValue));
    }

    // ---- Application under test ----

    public static String baseUrl() {
        return get("baseUrl");
    }

    public static String env() {
        return get("env", "staging");
    }

    // ---- Browser / execution ----

    public static String browser() {
        return get("browser", "chrome");
    }

    public static boolean headless() {
        return Boolean.parseBoolean(get("headless", "false"));
    }

    public static int explicitWaitSeconds() {
        return Integer.parseInt(get("explicit.wait.seconds", "10"));
    }

    // ---- Store Admin credentials ----

    public static class Admin {
        private Admin() {
        }

        public static String username() {
            return get("Admin.username");
        }

        public static String password() {
            return get("Admin.password");
        }
    }

    // ---- API testing ----

    public static String apiBaseUrl() {
        return get("api.base.url");
    }

    public static String authLoginEndpoint() {
        return get("auth.login.endpoint");
    }
}