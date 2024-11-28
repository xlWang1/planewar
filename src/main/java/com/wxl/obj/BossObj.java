package com.wxl.obj;

import com.wxl.PlaneWar;
import com.wxl.util.GameState;
import com.wxl.util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BossObj extends GameObj {
    public int BossMAXHp;
    public int BossHp = BossMAXHp;
    public BossObj(BufferedImage image, int x, int y, int width, int height, double speed, PlaneWar frame,int BossMaxhp) {

        super(image,BossMaxhp, x, y, width, height, speed, frame);

        BossMAXHp = BossMaxhp;
        BossHp = BossMaxhp;
    }

    public BossObj() {
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
                PlaneWar.score += 2;

                BossHp -= myBullet.attack;

                if(BossHp <= 0){
                    GameUtil.drawString(g, "游戏胜利", Color.PINK, 36, 150, 300);
                    PlaneWar.currentState = GameState.GAME_OVER_SUCCESS;
                }
                break;
            }
        }
        //将percent转化为百分比格式
        double currentHp = 100*(double)BossHp/BossMAXHp;
        if(currentHp < 0){
            currentHp = 0;
        }
        String currentHpStr = String.format("%.2f", currentHp);
        //绘制血条
        g.setColor(Color.WHITE);
        g.fillRect(10, 20, 100, 15);
        g.setColor(Color.RED);
        g.fillRect(10, 20, BossHp*100/BossMAXHp, 15);

        try {
            GameUtil.drawString(g, currentHpStr + "%", Color.BLACK, 12, 60, 30);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }


    public static void main(String[] args) {
        BossObj bossObj = new BossObj(GameUtil.boss, 0, 0, GameUtil.boss.getWidth(), GameUtil.boss.getHeight(), 5, null, 100);
        System.out.println(bossObj.BossMAXHp);
        System.out.println(bossObj.BossHp);
    }
}
