package com.generate.project.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 读取配置文件工具类
 */
public class PropertiesUtils {
    private static final Properties prop = new Properties();
    private static final Map<String, String> PROPS_MAP = new ConcurrentHashMap<>();

    static {
        InputStream is = null;
        try {
            is = PropertiesUtils.class.getClassLoader().getResourceAsStream("application.properties");
            prop.load(is);
            Iterator<Object> iterator = prop.keySet().iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                PROPS_MAP.put(key, prop.getProperty(key));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static String getString(String key) {
        return PROPS_MAP.get(key);
    }

    public static void main(String[] args) {
        System.out.println(PropertiesUtils.getString("db.password"));
    }

}
