package com.wxl.obj;
import com.wxl.PlaneWar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.awt.image.BufferedImage;


/**
 * 游戏对象基类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class GameObj {

    // 图像
    public BufferedImage image;
    //攻击力
    public double attack;
    public int HP;

    // 坐标
    public int x;
    public int y;

    // 尺寸
    public int width;
    public int height;

    // 速度
    public double speed;

    public PlaneWar frame;
    public GameObj(BufferedImage img, int x, int y, double s) {
        this.image = img;
        this.x = x;
        this.y = y;
        this.speed = s;
    }

    public GameObj(BufferedImage image, int x, int y, int width, int height, double speed, PlaneWar frame) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.frame = frame;
    }

    public GameObj(BufferedImage image, double bossMaxhp, int x, int y, int width, int height, double speed, PlaneWar frame) {
        this.image = image;
        this.attack = bossMaxhp;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.frame = frame;
    }

    // 绘制自己
    public void paintSelf(Graphics g) {
        g.drawImage(image, x, y, null);
    }
    // 获取矩形区域
    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }

}
