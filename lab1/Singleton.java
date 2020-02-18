package lab1;

import java.io.FileInputStream;
import java.util.Properties;

public class Singleton {
    private static Singleton uniq = null;

    private static String fileName = "lab1\\config.properties";
    private Properties properties;
    

    private Singleton() {
        try(FileInputStream fInputStream = new FileInputStream(fileName)) {
            properties = new Properties();
            properties.load(fInputStream);
        } 
        catch (Exception ex) {
            System.err.println(ex);
        }
    }

    public static Singleton getInstance() {
        if (uniq == null) {
            uniq = new Singleton();
        }
        return uniq;
    }

    public Properties getProperties() {
        return properties;
    }

}