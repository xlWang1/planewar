package com.wxl.util;
import java.util.concurrent.TimeUnit;
public class GameTimer {

    private long startTime;
    private long stopTime;
    private boolean running;

    public GameTimer() {
        reset();
    }

    /**
     * 开始计时
     */
    public void start() {
        startTime = System.currentTimeMillis();
        running = true;
    }

    /**
     * 停止计时
     */
    public void stop() {
        stopTime = System.currentTimeMillis();
        running = false;
    }

    /**
     * 重置计时器
     */
    public void reset() {
        startTime = 0;
        stopTime = 0;
        running = false;
    }

    /**
     * 获取已经过去的时间（毫秒）
     * @return 已经过去的时间（毫秒）
     */
    public long getElapsedMillis() {
        if (running) {
            return System.currentTimeMillis() - startTime;
        } else {
            return stopTime - startTime;
        }
    }

    /**
     * 获取已经过去的时间（秒）
     * @return 已经过去的时间（秒）
     */
    public long getElapsedSeconds() {
        return TimeUnit.MILLISECONDS.toSeconds(getElapsedMillis());
    }

    /**
     * 获取已经过去的时间（分钟）
     * @return 已经过去的时间（分钟）
     */
    public long getElapsedMinutes() {
        return TimeUnit.MILLISECONDS.toMinutes(getElapsedMillis());
    }

    /**
     * 获取已经过去的时间（格式化为 MM:ss）
     * @return 已经过去的时间（格式化为 MM:ss）
     */
    public String getFormattedElapsedTime() {
        long minutes = getElapsedMinutes();
        long seconds = getElapsedSeconds() % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}