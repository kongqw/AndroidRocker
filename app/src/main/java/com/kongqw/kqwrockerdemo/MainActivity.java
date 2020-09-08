package com.kongqw.kqwrockerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.kongqw.rockerlibrary.view.RockerView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView mLogLeft;
    private TextView mLogRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLogLeft = (TextView) findViewById(R.id.log_left);
        mLogRight = (TextView) findViewById(R.id.log_right);

        //初始化
        RockerView rockerViewLeft = (RockerView) findViewById(R.id.rockerView_left);
        if (rockerViewLeft != null) {
            //设置回调模式
            rockerViewLeft.setCallBackMode(RockerView.CallBackMode.CALL_BACK_MODE_STATE_CHANGE);
            // 监听摇动方向
            rockerViewLeft.setOnShakeListener(RockerView.DirectionMode.DIRECTION_4_ROTATE_45, new RockerView.OnShakeListener() {
                @Override
                public void onStart() {
                    mLogLeft.setText(null);
                }

                @Override
                public void direction(RockerView.Direction direction) {
                    mLogLeft.setText("摇动方向 : " + getDirection(direction));
                    Log.d(TAG,"摇动方向 : " + getDirection(direction));
                }

                @Override
                public void onFinish() {
                    mLogLeft.setText(null);
                }
            });
        }

        RockerView rockerViewRight = (RockerView) findViewById(R.id.rockerView_right);
        if (rockerViewRight != null) {
            // 监听摇动角度
            rockerViewRight.setOnAngleChangeListener(new RockerView.OnAngleChangeListener() {
                @Override
                public void onStart() {
                    mLogRight.setText(null);
                }

                @Override
                public void angle(double angle) {
                    mLogRight.setText("摇动角度 : " + angle);
                    Log.d(TAG,"摇动角度 : " + angle);
                }

                @Override
                public void onFinish() {
                    mLogRight.setText(null);
                }
            });
        }
    }

    private String getDirection(RockerView.Direction direction) {
        String message = null;
        switch (direction) {
            case DIRECTION_LEFT:
                message = "左";
                break;
            case DIRECTION_RIGHT:
                message = "右";
                break;
            case DIRECTION_UP:
                message = "上";
                break;
            case DIRECTION_DOWN:
                message = "下";
                break;
            case DIRECTION_UP_LEFT:
                message = "左上";
                break;
            case DIRECTION_UP_RIGHT:
                message = "右上";
                break;
            case DIRECTION_DOWN_LEFT:
                message = "左下";
                break;
            case DIRECTION_DOWN_RIGHT:
                message = "右下";
                break;
            default:
                break;
        }
        return message;
    }
}
