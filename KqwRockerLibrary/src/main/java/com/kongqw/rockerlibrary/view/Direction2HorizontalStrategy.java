package com.kongqw.rockerlibrary.view;

import static com.kongqw.rockerlibrary.view.JudgmentUtil.*;
import static com.kongqw.rockerlibrary.view.AngelConstants.*;

class Direction2HorizontalStrategy implements IDealRockerMoveStrategy {
    @Override
    public void dealCallBackModeMove(RockerView rockerView, double angle) {
        if (multiConditionJudgmentIsInRange(angle, ANGLE_HORIZONTAL_2D_OF_0P, ANGLE_HORIZONTAL_2D_OF_1P)) {  //0-90度和270度-360度
            // 右
            rockerView.getOnShakeListener().direction(RockerView.Direction.DIRECTION_RIGHT);
        } else if (JudgmentUtil.judgmentIsInRange(angle, ANGLE_HORIZONTAL_2D_OF_0P, ANGLE_HORIZONTAL_2D_OF_1P)) {   //90度-270度
            // 左
            rockerView.getOnShakeListener().direction(RockerView.Direction.DIRECTION_LEFT);
        }
    }

    @Override
    public void dealCallBackModeStateChange(RockerView rockerView, double angle) {
        if ((multiConditionJudgmentIsInRange(angle, ANGLE_HORIZONTAL_2D_OF_0P, ANGLE_HORIZONTAL_2D_OF_1P)) && rockerView.getTempDirection() != RockerView.Direction.DIRECTION_RIGHT) {  //0-90度和270度-360度 并且当前不是右边方向
            // 右
            rockerView.setTempDirection(RockerView.Direction.DIRECTION_RIGHT);
            rockerView.getOnShakeListener().direction(RockerView.Direction.DIRECTION_RIGHT);
        } else if (judgmentIsInRange(angle, ANGLE_HORIZONTAL_2D_OF_0P, ANGLE_HORIZONTAL_2D_OF_1P) && rockerView.getTempDirection() != RockerView.Direction.DIRECTION_LEFT) { //90度-270度 并且当前不是左边方向
            // 左
            rockerView.setTempDirection(RockerView.Direction.DIRECTION_LEFT);
            rockerView.getOnShakeListener().direction(RockerView.Direction.DIRECTION_LEFT);
        }
    }
}
