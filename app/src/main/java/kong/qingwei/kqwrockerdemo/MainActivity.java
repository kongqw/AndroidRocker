package kong.qingwei.kqwrockerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private RockerView rockerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rockerView = (RockerView) findViewById(R.id.rockerView);

    }

    public void move(View view) {
//        rockerView.drawRocker(-110, -200);
    }
}
