package com.milkyteapos.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesUtil<T> {

    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private static Properties props;

    private static final String  fileName = "application.properties";

    static {
        props = new Properties();
        try {
            props.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName),"UTF-8"));
        } catch (IOException e) {
            logger.error("配置文件读取异常",e);
        }
    }

    public static String getProperty(String key){
        String value = props.getProperty(key.trim());
        if(StringUtils.isBlank(value)){
            return null;
        }
        return value.trim();
    }

    public static void setProperty(String key, String value){
        try {
            Properties props = new Properties();
            props.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName));
            OutputStream fos = new FileOutputStream(PropertiesUtil.class.getClassLoader().getResource(fileName).getFile());
            props.setProperty(key.trim(), value.trim());
            props.store(fos, "Update value");
            fos.close();
        }catch (IOException e) {
            e.printStackTrace();
            System.err.println("属性文件更新错误");
        }

    }

    public static String getProperty(String key,String defaultValue){

        String value = props.getProperty(key.trim());
        if(StringUtils.isBlank(value)){
            value = defaultValue;
        }
        return value.trim();
    }




}

