package com.kongqw.rockerlibrary.view.strategy;

import com.kongqw.rockerlibrary.view.AngelConstants;
import com.kongqw.rockerlibrary.view.Direction;
import com.kongqw.rockerlibrary.view.RockerView;

import static com.kongqw.rockerlibrary.view.AngelConstants.*;
import static com.kongqw.rockerlibrary.view.JudgmentUtil.*;

/**
 * 纵向 上下两个方向
 */
public class Direction4Rotate45Strategy implements IDealRockerMoveStrategy{
    @Override
    public void dealCallBackModeMove(RockerView rockerView, double angle) {
        if (multiConditionJudgmentIsInRange(angle, ANGLE_ROTATE45_4D_OF_0P, ANGLE_ROTATE45_4D_OF_3P)) { //0-45度和315度-360度
            // 右
            rockerView.getOnShakeListener().direction(Direction.DIRECTION_RIGHT);
        } else if (judgmentIsInRange(angle, ANGLE_ROTATE45_4D_OF_0P, AngelConstants.ANGLE_ROTATE45_4D_OF_1P)) {   //45度-135度
            // 下
            rockerView.getOnShakeListener().direction(Direction.DIRECTION_DOWN);
        } else if (judgmentIsInRange(angle, AngelConstants.ANGLE_ROTATE45_4D_OF_1P, ANGLE_ROTATE45_4D_OF_2P)) { //135度-225度
            // 左
            rockerView.getOnShakeListener().direction(Direction.DIRECTION_LEFT);
        } else if (judgmentIsInRange(angle, ANGLE_ROTATE45_4D_OF_2P, ANGLE_ROTATE45_4D_OF_3P)) { //225度-315度
            // 上
            rockerView.getOnShakeListener().direction(Direction.DIRECTION_UP);
        }
    }

    @Override
    public void dealCallBackModeStateChange(RockerView rockerView, double angle) {
        if ((multiConditionJudgmentIsInRange(angle, ANGLE_ROTATE45_4D_OF_0P, ANGLE_ROTATE45_4D_OF_3P)) && rockerView.getTempDirection() != Direction.DIRECTION_RIGHT) { //0-45度和315度-360度 并且当前不是右方向
            // 右
            rockerView.setTempDirection(Direction.DIRECTION_RIGHT);
            rockerView.getOnShakeListener().direction(Direction.DIRECTION_RIGHT);
        } else if (judgmentIsInRange(angle, ANGLE_ROTATE45_4D_OF_0P, ANGLE_ROTATE45_4D_OF_1P) && rockerView.getTempDirection() != Direction.DIRECTION_DOWN) { //45度-135度 并且当前不是下方向
            // 下
            rockerView.setTempDirection(Direction.DIRECTION_DOWN);
            rockerView.getOnShakeListener().direction(Direction.DIRECTION_DOWN);
        } else if (judgmentIsInRange(angle, ANGLE_ROTATE45_4D_OF_1P, ANGLE_ROTATE45_4D_OF_2P) && rockerView.getTempDirection() != Direction.DIRECTION_LEFT) { //135度-225度 并且当前不是左方向
            // 左
            rockerView.setTempDirection(Direction.DIRECTION_LEFT);
            rockerView.getOnShakeListener().direction(Direction.DIRECTION_LEFT);
        } else if (judgmentIsInRange(angle, ANGLE_ROTATE45_4D_OF_2P, ANGLE_ROTATE45_4D_OF_3P) && rockerView.getTempDirection() != Direction.DIRECTION_UP) { //225度-315度 并且当前不是上方向
            // 上
            rockerView.setTempDirection(Direction.DIRECTION_UP);
            rockerView.getOnShakeListener().direction(Direction.DIRECTION_UP);
        }
    }
}
