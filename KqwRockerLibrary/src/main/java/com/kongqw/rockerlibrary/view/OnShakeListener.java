package com.kongqw.rockerlibrary.view;

/**
 * 摇动方向监听接口
 */
public interface OnShakeListener {
    // 开始
    void onStart();

    /**
     * 摇动方向
     *
     * @param direction 方向
     */
    void direction(Direction direction);

    // 结束
    void onFinish();
}