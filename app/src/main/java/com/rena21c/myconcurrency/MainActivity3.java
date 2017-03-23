package com.rena21c.myconcurrency;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Queue;

public class MainActivity3 extends AppCompatActivity {

    TextView tvOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Queue<Runnable> queue = new LinkedList<>();
        tvOutput = (TextView) findViewById(R.id.tvOutput);
        appendTextOnUiThread(getClass().getSimpleName() + " >>>>>>>>>>>>");

        queue.add(new Runnable() {
            @Override
            public void run() {
                ThreadUtil.sleep(1000);
                appendTextOnUiThread("스레드 1 결과뿅");
            }
        });

        queue.add(new Runnable() {
            @Override
            public void run() {
                ThreadUtil.sleep(1000);
                appendTextOnUiThread("스레드 2 결과뿅");
            }
        });

        queue.add(new Runnable() {
            @Override
            public void run() {
                ThreadUtil.sleep(1000);
                appendTextOnUiThread("스레드 3 결과뿅");
            }
        });


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
