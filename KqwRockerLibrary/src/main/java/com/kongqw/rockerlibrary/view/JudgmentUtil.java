package com.kongqw.rockerlibrary.view;

import static com.kongqw.rockerlibrary.view.AngelConstants.ANGLE_0;
import static com.kongqw.rockerlibrary.view.AngelConstants.ANGLE_360;

public class JudgmentUtil {
    /**
     * 判断当前角度是否处于某多个区间
     * 判断targetAngle是否处于  0度-firstAngle度区间 以及 secondAngle度-360度 区间内
     *
     * @param targetAngle 当前角度
     * @param firstAngle  区间左边的角度
     * @param secondAngle 区间右边的角度
     * @return 是否当前角度是否处于某多个区间
     */
    public static boolean multiConditionJudgmentIsInRange(double targetAngle, double firstAngle, double secondAngle) {
        return judgmentIsInRange(targetAngle, ANGLE_0, firstAngle) || judgmentIsInRange(targetAngle, secondAngle, ANGLE_360);
    }

    /**
     * 判断当前角度是否处于某个区间
     *
     * @param targetAngle 当前角度
     * @param smallAngle  区间左边的角度
     * @param bigAngle    区间右边的角度
     * @return 是否当前角度是否处于某个区间
     */
    public static boolean judgmentIsInRange(double targetAngle, double smallAngle, double bigAngle) {
        return smallAngle <= targetAngle && bigAngle > targetAngle;
    }
}
