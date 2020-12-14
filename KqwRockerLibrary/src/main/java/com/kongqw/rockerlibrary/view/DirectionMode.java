package com.kongqw.rockerlibrary.view;

/**
 * 摇杆支持几个方向
 */
public enum DirectionMode {
    /**
     * <img src="https://img-blog.csdn.net/20160901183144896">
     */
    DIRECTION_2_HORIZONTAL,// 横向 左右两个方向
    /**
     * <img src="https://img-blog.csdn.net/20160901183332084">
     */
    DIRECTION_2_VERTICAL, // 纵向 上下两个方向
    /**
     * <img src="https://img-blog.csdn.net/20160901183347055">
     */
    DIRECTION_4_ROTATE_0, // 四个方向
    /**
     * <img src="https://img-blog.csdn.net/20160901183404461">
     */
    DIRECTION_4_ROTATE_45, // 四个方向 旋转45度
    /**
     * <img src="https://img-blog.csdn.net/20160901183419477">
     */
    DIRECTION_8 // 八个方向
}