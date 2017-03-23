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

        new Thread(new Runnable() {
            @Override
            public void run() {
                blocking();
            }
        }).start();

        tvOutput.append("end3");
    }

    private void blocking() {

        BlockingCallbackSample.blockingCall(3000, new CustomOnFinishListener() {
            @Override
            public void onFinish2() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        appendTextOnUiThread("End1");
                    }
                });
            }
        });

        BlockingCallbackSample.blockingCall(3000, new CustomOnFinishListener() {
            @Override
            public void onFinish2() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        appendTextOnUiThread("End2");
                    }
                });
            }
        });

    }

    void appendTextOnUiThread(String str) {
        tvOutput.append(str + "\n");
    }
}
