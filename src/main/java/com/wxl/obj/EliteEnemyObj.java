package com.wxl.obj;

import com.wxl.PlaneWar;
import com.wxl.util.GameState;
import com.wxl.util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EliteEnemyObj extends GameObj {
    public int EliteEnemyMaxHp;
    public int EliteEnemyHp = 100;
    public EliteEnemyObj(BufferedImage image, int HP, int x, int y, int width, int height, double speed, PlaneWar frame) {
        super(image, HP, x, y, width, height, speed, frame);
        this.EliteEnemyMaxHp = HP;
        this.EliteEnemyHp = EliteEnemyMaxHp;
    }

    public EliteEnemyObj() {
        super();
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        if(this.getX() < 0 || this.getX() > (this.frame.getWidth()-this.getWidth())){
            this.setSpeed(-this.getSpeed());
        }
        this.setX(this.getX() + (int)this.getSpeed());
        //碰撞检测
        for (MyBulletObj myBullet : GameUtil.myBulletList){
            if(myBullet.getRec().intersects(this.getRec())){
                g.drawImage(GameUtil.boom, myBullet.getX(), myBullet.getY(), frame);
                myBullet.setX(-150);
                myBullet.setY(0);
                GameUtil.removeObjList.add(myBullet);
                EliteEnemyHp -= myBullet.attack;

                if(EliteEnemyHp <= 0){
                    g.drawImage(GameUtil.boom, this.getX(), this.getY(), frame);
                    this.setX(-444);
                    this.setY(0);
                    PlaneWar.score += this.EliteEnemyMaxHp;
                    GameUtil.removeObjList.add(this);
                }
                break;
            }
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
