package com.kongqw.rockerlibrary.view.strategy;

import com.kongqw.rockerlibrary.view.Direction;
import com.kongqw.rockerlibrary.view.RockerView;

import static com.kongqw.rockerlibrary.view.JudgmentUtil.*;
import static com.kongqw.rockerlibrary.view.AngelConstants.*;

/**
 * 纵向 上下两个方向
 */
public class Direction4Rotate0Strategy implements IDealRockerMoveStrategy{
    @Override
    public void dealCallBackModeMove(RockerView rockerView, double angle) {
        if (judgmentIsInRange(angle, ANGLE_4D_OF_0P, ANGLE_4D_OF_1P)) {  // 0度到-90度
            // 右下
            rockerView.getOnShakeListener().direction(Direction.DIRECTION_DOWN_RIGHT);
        } else if (judgmentIsInRange(angle, ANGLE_4D_OF_1P, ANGLE_4D_OF_2P)) { // 90度到-180度
            // 左下
            rockerView.getOnShakeListener().direction(Direction.DIRECTION_DOWN_LEFT);
        } else if (judgmentIsInRange(angle, ANGLE_4D_OF_2P, ANGLE_4D_OF_3P)) { // 180度到-270度
            // 左上
            rockerView.getOnShakeListener().direction(Direction.DIRECTION_UP_LEFT);
        } else if (judgmentIsInRange(angle, ANGLE_4D_OF_3P, ANGLE_360)) {  // 270度到-360度
            // 右上
            rockerView.getOnShakeListener().direction(Direction.DIRECTION_UP_RIGHT);
        }
    }

    @Override
    public void dealCallBackModeStateChange(RockerView rockerView, double angle) {
        if (judgmentIsInRange(angle, ANGLE_4D_OF_0P, ANGLE_4D_OF_1P) && rockerView.getTempDirection() != Direction.DIRECTION_DOWN_RIGHT) {// 0度到-90度 并且当前不是右下方向
            // 右下
            rockerView.setTempDirection(Direction.DIRECTION_DOWN_RIGHT);
            rockerView.getOnShakeListener().direction(Direction.DIRECTION_DOWN_RIGHT);
        } else if (judgmentIsInRange(angle, ANGLE_4D_OF_1P, ANGLE_4D_OF_2P) && rockerView.getTempDirection() != Direction.DIRECTION_DOWN_LEFT) {// 90度到-180度 并且当前不是左下方向
            // 左下
            rockerView.setTempDirection(Direction.DIRECTION_DOWN_LEFT);
            rockerView.getOnShakeListener().direction(Direction.DIRECTION_DOWN_LEFT);
        } else if (judgmentIsInRange(angle, ANGLE_4D_OF_2P, ANGLE_4D_OF_3P) && rockerView.getTempDirection() != Direction.DIRECTION_UP_LEFT) {// 180度到-270度 并且当前不是左上方向
            // 左上
            rockerView.setTempDirection(Direction.DIRECTION_UP_LEFT);
            rockerView.getOnShakeListener().direction(Direction.DIRECTION_UP_LEFT);
        } else if (judgmentIsInRange(angle, ANGLE_4D_OF_3P, ANGLE_360) && rockerView.getTempDirection() != Direction.DIRECTION_UP_RIGHT) {// 270度到-360度 并且当前不是右上方向
            // 右上
            rockerView.setTempDirection(Direction.DIRECTION_UP_RIGHT);
            rockerView.getOnShakeListener().direction(Direction.DIRECTION_UP_RIGHT);
        }
    }
}
