package filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertiesReader {

    private static final Logger log = Logger.getLogger(PropertiesReader.class.getName());
    private static final Properties properties = new Properties();

    public static Properties getProps() {
        log.info("loading props");
        try {
            properties.load(PropertiesReader.class.getResourceAsStream("/properties.properties"));
        } catch (IOException ex) {
            Logger.getLogger(PropertiesReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return properties;
    }

    public static Map<String, String> getPropsAsMap() {
        log.info("properties to map");
        getProps();
        String key;
        String val;
        Map<String, String> kv = new HashMap<>();
        for (Map.Entry<Object, Object> e : properties.entrySet()) {
            key = e.getKey().toString();
            val = e.getValue().toString();
            kv.put(key, val);
        }
        return kv;
    }

}
