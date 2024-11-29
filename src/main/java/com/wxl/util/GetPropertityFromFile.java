package com.wxl.util;

import java.io.InputStream;
import java.util.Properties;

public class GetPropertityFromFile {
    public static String bakcGroundMusic;
    public static String shootMusic;
    public static String myShip;
    public static String enemyShip1;
    public static String enemyShip2;
    public static String enemyShip3;
    public static String eliteEnemyShip1;
    public static String myBulletLevel1;
    public static String myBulletLevel2;
    public static String eneMyBulletLevel1;
    public static String eneMyBulletLevel2;
    public static String backGround;
    public static String boom;
    public static String boss;
    public static void getPropertity() {
        try(InputStream resourceAsStream = GetPropertityFromFile.class.getResourceAsStream("/filePath.properties")) {
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            bakcGroundMusic = properties.getProperty("backGroundMusic");
            shootMusic = properties.getProperty("shootMusic");
            myShip = properties.getProperty("myShip");
            enemyShip1 = properties.getProperty("enemyShip1");
            enemyShip2 = properties.getProperty("enemyShip2");
            enemyShip3 = properties.getProperty("enemyShip3");
            eliteEnemyShip1 = properties.getProperty("eliteEnemyShip1");
            myBulletLevel1 = properties.getProperty("myBulletLevel1");
            myBulletLevel2 = properties.getProperty("myBulletLevel2");
            eneMyBulletLevel1 = properties.getProperty("eneMyBulletLevel1");
            eneMyBulletLevel2 = properties.getProperty("eneMyBulletLevel2");
            backGround = properties.getProperty("background");
            boom = properties.getProperty("boom");
            boss = properties.getProperty("boss");


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
