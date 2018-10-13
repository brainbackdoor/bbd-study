package com.brainbackdoor.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyLoader {
    private static final String PROPERTY_FILE_LOCATION = "src/main/resources/application.properties";
    private Map<String, String> propMap;

    private static final PropertyLoader instance = new PropertyLoader();
    private PropertyLoader() {
        loadProperties();
    }

    public String getPropertyValue(String key) {
        return propMap.get(key);
    }

    private void loadProperties() {
        Properties prop = new Properties();
        Path path = Paths.get(PROPERTY_FILE_LOCATION);
        try {
            prop.load(new FileInputStream(path.toFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        propMap = new HashMap<>();
        prop.forEach((key, value) -> propMap.put((String)key, (String)value));
    }

    public static PropertyLoader getInstance() {
        return instance;
    }
}
