package com.kongqw.rockerlibrary.view.strategy;

import com.kongqw.rockerlibrary.view.Direction;
import com.kongqw.rockerlibrary.view.RockerView;

import static com.kongqw.rockerlibrary.view.JudgmentUtil.*;
import static com.kongqw.rockerlibrary.view.AngelConstants.*;

/**
 * 纵向 上下两个方向
 */
public class Direction2VerticalStrategy implements IDealRockerMoveStrategy{
    @Override
    public void dealCallBackModeMove(RockerView rockerView, double angle) {
        if (judgmentIsInRange(angle, ANGLE_VERTICAL_2D_OF_0P, ANGLE_VERTICAL_2D_OF_1P)) { // 0度到-180度
            // 下
            rockerView.getOnShakeListener().direction(Direction.DIRECTION_DOWN);
        } else if (judgmentIsInRange(angle, ANGLE_VERTICAL_2D_OF_1P, ANGLE_360)) {  // 180度到-360度
            // 上
            rockerView.getOnShakeListener().direction(Direction.DIRECTION_UP);
        }
    }

    @Override
    public void dealCallBackModeStateChange(RockerView rockerView, double angle) {
        if ((judgmentIsInRange(angle, ANGLE_VERTICAL_2D_OF_0P, ANGLE_VERTICAL_2D_OF_1P)) && rockerView.getTempDirection() != Direction.DIRECTION_DOWN) {  // 0度到-180度 并且当前不是下方向
            // 下
            rockerView.setTempDirection(Direction.DIRECTION_DOWN);
            rockerView.getOnShakeListener().direction(Direction.DIRECTION_DOWN);
        } else if (judgmentIsInRange(angle, ANGLE_VERTICAL_2D_OF_1P, ANGLE_360) && rockerView.getTempDirection() != Direction.DIRECTION_UP) { // 180度到-360度 并且当前不是上方向
            // 上
            rockerView.setTempDirection(Direction.DIRECTION_UP);
            rockerView.getOnShakeListener().direction(Direction.DIRECTION_UP);
        }
    }
}
