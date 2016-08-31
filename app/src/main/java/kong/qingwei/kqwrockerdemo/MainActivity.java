package kong.qingwei.kqwrockerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import kong.qingwei.rockerlibrary.RockerView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RockerView rockerView1;
    private RockerView rockerView2;
    private RockerView rockerView3;
    private RockerView rockerView4;
    private RockerView rockerView5;
    private RockerView rockerView6;
    private TextView log1;
    private TextView log2;
    private TextView log3;
    private TextView log4;
    private TextView log5;
    private TextView log6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        log1 = (TextView) findViewById(R.id.log1);
//        log2 = (TextView) findViewById(R.id.log2);
//        log3 = (TextView) findViewById(R.id.log3);
//        log4 = (TextView) findViewById(R.id.log4);
//        log5 = (TextView) findViewById(R.id.log5);
//        log6 = (TextView) findViewById(R.id.log6);


        rockerView1 = (RockerView) findViewById(R.id.rockerView1);
        rockerView1.setOnAngleChangeListener(new RockerView.OnAngleChangeListener() {
            @Override
            public void onStart() {
                Log.i(TAG, "onStart: ");
                log1.setText(null);
            }

            @Override
            public void angle(double angle) {
                Log.i(TAG, "angle: " + angle);
                log1.setText("摇动角度:" + angle);
            }

            @Override
            public void onFinish() {
                Log.i(TAG, "onFinish: ");
                log1.setText(null);
            }
        });
//
//        rockerView2 = (RockerView) findViewById(R.id.rockerView2);
//        // 设置回调模式（默认就是CALL_BACK_MODE_MOVE）
//        rockerView2.setCallBackMode(RockerView.CallBackMode.CALL_BACK_MODE_STATE_CHANGE);
//        // 添加左右两个方向的监听
//        rockerView2.setOnShakeListener(RockerView.DirectionMode.DIRECTION_2_HORIZONTAL, new RockerView.OnShakeListener() {
//            @Override
//            public void onStart() {
//                Log.i(TAG, "onStart: ");
//                log2.setText(null);
//            }
//
//            @Override
//            public void direction(RockerView.Direction direction) {
//                Log.i(TAG, "direction: " + direction);
//                log2.setText(getDirection(direction));
//            }
//
//            @Override
//            public void onFinish() {
//                Log.i(TAG, "onFinish: ");
//                log2.setText(null);
//            }
//        });
//
//        rockerView3 = (RockerView) findViewById(R.id.rockerView3);
//        // 设置回调模式（默认就是CALL_BACK_MODE_MOVE）
//        rockerView3.setCallBackMode(RockerView.CallBackMode.CALL_BACK_MODE_STATE_CHANGE);
//        // 添加上下两个方向的监听
//        rockerView3.setOnShakeListener(RockerView.DirectionMode.DIRECTION_2_VERTICAL, new RockerView.OnShakeListener() {
//            @Override
//            public void onStart() {
//                Log.i(TAG, "onStart: ");
//                log3.setText(null);
//            }
//
//            @Override
//            public void direction(RockerView.Direction direction) {
//                Log.i(TAG, "direction: " + direction);
//                log3.setText(getDirection(direction));
//            }
//
//            @Override
//            public void onFinish() {
//                Log.i(TAG, "onFinish: ");
//                log3.setText(null);
//            }
//        });
//
//        rockerView4 = (RockerView) findViewById(R.id.rockerView4);
//        // 设置回调模式（默认就是CALL_BACK_MODE_MOVE）
//        rockerView4.setCallBackMode(RockerView.CallBackMode.CALL_BACK_MODE_STATE_CHANGE );
//        // 添加四个方向的监听
//        rockerView4.setOnShakeListener(RockerView.DirectionMode.DIRECTION_4_ROTATE_0, new RockerView.OnShakeListener() {
//            @Override
//            public void onStart() {
//                Log.i(TAG, "onStart: ");
//                log4.setText(null);
//            }
//
//            @Override
//            public void direction(RockerView.Direction direction) {
//                Log.i(TAG, "direction: " + direction);
//                log4.setText(getDirection(direction));
//            }
//
//            @Override
//            public void onFinish() {
//                Log.i(TAG, "onFinish: ");
//                log4.setText(null);
//            }
//        });
//
//        rockerView5 = (RockerView) findViewById(R.id.rockerView5);
//        // 设置回调模式（默认就是CALL_BACK_MODE_MOVE）
//        rockerView5.setCallBackMode(RockerView.CallBackMode.CALL_BACK_MODE_MOVE);
//        // 添加四个方向的监听
//        rockerView5.setOnShakeListener(RockerView.DirectionMode.DIRECTION_4_ROTATE_45, new RockerView.OnShakeListener() {
//            @Override
//            public void onStart() {
//                Log.i(TAG, "onStart: ");
//                log5.setText(null);
//            }
//
//            @Override
//            public void direction(RockerView.Direction direction) {
//                Log.i(TAG, "direction: " + direction);
//                log5.setText(getDirection(direction));
//            }
//
//            @Override
//            public void onFinish() {
//                Log.i(TAG, "onFinish: ");
//                log5.setText(null);
//            }
//        });
//
//        rockerView6 = (RockerView) findViewById(R.id.rockerView6);
//        // 设置回调模式（默认就是CALL_BACK_MODE_MOVE）
//        rockerView6.setCallBackMode(RockerView.CallBackMode.CALL_BACK_MODE_MOVE);
//        // 添加四个方向的监听
//        rockerView6.setOnShakeListener(RockerView.DirectionMode.DIRECTION_8, new RockerView.OnShakeListener() {
//            @Override
//            public void onStart() {
//                Log.i(TAG, "onStart: ");
//                log6.setText(null);
//            }
//
//            @Override
//            public void direction(RockerView.Direction direction) {
//                Log.i(TAG, "direction: " + direction);
//                log6.setText(getDirection(direction));
//            }
//
//            @Override
//            public void onFinish() {
//                Log.i(TAG, "onFinish: ");
//                log6.setText(null);
//            }
//        });

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
