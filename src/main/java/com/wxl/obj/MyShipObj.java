package com.wxl.obj;

import com.wxl.PlaneWar;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class MyShipObj extends GameObj{
    public int MyMaxHP;
    public int MyHP;
    public MyShipObj(BufferedImage image, int HP,int x, int y, int width, int height, double speed, PlaneWar frame) {
        super(image,HP,x, y, width, height, speed, frame);
        this.MyMaxHP = HP;
        this.MyHP = this.MyMaxHP;
        //添加鼠标滚动时间监听器
        this.frame.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e){
                MyShipObj.this.x = e.getX()-width+5;
                MyShipObj.this.y = e.getY()-height-5;
            }
        });
    }
    public int getHP() {
        return MyHP;
    }

    public MyShipObj() {
        super();
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
