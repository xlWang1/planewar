package com.wxl.obj;

import com.wxl.PlaneWar;
import com.wxl.util.GameState;
import com.wxl.util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EnemyBulletObj extends GameObj {
    public EnemyBulletObj(BufferedImage image, int x, int y, int width, int height, double speed, PlaneWar frame,double attack) {
        super(image,attack, x, y, width, height, speed, frame);
    }

    public EnemyBulletObj() {
        super();
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        y += speed;
        // 碰撞检测
        if (y > this.frame.getHeight()) {
            this.setX(-500);
            this.setY(500);
            GameUtil.removeObjList.add(this);
        }
        if (getRec().intersects(this.frame.myShip.getRec())) {

            this.frame.myShip.MyHP = (int) (this.frame.myShip.MyHP - this.attack);

            if (this.frame.myShip.getHP() <= 0) {
                g.drawImage(GameUtil.boom, this.x, this.y, this.frame);
                GameUtil.drawString(g, "游戏失败", Color.RED, 36, 150, 300);
                PlaneWar.currentState = GameState.GAME_OVER_FAILED;
            }else {
                g.drawImage(GameUtil.boom, this.x, this.y, this.frame);
                setX(-2222);
                setY(100);
                GameUtil.removeObjList.add(this);
            }
        }
        for (MyBulletObj myBullet : GameUtil.myBulletList) {
            if (getRec().intersects(myBullet.getRec())) {
                g.drawImage(GameUtil.boom, this.x, this.y, this.frame);
                setX(-222);
                setY(100);
                myBullet.setX(-20);
                myBullet.setY(20);
                GameUtil.removeObjList.add(this);
                GameUtil.removeObjList.add(myBullet);
            }
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
