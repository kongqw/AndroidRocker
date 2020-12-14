package com.kongqw.rockerlibrary.view;

import static com.kongqw.rockerlibrary.view.JudgmentUtil.*;
import static com.kongqw.rockerlibrary.view.AngelConstants.*;

/**
 * 纵向 上下两个方向
 */
class Direction2VerticalStrategy implements IDealRockerMoveStrategy{
    @Override
    public void dealCallBackModeMove(RockerView rockerView, double angle) {
        if (judgmentIsInRange(angle, ANGLE_VERTICAL_2D_OF_0P, ANGLE_VERTICAL_2D_OF_1P)) { // 0度到-180度
            // 下
            rockerView.getOnShakeListener().direction(RockerView.Direction.DIRECTION_DOWN);
        } else if (judgmentIsInRange(angle, ANGLE_VERTICAL_2D_OF_1P, ANGLE_360)) {  // 180度到-360度
            // 上
            rockerView.getOnShakeListener().direction(RockerView.Direction.DIRECTION_UP);
        }
    }

    @Override
    public void dealCallBackModeStateChange(RockerView rockerView, double angle) {
        if ((judgmentIsInRange(angle, ANGLE_VERTICAL_2D_OF_0P, ANGLE_VERTICAL_2D_OF_1P)) && rockerView.getTempDirection() != RockerView.Direction.DIRECTION_DOWN) {  // 0度到-180度 并且当前不是下方向
            // 下
            rockerView.setTempDirection(RockerView.Direction.DIRECTION_DOWN);
            rockerView.getOnShakeListener().direction(RockerView.Direction.DIRECTION_DOWN);
        } else if (judgmentIsInRange(angle, ANGLE_VERTICAL_2D_OF_1P, ANGLE_360) && rockerView.getTempDirection() != RockerView.Direction.DIRECTION_UP) { // 180度到-360度 并且当前不是上方向
            // 上
            rockerView.setTempDirection(RockerView.Direction.DIRECTION_UP);
            rockerView.getOnShakeListener().direction(RockerView.Direction.DIRECTION_UP);
        }
    }
}
