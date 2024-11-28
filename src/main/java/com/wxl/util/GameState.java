package com.wxl.util;
/**
 * 游戏状态枚举类
 */
public enum GameState {
    NOT_STARTED(0),  // 游戏未开始
    IN_PROGRESS(1),  // 游戏进行中
    PAUSED(2),       // 游戏暂停
    GAME_OVER_FAILED(3),  // 游戏通关失败
    GAME_OVER_SUCCESS(4);  // 游戏通关成功

    private int stateCode;

    GameState(int stateCode) {
        this.stateCode = stateCode;
    }

    /**
     * 获取状态码
     * @return 状态码
     */
    public int getStateCode() {
        return stateCode;
    }

    /**
     * 根据状态码获取游戏状态枚举
     * @param stateCode 状态码
     * @return 对应的游戏状态枚举
     */
    public static GameState fromStateCode(int stateCode) {
        for (GameState gameState : GameState.values()) {
            if (gameState.getStateCode() == stateCode) {
                return gameState;
            }
        }
        throw new IllegalArgumentException("未知的状态码: " + stateCode);
    }
}
