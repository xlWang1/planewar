package com.wxl.obj;

import com.wxl.PlaneWar;
import com.wxl.util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MyBulletObj extends GameObj {
    public MyBulletObj(BufferedImage image, int x, int y, int width, int height, double speed, PlaneWar frame,double attack) {
        super(image, attack, x, y, width, height, speed, frame);
    }

    public MyBulletObj() {
        super();
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        y -= speed;
        // 判断子弹是否越界
        if (y < 0) {
            this.setX(-300);
            this.setY(-300);
            GameUtil.removeObjList.add(this);
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
