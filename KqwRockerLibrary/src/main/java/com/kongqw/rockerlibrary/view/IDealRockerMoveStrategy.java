package com.kongqw.rockerlibrary.view;


import com.kongqw.rockerlibrary.view.RockerView;

public interface IDealRockerMoveStrategy {

    /**
     * CallBackMode.CALL_BACK_MODE_MOVE 情况下 根据角度来判断方向，然后回调出去
     *
     * @param angle 角度
     */
    void dealCallBackModeMove(RockerView rockerView, double angle);

    /**
     * CallBackMode.CALL_BACK_MODE_STATE_CHANGE 情况下 根据角度来判断方向，然后回调出去
     *
     * @param angle 角度
     */
    void dealCallBackModeStateChange(RockerView rockerView, double angle);
}
