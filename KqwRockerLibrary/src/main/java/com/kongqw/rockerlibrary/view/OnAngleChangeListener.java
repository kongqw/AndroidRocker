package com.kongqw.rockerlibrary.view;

/**
 * 摇动角度的监听接口
 */
public interface OnAngleChangeListener {
    // 开始
    void onStart();

    /**
     * 摇杆角度变化
     *
     * @param angle 角度[0,360)
     */
    void angle(double angle);

    // 结束
    void onFinish();
}