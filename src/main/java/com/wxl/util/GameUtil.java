package com.wxl.util;

import com.wxl.obj.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class GameUtil {

    // 定义静态属性
    public static BufferedImage myShip;
    public static BufferedImage boss;
    public static BufferedImage boom;
    public static BufferedImage enemyShip2;
    public static BufferedImage enemyShip3;
    public static BufferedImage enemyShip1;
    public static BufferedImage eliteEnemyShip1;
    public static ArrayList<EnemyShipObj> enemyShipList = new ArrayList<>();
    public static ArrayList<EliteEnemyObj> eliteEnemyShipList = new ArrayList<>();
    public static ArrayList<GameObj> AllObjList = new ArrayList<>();
    public static ArrayList<GameObj> removeObjList = new ArrayList<>();
    public static ArrayList<BossObj> bossList = new ArrayList<>();
    public static ArrayList<MyBulletObj> myBulletList = new ArrayList<>();
    public static ArrayList<EnemyBulletObj> enemyBulletList = new ArrayList<>();
    public static BufferedImage myBulletLevel1;
    public static BufferedImage eneMyBulletLevel1;
    public static BufferedImage eneMyBulletLevel2;
    public static BufferedImage myBulletLevel2;
    public static BufferedImage background;

    // 静态代码块，用于加载图片资源
    static {
        try {
            myShip = loadInternalImage("/images/ship/myShip.png");
            enemyShip1 = loadInternalImage("/images/ship/enemyShip01.png");
            enemyShip2 = loadInternalImage("/images/ship/enemyShip02.png");
            enemyShip3 = loadInternalImage("/images/ship/enemyShip03.png");
            eliteEnemyShip1 = loadInternalImage("/images/ship/eliteEnemy01.png");
            myBulletLevel1 = loadInternalImage("/images/bullet/myBullet01.png");
            myBulletLevel2 = loadInternalImage("/images/bullet/myBullet02.png");
            eneMyBulletLevel1 = loadInternalImage("/images/bullet/enemyBullet01.png");
            eneMyBulletLevel2 = loadInternalImage("/images/bullet/enemyBullet02.png");
            background = loadInternalImage("/images/background.png");
            boom = loadInternalImage("/images/boom.png");
            boss = loadInternalImage("/images/ship/boss.png");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("图片加载失败");
            System.exit(1); // 退出程序，因为缺少关键资源
        }
    }

    /**
     * 加载图片资源
     *
     * @param path 图片资源的路径
     * @return 加载的图片对象
     * @throws IOException 如果读取图片时发生错误
     */
    private static BufferedImage loadInternalImage(String path) throws IOException {
        InputStream is = GameUtil.class.getResourceAsStream(path);
        if (is == null) {
            throw new IOException("Resource not found: " + path);
        }
        return ImageIO.read(is);
    }

    /**
     * 在窗体上绘制字符串
     *
     * @param g Graphics 对象
     * @param text 要绘制的字符串
     * @param color 文字颜色
     * @param size 字体大小
     * @param x x 坐标
     * @param y y 坐标
     */
    public static void drawString(Graphics g, String text, Color color, int size, int x, int y) {
        Font font = new Font("仿宋", Font.PLAIN, size);
        g.setFont(font);
        g.setColor(color);
        g.drawString(text, x, y);
    }
}