package com.wxl.util;

import java.io.InputStream;
import java.util.Properties;

public class GetPropertityFromFile {
    public static String bakcGroundMusic;
    public static String shootMusic;
    public static void getPropertity() {
        try(InputStream resourceAsStream = GetPropertityFromFile.class.getResourceAsStream("/filePath.properties")) {
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            bakcGroundMusic = properties.getProperty("backGroundMusic");
            shootMusic = properties.getProperty("shootMusic");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
