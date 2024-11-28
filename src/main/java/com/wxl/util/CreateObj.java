package com.wxl.util;

import com.wxl.PlaneWar;
import com.wxl.obj.*;

public class CreateObj {
    public PlaneWar planeWar;
    public int eliteEnemyObjNum;

    public void createObj(PlaneWar frame) {
        this.planeWar = frame;
        // 生成子弹
        if (planeWar.count % 10 == 0) {
            if(planeWar.gameTimer.getElapsedSeconds() <= 10){
                GameUtil.myBulletList.add(new MyBulletObj(GameUtil.myBulletLevel1, planeWar.myShip.x + 7, planeWar.myShip.y-19, 10, 16,5,planeWar,5));
                GameUtil.AllObjList.add(GameUtil.myBulletList.get(GameUtil.myBulletList.size()-1));
            }else{
                if (!planeWar.bulletUp){
                    GameUtil.myBulletList.clear();
                    planeWar.bulletUp = true;
                }
                GameUtil.myBulletList.add(new MyBulletObj(GameUtil.myBulletLevel2, planeWar.myShip.x + 7, planeWar.myShip.y-19, 16, 16,10,planeWar,10));
                GameUtil.AllObjList.add(GameUtil.myBulletList.get(GameUtil.myBulletList.size()-1));
            }

        }
        // 生成敌机
        if (planeWar.count % 40 == 0) {
            GameUtil.enemyShipList.add(new EnemyShipObj(GameUtil.enemyShip1,(int)(Math.random()*14)*32 , 0, 32, 32, 5, planeWar,10));
            GameUtil.enemyShipList.add(new EnemyShipObj(GameUtil.enemyShip2,(int)(Math.random()*14)*32 , 0, 32, 32, 5, planeWar,5));
            GameUtil.enemyShipList.add(new EnemyShipObj(GameUtil.enemyShip3,(int)(Math.random()*14)*32 , 0, 32, 32, 5, planeWar,8));
            int size = GameUtil.enemyShipList.size();
            for (int i = size - 3; i < size; i++) {
                GameUtil.AllObjList.add(GameUtil.enemyShipList.get(i));
            }
            planeWar.count = 0;
        }
        // 生成boss
        if (planeWar.gameTimer.getElapsedSeconds() > 60 && planeWar.boss == null) {
            planeWar.boss = new BossObj(GameUtil.boss, 0, 0, GameUtil.boss.getWidth(), GameUtil.boss.getHeight(),8,planeWar,220);
            GameUtil.bossList.add(planeWar.boss);

            GameUtil.AllObjList.add(GameUtil.bossList.get(GameUtil.bossList.size()-1));
        }
        // 生成精英敌机
        if (planeWar.gameTimer.getElapsedSeconds()> 10 && eliteEnemyObjNum < 3) {
            if (eliteEnemyObjNum == 0) {
                EliteEnemyObj eliteEnemyObj1 = new EliteEnemyObj(GameUtil.eliteEnemyShip1, 150, 0, 80,GameUtil.eliteEnemyShip1.getWidth(), GameUtil.eliteEnemyShip1.getHeight(),5,planeWar);
//                planeWar.eliteEnemyObjLists.add(eliteEnemyObj1);
                GameUtil.eliteEnemyShipList.add(eliteEnemyObj1);
                eliteEnemyObjNum = 1;
                GameUtil.AllObjList.add(GameUtil.eliteEnemyShipList.get(GameUtil.eliteEnemyShipList.size()-1));
            } else if (planeWar.gameTimer.getElapsedSeconds()> 20 && eliteEnemyObjNum < 2){
                EliteEnemyObj eliteEnemyObj2 = new EliteEnemyObj(GameUtil.eliteEnemyShip1, 180, 0, 130,GameUtil.eliteEnemyShip1.getWidth(), GameUtil.eliteEnemyShip1.getHeight(),5,planeWar);
                GameUtil.eliteEnemyShipList.add(eliteEnemyObj2);
                eliteEnemyObjNum = 2;
                GameUtil.AllObjList.add(GameUtil.eliteEnemyShipList.get(GameUtil.eliteEnemyShipList.size()-1));
            } else if (planeWar.boss != null) {
                EliteEnemyObj eliteEnemyObj3 = new EliteEnemyObj(GameUtil.eliteEnemyShip1, 200, 0, 80,GameUtil.eliteEnemyShip1.getWidth(), GameUtil.eliteEnemyShip1.getHeight(),5,planeWar);
                GameUtil.eliteEnemyShipList.add(eliteEnemyObj3);
                eliteEnemyObjNum = 3;
                GameUtil.AllObjList.add(GameUtil.eliteEnemyShipList.get(GameUtil.eliteEnemyShipList.size()-1));
            }
        }
        // 生成敌方子弹
        if (planeWar.count % 20 == 0) {

            if (planeWar.boss != null && planeWar.boss.BossHp <= planeWar.boss.BossMAXHp/2){
                if (!planeWar.bossBulletUp){
                    GameUtil.enemyBulletList.clear();
                    planeWar.bossBulletUp = true;
                }
                GameUtil.enemyBulletList.add(new EnemyBulletObj(GameUtil.eneMyBulletLevel2,planeWar.boss.x+35,planeWar.boss.y-20,24,16,10,planeWar,30));
                GameUtil.AllObjList.add(GameUtil.enemyBulletList.get(GameUtil.enemyBulletList.size()-1));
            } else if (planeWar.boss != null){
                GameUtil.enemyBulletList.add(new EnemyBulletObj(GameUtil.eneMyBulletLevel1,planeWar.boss.x+35,planeWar.boss.y-20,16,16,8,planeWar,15));
                GameUtil.AllObjList.add(GameUtil.enemyBulletList.get(GameUtil.enemyBulletList.size()-1));
            }
            if (GameUtil.eliteEnemyShipList.size() >= 1){
                for(EliteEnemyObj e : GameUtil.eliteEnemyShipList) {
                    if(e.EliteEnemyHp > 0){
                        GameUtil.enemyBulletList.add(new EnemyBulletObj(GameUtil.eneMyBulletLevel1,e.getX()+22,e.getX()+57,16,16,8,planeWar,15));
                        GameUtil.AllObjList.add(GameUtil.enemyBulletList.get(GameUtil.enemyBulletList.size()-1));
                    }
                }

            }
        }
    }
}
