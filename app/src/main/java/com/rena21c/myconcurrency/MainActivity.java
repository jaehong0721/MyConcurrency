package com.rena21c.myconcurrency;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOutput = (TextView) findViewById(R.id.tvOutput);
        appendTextOnUiThread(">>>>>>>>>>>>");

        new Thread(new Runnable() {
            @Override
            public void run() {
                ThreadUtil.sleep(1000);
                appendTextOnUiThread("스레드 1 결과뿅");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                ThreadUtil.sleep(1000);
                appendTextOnUiThread("스레드 2 결과뿅");
            }
        }).start();
    }

    private void appendTextOnUiThread(final String s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvOutput.append(s + "\n");
            }
        });
    }
}
