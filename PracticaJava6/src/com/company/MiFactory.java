package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class MiFactory {

    public static Object getInstance(String objName) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Properties properties = new Properties();
            properties.load(new FileInputStream(new File(objName)));
            String className = properties.getProperty("sorter");
            System.out.println(className);
            return Class.forName(className).getConstructor().newInstance();
    }

}
