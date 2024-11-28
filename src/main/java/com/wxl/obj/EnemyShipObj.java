package com.wxl.obj;

import com.wxl.PlaneWar;
import com.wxl.util.GameState;
import com.wxl.util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EnemyShipObj extends GameObj{
    public int enemyShipHP;
    public int enemyMaxShipHP;
    public EnemyShipObj(BufferedImage image, int x, int y, int width, int height, double speed, PlaneWar frame,int HP) {
        super(image, x, y, width, height, speed, frame);
        this.enemyMaxShipHP = HP;
        this.enemyShipHP = this.enemyMaxShipHP;
    }

    public EnemyShipObj() {
        super();
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        y += speed;
        // 碰撞检测
        for (MyBulletObj myBullet : GameUtil.myBulletList) {
            if (this.getRec().intersects(myBullet.getRec())) {
                this.enemyShipHP -= myBullet.attack;
                g.drawImage(GameUtil.boom, this.x, this.y, frame);
                if (this.enemyShipHP <= 0) {
                    this.setX(-100);
                    this.setY(100);
                    myBullet.setX(-200);
                    myBullet.setY(-200);
                    GameUtil.removeObjList.add(this);
                    GameUtil.removeObjList.add(myBullet);
                    PlaneWar.score += this.enemyMaxShipHP;
                }

                break;
            }
        }
        //与我方飞机碰撞检测
        if (this.getRec().intersects(this.frame.myShip.getRec())) {
            GameUtil.drawString(g, "游戏失败", Color.RED, 36, 150, 300);
            PlaneWar.currentState = GameState.GAME_OVER_FAILED;
        }
        // 越界检测
        if(this.y > this.frame.getHeight()){
            this.setX(-100);
            this.setY(100);
            GameUtil.removeObjList.add(this);
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
