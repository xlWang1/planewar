package com.wxl;

import com.wxl.obj.*;
import com.wxl.util.*;

import javax.sound.sampled.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;


public class PlaneWar extends JFrame {
    public static int width = 480;
    public static int height = 600;
    public int count;
    public static int score = 0;
    public static GameState currentState = GameState.NOT_STARTED;
    public BgObj bgObj = new BgObj(GameUtil.background, 0, height-GameUtil.background.getHeight(),2);
//    public BgObj bgObj = new BgObj(GameUtil.background, 0, -100,2);

    public  MyShipObj myShip = new MyShipObj(GameUtil.myShip,30,200, 450, 32, 32, 0, this);
    public BossObj boss = null;
    public boolean bossBulletUp;
    public boolean isCountTime = false;
    public GameTimer gameTimer = new GameTimer();
    public boolean bulletUp;


    /**
     * 初始化窗口的方法
     */
    public void launch() {
       String title = "飞机大战";
       String  fontName = "仿宋";
        // 设置窗口标题
        setTitle(title);
        // 设置窗口大小
        setSize(width, height);
        // 设置关闭操作
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置窗口位置居中
        setLocationRelativeTo(null);
        // 创建并添加一个自定义面板
        JPanel panel = new BackgroundPanel();
        panel.setPreferredSize(new Dimension(width, height));
        add(panel);
        // 设置字体（可选）
        Font font = new Font(fontName, Font.PLAIN, 14);
        panel.setFont(font);
        // 设置窗口可见
        setVisible(true);
        GameUtil.AllObjList.add(bgObj);
        GameUtil.AllObjList.add(myShip);
        //添加鼠标点击事件监听器
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (currentState == GameState.NOT_STARTED) {
                    currentState = GameState.IN_PROGRESS;
                    repaint();
                }
            }
        });
        //添加键盘事件，按下空格键游戏暂停
        addKeyListener( new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 32) {
                    if (currentState == GameState.IN_PROGRESS) {
                        currentState = GameState.PAUSED;
                        repaint();
                    } else if (currentState == GameState.PAUSED) {
                        currentState = GameState.IN_PROGRESS;
                        repaint();
                    }
                }
            }
        });
        //启动游戏循环线程
        new Thread(() -> {
            CreateObj createObj = new CreateObj();
            while (true) {
                if (currentState == GameState.IN_PROGRESS){
                    createObj.createObj(this);
                    repaint();
                }
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        playMusic(GetPropertityFromFile.bakcGroundMusic);
        playFireSound(GetPropertityFromFile.shootMusic);


    }


    private void playFireSound(String filePath) {
        new Thread(() -> {
            System.out.println("播放开火线程:" + Thread.currentThread().getName());
            try(InputStream resourceAsStream = PlaneWar.class.getResourceAsStream(filePath);
                BufferedInputStream bufferedStream = new BufferedInputStream(Objects.requireNonNull(resourceAsStream))) {
                // 加载音频文件
                // 使用 BufferedInputStream 包装输入流
                // 将输入流转换为 AudioInputStream
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(Objects.requireNonNull(bufferedStream));
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                //每两秒播放一次音效
                while (true) {
                    if (currentState == GameState.IN_PROGRESS) {
                        Thread.sleep(300);
                        clip.setFramePosition(0);
                        clip.start();
                    }else if (currentState == GameState.GAME_OVER_FAILED || currentState == GameState.GAME_OVER_SUCCESS || currentState == GameState.PAUSED) {
                        break;
                    }else {
                       Thread.sleep(1000);
                    }

                }
            } catch (InterruptedException | UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }).start();
    }



    private void playMusic(String filePath) {
        // 加载音频文件
        // 循环播放背景音乐
        // 等待音乐播放完毕
        Thread musicThread = new Thread(() -> {
            System.out.println("播放背景音乐线程:" + Thread.currentThread().getName());
            try(InputStream resourceAsStream = PlaneWar.class.getResourceAsStream(filePath);
                BufferedInputStream bufferedStream = new BufferedInputStream(Objects.requireNonNull(resourceAsStream))) {
                // 加载音频文件
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(Objects.requireNonNull(bufferedStream));
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);

                // 循环播放背景音乐
                clip.loop(Clip.LOOP_CONTINUOUSLY);

                // 等待音乐播放完毕
                synchronized (clip) {
                    clip.wait();
                }
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        musicThread.start();
    }

    /**
     * 自定义面板类，用于绘制背景图片
     */
    private class BackgroundPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // 根据游戏状态绘制不同的内容
            switch (currentState) {
                case NOT_STARTED -> {
                    bgObj.paintSelf(g);
                    GameUtil.drawString(g, "开始游戏", Color.YELLOW, 36, 150, 300);
                }
                case IN_PROGRESS -> {
                    if(!isCountTime){
                        gameTimer.start();
                        isCountTime = true;
                    }
                    for (GameObj obj : GameUtil.AllObjList) {
                        obj.paintSelf(g);
                    }
                    GameUtil.AllObjList.removeAll(GameUtil.removeObjList);
                    GameUtil.removeObjList.clear();

                    count++;
                }
                case PAUSED ->{
                    g.drawImage(GameUtil.background, 0, 0, this);
                    GameUtil.drawString(g, "游戏暂停", Color.YELLOW, 36, 150, 300);
                }

                case GAME_OVER_FAILED -> GameUtil.drawString(g, "游戏失败", Color.YELLOW, 36, 150, 300);
                case GAME_OVER_SUCCESS -> GameUtil.drawString(g, "游戏胜利", Color.GREEN, 36, 150, 300);
            }
            GameUtil.drawString(g, "分数:" + score, Color.YELLOW, 18, 0, 512);
            GameUtil.drawString(g, "游戏时间:" + gameTimer.getFormattedElapsedTime(), Color.RED, 18, 0, 530);
        }
    }

}
