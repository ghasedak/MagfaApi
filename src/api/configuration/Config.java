package api.configuration;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by gladiator on 1/24/16.
 */
public class Config {
    private static String httpEndPointUrl;
    private static String domain;
    private static String username;
    private static String password;

    public void load() throws IOException {
        File configFile = new File("configuration.properties");
        FileReader fileReader = new FileReader(configFile);
        Properties config = new Properties();
        config.load(fileReader);
        httpEndPointUrl = config.getProperty("http.end.point.url");
        domain = config.getProperty("domain");
        username = config.getProperty("username");
        password = config.getProperty("password");
    }

    public static String getHttpEndPointUrl() {
        return httpEndPointUrl;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static String getDomain() {
        return domain;
    }
}
