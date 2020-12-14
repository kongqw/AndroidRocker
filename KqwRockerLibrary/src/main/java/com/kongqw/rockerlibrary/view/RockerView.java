package com.kongqw.rockerlibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.kongqw.rockerlibrary.Logger;
import com.kongqw.rockerlibrary.R;
import com.kongqw.rockerlibrary.view.strategy.Direction2HorizontalStrategy;
import com.kongqw.rockerlibrary.view.strategy.Direction2VerticalStrategy;
import com.kongqw.rockerlibrary.view.strategy.Direction4Rotate0Strategy;
import com.kongqw.rockerlibrary.view.strategy.Direction4Rotate45Strategy;
import com.kongqw.rockerlibrary.view.strategy.Direction8Strategy;
import com.kongqw.rockerlibrary.view.strategy.IDealRockerMoveStrategy;

/**
 * Created by kqw on 2016/8/30.
 * 摇杆控件
 */
public class RockerView extends View {
    private static final String TAG = "RockerView";

    private static final int DEFAULT_SIZE = 400;
    private static final int DEFAULT_ROCKER_RADIUS = DEFAULT_SIZE / 8;

    private Paint mAreaBackgroundPaint;
    private Paint mRockerPaint;

    // 绘制 跟随手势移动的光环 的画笔
    private Paint mArcPaint;
    // 摇杆滑动的角度
    private double angle = 0;

    private Point mRockerPosition;
    private Point mCenterPoint;

    private int mAreaRadius;
    private int mRockerRadius;

    private CallBackMode mCallBackMode = CallBackMode.CALL_BACK_MODE_MOVE;
    private OnAngleChangeListener mOnAngleChangeListener;
    private OnShakeListener OnShakeListener;

    public OnShakeListener getOnShakeListener() {
        return OnShakeListener;
    }

    public void setOnShakeListener(OnShakeListener onShakeListener) {
        this.OnShakeListener = onShakeListener;
    }

    private IDealRockerMoveStrategy mDealRockerMoveStrategy;
    private DirectionMode mDirectionMode;
    private Direction tempDirection = Direction.DIRECTION_CENTER;

    public Direction getTempDirection() {
        return tempDirection;
    }

    public void setTempDirection(Direction tempDirection) {
        this.tempDirection = tempDirection;
    }

    // 摇杆可移动区域背景
    private static final int AREA_BACKGROUND_MODE_PIC = 0;
    private static final int AREA_BACKGROUND_MODE_COLOR = 1;
    private static final int AREA_BACKGROUND_MODE_XML = 2;
    private static final int AREA_BACKGROUND_MODE_DEFAULT = 3;
    private int mAreaBackgroundMode = AREA_BACKGROUND_MODE_DEFAULT;
    private Bitmap mAreaBitmap;
    private int mAreaColor;

    // 摇杆背景
    private static final int ROCKER_BACKGROUND_MODE_PIC = 4;
    private static final int ROCKER_BACKGROUND_MODE_COLOR = 5;
    private static final int ROCKER_BACKGROUND_MODE_XML = 6;
    private static final int ROCKER_BACKGROUND_MODE_DEFAULT = 7;
    private int mRockerBackgroundMode = ROCKER_BACKGROUND_MODE_DEFAULT;
    private Bitmap mRockerBitmap;
    private int mRockerColor;

    // 摇杆移动的时候，光环背景颜色
    private int mARCColor;
    // 摇杆移动的时候，光环背景宽度
    private int mARCStrokeWidth;

    public RockerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 获取自定义属性
        initAttribute(context, attrs);

        if (isInEditMode()) {
            Logger.i(TAG, "RockerView: isInEditMode");
        }

        // 移动区域画笔
        mAreaBackgroundPaint = new Paint();
        mAreaBackgroundPaint.setAntiAlias(true);

        // 摇杆画笔
        mRockerPaint = new Paint();
        mRockerPaint.setAntiAlias(true);

        //  绘制  跟随手势移动的光环 的画笔
        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);                       //使用抗锯齿功能
        mArcPaint.setColor(mARCColor);                      //设置画笔的颜色为绿色
        mArcPaint.setStyle(Paint.Style.STROKE);             //设置画笔类型为STROKE类型
        mArcPaint.setStrokeWidth(mARCStrokeWidth);          //设置画笔宽度

        // 中心点
        mCenterPoint = new Point();
        // 摇杆位置
        mRockerPosition = new Point();
    }

    /**
     * 获取属性
     *
     * @param context context
     * @param attrs   attrs
     */
    private void initAttribute(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RockerView);

        // 可移动区域背景
        Drawable areaBackground = typedArray.getDrawable(R.styleable.RockerView_areaBackground);
        if (null != areaBackground) {
            // 设置了背景
            if (areaBackground instanceof BitmapDrawable) {
                // 设置了一张图片
                mAreaBitmap = ((BitmapDrawable) areaBackground).getBitmap();
                mAreaBackgroundMode = AREA_BACKGROUND_MODE_PIC;
            } else if (areaBackground instanceof GradientDrawable) {
                // XML
                mAreaBitmap = drawable2Bitmap(areaBackground);
                mAreaBackgroundMode = AREA_BACKGROUND_MODE_XML;
            } else if (areaBackground instanceof ColorDrawable) {
                // 色值
                mAreaColor = ((ColorDrawable) areaBackground).getColor();
                mAreaBackgroundMode = AREA_BACKGROUND_MODE_COLOR;
            } else {
                // 其他形式
                mAreaBackgroundMode = AREA_BACKGROUND_MODE_DEFAULT;
            }
        } else {
            // 没有设置背景
            mAreaBackgroundMode = AREA_BACKGROUND_MODE_DEFAULT;
        }

        // 摇杆背景
        Drawable rockerBackground = typedArray.getDrawable(R.styleable.RockerView_rockerBackground);
        if (null != rockerBackground) {
            // 设置了摇杆背景
            if (rockerBackground instanceof BitmapDrawable) {
                // 图片
                mRockerBitmap = ((BitmapDrawable) rockerBackground).getBitmap();
                mRockerBackgroundMode = ROCKER_BACKGROUND_MODE_PIC;
            } else if (rockerBackground instanceof GradientDrawable) {
                // XML
                mRockerBitmap = drawable2Bitmap(rockerBackground);
                mRockerBackgroundMode = ROCKER_BACKGROUND_MODE_XML;
            } else if (rockerBackground instanceof ColorDrawable) {
                // 色值
                mRockerColor = ((ColorDrawable) rockerBackground).getColor();
                mRockerBackgroundMode = ROCKER_BACKGROUND_MODE_COLOR;
            } else {
                // 其他形式
                mRockerBackgroundMode = ROCKER_BACKGROUND_MODE_DEFAULT;
            }
        } else {
            // 没有设置摇杆背景
            mRockerBackgroundMode = ROCKER_BACKGROUND_MODE_DEFAULT;
        }

        // 摇杆移动的时候，光环背景颜色
        mARCColor = typedArray.getColor(R.styleable.RockerView_arcColor, Color.GREEN);
        // 摇杆移动的时候，光环背景的宽度
        mARCStrokeWidth = typedArray.getInt(R.styleable.RockerView_arcStrokeWidth, 10);

        // 摇杆半径
        mRockerRadius = typedArray.getDimensionPixelOffset(R.styleable.RockerView_rockerRadius, DEFAULT_ROCKER_RADIUS);

        Logger.i(TAG, "initAttribute: mAreaBackground = " + areaBackground + "   mRockerBackground = " + rockerBackground + "  mRockerRadius = " + mRockerRadius);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureWidth, measureHeight;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            // 具体的值和match_parent
            measureWidth = widthSize;
        } else {
            // wrap_content
            measureWidth = DEFAULT_SIZE;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            measureHeight = heightSize;
        } else {
            measureHeight = DEFAULT_SIZE;
        }
        Logger.i(TAG, "onMeasure: --------------------------------------");
        Logger.i(TAG, "onMeasure: widthMeasureSpec = " + widthMeasureSpec + " heightMeasureSpec = " + heightMeasureSpec);
        Logger.i(TAG, "onMeasure: widthMode = " + widthMode + "  measureWidth = " + widthSize);
        Logger.i(TAG, "onMeasure: heightMode = " + heightMode + "  measureHeight = " + widthSize);
        Logger.i(TAG, "onMeasure: measureWidth = " + measureWidth + " measureHeight = " + measureHeight);
        setMeasuredDimension(measureWidth, measureHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();

        int cx = measuredWidth / 2;
        int cy = measuredHeight / 2;
        // 中心点
        mCenterPoint.set(cx, cy);
        // 可移动区域的半径
        mAreaRadius = (measuredWidth <= measuredHeight) ? cx : cy;

        // 摇杆位置
        if (0 == mRockerPosition.x || 0 == mRockerPosition.y) {
            mRockerPosition.set(mCenterPoint.x, mCenterPoint.y);
        }

        // 画可移动区域
        if (AREA_BACKGROUND_MODE_PIC == mAreaBackgroundMode || AREA_BACKGROUND_MODE_XML == mAreaBackgroundMode) {
            // 图片
            Rect src = new Rect(0, 0, mAreaBitmap.getWidth(), mAreaBitmap.getHeight());
            Rect dst = new Rect(mCenterPoint.x - mAreaRadius, mCenterPoint.y - mAreaRadius, mCenterPoint.x + mAreaRadius, mCenterPoint.y + mAreaRadius);
            canvas.drawBitmap(mAreaBitmap, src, dst, mAreaBackgroundPaint);
        } else if (AREA_BACKGROUND_MODE_COLOR == mAreaBackgroundMode) {
            // 色值
            mAreaBackgroundPaint.setColor(mAreaColor);
            canvas.drawCircle(mCenterPoint.x, mCenterPoint.y, mAreaRadius, mAreaBackgroundPaint);
        } else {
            // 其他或者未设置
            mAreaBackgroundPaint.setColor(Color.GRAY);
            canvas.drawCircle(mCenterPoint.x, mCenterPoint.y, mAreaRadius, mAreaBackgroundPaint);
        }

        // 画摇杆背景
        if (ROCKER_BACKGROUND_MODE_PIC == mRockerBackgroundMode || ROCKER_BACKGROUND_MODE_XML == mRockerBackgroundMode) {
            // 图片
            Rect src = new Rect(0, 0, mRockerBitmap.getWidth(), mRockerBitmap.getHeight());
            Rect dst = new Rect(mRockerPosition.x - mRockerRadius, mRockerPosition.y - mRockerRadius, mRockerPosition.x + mRockerRadius, mRockerPosition.y + mRockerRadius);
            canvas.drawBitmap(mRockerBitmap, src, dst, mRockerPaint);
        } else if (ROCKER_BACKGROUND_MODE_COLOR == mRockerBackgroundMode) {
            // 色值
            mRockerPaint.setColor(mRockerColor);
            canvas.drawCircle(mRockerPosition.x, mRockerPosition.y, mRockerRadius, mRockerPaint);
        } else {
            // 其他或者未设置
            mRockerPaint.setColor(Color.RED);
            canvas.drawCircle(mRockerPosition.x, mRockerPosition.y, mRockerRadius, mRockerPaint);
        }

        // 绘制跟随手势移动的光环
        if (angle > 0) {
            RectF oval = new RectF(0, 0, getWidth(), getHeight());
            float mAngle = (float) angle;
            // 使移动的光环圆弧  紧跟着滑动滑动的角度，并且使角度在圆弧中间
            canvas.drawArc(oval, mAngle - 30, 60, false, mArcPaint);//绘制圆弧，不含圆心
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:// 按下
                // 回调 开始
                callBackStart();
            case MotionEvent.ACTION_MOVE:// 移动
                float moveX = event.getX();
                float moveY = event.getY();
                mRockerPosition = getRockerPositionPoint(mCenterPoint, new Point((int) moveX, (int) moveY), mAreaRadius, mRockerRadius);
                moveRocker(mRockerPosition.x, mRockerPosition.y);
                break;
            case MotionEvent.ACTION_UP:// 抬起
            case MotionEvent.ACTION_CANCEL:// 移出区域
                // 回调 结束
                callBackFinish();
                float upX = event.getX();
                float upY = event.getY();
                moveRocker(mCenterPoint.x, mCenterPoint.y);
                Logger.i(TAG, "onTouchEvent: 抬起位置 : x = " + upX + " y = " + upY);
                break;
        }
        return true;
    }

    /**
     * 获取摇杆实际要显示的位置（点）
     *
     * @param centerPoint  中心点
     * @param touchPoint   触摸点
     * @param regionRadius 摇杆可活动区域半径
     * @param rockerRadius 摇杆半径
     * @return 摇杆实际显示的位置（点）
     */
    private Point getRockerPositionPoint(Point centerPoint, Point touchPoint, float regionRadius, float rockerRadius) {
        // 两点在X轴的距离
        float lenX = (float) (touchPoint.x - centerPoint.x);
        // 两点在Y轴距离
        float lenY = (float) (touchPoint.y - centerPoint.y);
        // 两点距离
        float lenXY = (float) Math.sqrt((double) (lenX * lenX + lenY * lenY));
        // 计算弧度
        double radian = Math.acos(lenX / lenXY) * (touchPoint.y < centerPoint.y ? -1 : 1);
        // 计算角度
        angle = radian2Angle(radian);

        // 回调 返回参数
        callBack(angle);

        Logger.i(TAG, "getRockerPositionPoint: 角度 :" + angle);
        if (lenXY + rockerRadius <= regionRadius) { // 触摸位置在可活动范围内
            return touchPoint;
        } else { // 触摸位置在可活动范围以外
            // 计算要显示的位置
            int showPointX = (int) (centerPoint.x + (regionRadius - rockerRadius) * Math.cos(radian));
            int showPointY = (int) (centerPoint.y + (regionRadius - rockerRadius) * Math.sin(radian));
            return new Point(showPointX, showPointY);
        }
    }

    /**
     * 移动摇杆到指定位置
     *
     * @param x x坐标
     * @param y y坐标
     */
    private void moveRocker(float x, float y) {
        mRockerPosition.set((int) x, (int) y);
        Logger.i(TAG, "onTouchEvent: 移动位置 : x = " + mRockerPosition.x + " y = " + mRockerPosition.y);
        invalidate();
    }

    /**
     * 弧度转角度
     *
     * @param radian 弧度
     * @return 角度[0, 360)
     */
    private double radian2Angle(double radian) {
        double tmp = Math.round(radian / Math.PI * 180);
        return tmp >= 0 ? tmp : 360 + tmp;
    }

    /**
     * Drawable 转 Bitmap
     *
     * @param drawable Drawable
     * @return Bitmap
     */
    private Bitmap drawable2Bitmap(Drawable drawable) {
        // 取 drawable 的长宽
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        // 取 drawable 的颜色格式
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
        // 建立对应 bitmap
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * 回调
     * 开始
     */
    private void callBackStart() {
        tempDirection = Direction.DIRECTION_CENTER;
        if (null != mOnAngleChangeListener) {
            mOnAngleChangeListener.onStart();
        }
        if (null != OnShakeListener) {
            OnShakeListener.onStart();
        }
    }

    /**
     * 回调
     * 返回参数
     *
     * @param angle 摇动角度
     */
    private void callBack(double angle) {
        // 角度直接回调
        if (null != mOnAngleChangeListener) {
            mOnAngleChangeListener.angle(angle);
        }
        // 方向的还得做一系列的逻辑判断
        if (null != OnShakeListener) {
            if (CallBackMode.CALL_BACK_MODE_MOVE == mCallBackMode) {
                mDealRockerMoveStrategy.dealCallBackModeMove(this, angle);
            } else if (CallBackMode.CALL_BACK_MODE_STATE_CHANGE == mCallBackMode) {
                mDealRockerMoveStrategy.dealCallBackModeStateChange(this, angle);
            }
        }
    }


    /**
     * 回调
     * 结束
     */
    private void callBackFinish() {
        //结束的时候，滑动的角度要归零
        angle = 0;
        tempDirection = Direction.DIRECTION_CENTER;
        if (null != mOnAngleChangeListener) {
            mOnAngleChangeListener.onFinish();
        }
        if (null != OnShakeListener) {
            OnShakeListener.onFinish();
        }
    }



    /**
     * 设置回调模式
     *
     * @param mode 回调模式
     */
    public void setCallBackMode(CallBackMode mode) {
        mCallBackMode = mode;
    }

    /**
     * 添加摇杆摇动角度的监听
     *
     * @param listener 回调接口
     */
    public void setOnAngleChangeListener(OnAngleChangeListener listener) {
        mOnAngleChangeListener = listener;
    }

    /**
     * 添加摇动的监听
     *
     * @param directionMode 监听的方向
     * @param listener      回调
     */
    public void setOnShakeListener(DirectionMode directionMode, OnShakeListener listener) {
        mDirectionMode = directionMode;
        mDealRockerMoveStrategy = initStrategy(mDirectionMode);
        OnShakeListener = listener;
    }

    private IDealRockerMoveStrategy initStrategy(DirectionMode mDirectionMode) {
        IDealRockerMoveStrategy dealRockerMoveStrategy = new Direction2HorizontalStrategy();
        //根据不同的方向来生成不同的策略
        if (mDirectionMode == DirectionMode.DIRECTION_2_HORIZONTAL) {
            dealRockerMoveStrategy = new Direction2HorizontalStrategy();
        } else if (mDirectionMode == DirectionMode.DIRECTION_2_VERTICAL) {
            dealRockerMoveStrategy = new Direction2VerticalStrategy();
        } else if (mDirectionMode == DirectionMode.DIRECTION_4_ROTATE_0) {
            dealRockerMoveStrategy = new Direction4Rotate0Strategy();
        } else if (mDirectionMode == DirectionMode.DIRECTION_4_ROTATE_45) {
            dealRockerMoveStrategy = new Direction4Rotate45Strategy();
        } else if (mDirectionMode == DirectionMode.DIRECTION_8) {
            dealRockerMoveStrategy = new Direction8Strategy();
        }
        return dealRockerMoveStrategy;
    }
}
