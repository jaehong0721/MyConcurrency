package com.rena21c.myconcurrency;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity4 extends AppCompatActivity {

    TextView tvOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOutput = (TextView) findViewById(R.id.tvOutput);
        tvOutput.setText("start" + "\n");

        CallbackSample.blockingCall(3000, new OnFinishListener() {
            @Override
            public void onFinish() {
                tvOutput.append("end1");
            }
        });

        CallbackSample.blockingCall(3000, new OnFinishListener() {
            @Override
            public void onFinish() {
                tvOutput.append("end2");
            }
        });
    }
}
