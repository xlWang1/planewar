package com.wxl.obj;

import com.wxl.PlaneWar;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BgObj extends GameObj {
    public BgObj(BufferedImage image, int x, int y, double speed) {
        super(image, x, y, speed);
    }

    public BgObj() {
        super();
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        this.y += this.speed;
        if (this.y >= 0){
//            this.y = -100;
            this.y = -475;

        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
