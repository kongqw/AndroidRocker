package com.kongqw.rockerlibrary.view.strategy;

import com.kongqw.rockerlibrary.view.Direction;
import com.kongqw.rockerlibrary.view.RockerView;

import static com.kongqw.rockerlibrary.view.AngelConstants.*;
import static com.kongqw.rockerlibrary.view.JudgmentUtil.judgmentIsInRange;
import static com.kongqw.rockerlibrary.view.JudgmentUtil.multiConditionJudgmentIsInRange;

/**
 * 纵向 上下两个方向
 */
public class Direction8Strategy implements IDealRockerMoveStrategy{
    @Override
    public void dealCallBackModeMove(RockerView rockerView, double angle) {
        if (multiConditionJudgmentIsInRange(angle, ANGLE_8D_OF_0P, ANGLE_8D_OF_7P)) {   //0-22.5度和337.5度-360度
            // 右
            rockerView.getOnShakeListener().direction(Direction.DIRECTION_RIGHT);
        } else if (judgmentIsInRange(angle, ANGLE_8D_OF_0P, ANGLE_8D_OF_1P)) { //22.5度 - 67.5度
            // 右下
            rockerView.getOnShakeListener().direction(Direction.DIRECTION_DOWN_RIGHT);
        } else if (judgmentIsInRange(angle, ANGLE_8D_OF_1P, ANGLE_8D_OF_2P)) {// 67.5度 - 112.5度
            // 下
            rockerView.getOnShakeListener().direction(Direction.DIRECTION_DOWN);
        } else if (judgmentIsInRange(angle, ANGLE_8D_OF_2P, ANGLE_8D_OF_3P)) {//  112.5度 - 157.5度
            // 左下
            rockerView.getOnShakeListener().direction(Direction.DIRECTION_DOWN_LEFT);
        } else if (judgmentIsInRange(angle, ANGLE_8D_OF_3P, ANGLE_8D_OF_4P)) {// 157.5度 -  202.5度
            // 左
            rockerView.getOnShakeListener().direction(Direction.DIRECTION_LEFT);
        } else if (judgmentIsInRange(angle, ANGLE_8D_OF_4P, ANGLE_8D_OF_5P)) {// 202.5度 -  247.5度
            // 左上
            rockerView.getOnShakeListener().direction(Direction.DIRECTION_UP_LEFT);
        } else if (judgmentIsInRange(angle, ANGLE_8D_OF_5P, ANGLE_8D_OF_6P)) {//  247.5度 - 292.5度
            // 上
            rockerView.getOnShakeListener().direction(Direction.DIRECTION_UP);
        } else if (judgmentIsInRange(angle, ANGLE_8D_OF_6P, ANGLE_8D_OF_7P)) {//  292.5度 - 337.5度
            // 右上
            rockerView.getOnShakeListener().direction(Direction.DIRECTION_UP_RIGHT);
        }
    }

    @Override
    public void dealCallBackModeStateChange(RockerView rockerView, double angle) {

        if ((multiConditionJudgmentIsInRange(angle, ANGLE_8D_OF_0P, ANGLE_8D_OF_7P)) && rockerView.getTempDirection()  != Direction.DIRECTION_RIGHT) {//0-22.5度和337.5度-360度 并且当前不是右方向
            // 右
            rockerView.setTempDirection(Direction.DIRECTION_RIGHT);
            rockerView.getOnShakeListener().direction(Direction.DIRECTION_RIGHT);

        } else if (judgmentIsInRange(angle, ANGLE_8D_OF_0P, ANGLE_8D_OF_1P) && rockerView.getTempDirection()  != Direction.DIRECTION_DOWN_RIGHT) { //22.5度 - 67.5度 并且当前不是右下方向
            // 右下
            rockerView.setTempDirection(Direction.DIRECTION_DOWN_RIGHT);
            rockerView.getOnShakeListener().direction(Direction.DIRECTION_DOWN_RIGHT);
        } else if (judgmentIsInRange(angle, ANGLE_8D_OF_1P, ANGLE_8D_OF_2P) && rockerView.getTempDirection()  != Direction.DIRECTION_DOWN) {// 67.5度 - 112.5度 并且当前不是下方向
            // 下
            rockerView.setTempDirection(Direction.DIRECTION_DOWN);
            rockerView.getOnShakeListener().direction(Direction.DIRECTION_DOWN);
        } else if (judgmentIsInRange(angle, ANGLE_8D_OF_2P, ANGLE_8D_OF_3P) && rockerView.getTempDirection()  != Direction.DIRECTION_DOWN_LEFT) {//  112.5度 - 157.5度 并且当前不是左下方向
            // 左下
            rockerView.setTempDirection(Direction.DIRECTION_DOWN_LEFT);
            rockerView.getOnShakeListener().direction(Direction.DIRECTION_DOWN_LEFT);
        } else if (judgmentIsInRange(angle, ANGLE_8D_OF_3P, ANGLE_8D_OF_4P) && rockerView.getTempDirection()  != Direction.DIRECTION_LEFT) {// 157.5度 -  202.5度 并且当前不是左方向
            // 左
            rockerView.setTempDirection(Direction.DIRECTION_LEFT);
            rockerView.getOnShakeListener().direction(Direction.DIRECTION_LEFT);
        } else if (judgmentIsInRange(angle, ANGLE_8D_OF_4P, ANGLE_8D_OF_5P) && rockerView.getTempDirection()  != Direction.DIRECTION_UP_LEFT) {// 202.5度 -  247.5度 并且当前不是左上方向
            // 左上
            rockerView.setTempDirection(Direction.DIRECTION_UP_LEFT);
            rockerView.getOnShakeListener().direction(Direction.DIRECTION_UP_LEFT);
        } else if (judgmentIsInRange(angle, ANGLE_8D_OF_5P, ANGLE_8D_OF_6P) && rockerView.getTempDirection()  != Direction.DIRECTION_UP) {//  247.5度 - 292.5度 并且当前不是上方向
            // 上
            rockerView.setTempDirection(Direction.DIRECTION_UP);
            rockerView.getOnShakeListener().direction(Direction.DIRECTION_UP);
        } else if (judgmentIsInRange(angle, ANGLE_8D_OF_6P, ANGLE_8D_OF_7P) && rockerView.getTempDirection()  != Direction.DIRECTION_UP_RIGHT) {//  292.5度 - 337.5度 并且当前不是右上方向
            // 右上
            rockerView.setTempDirection(Direction.DIRECTION_UP_RIGHT);
            rockerView.getOnShakeListener().direction(Direction.DIRECTION_UP_RIGHT);
        }

    }
}
