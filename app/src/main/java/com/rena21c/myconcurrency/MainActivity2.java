package com.rena21c.myconcurrency;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Queue;

public class MainActivity2 extends AppCompatActivity {

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

        //동시에 수행됨
        for(Runnable runnable : queue) {
            Thread t = new Thread(runnable);
            t.start();
        }

        //순차적으로 실행
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(Runnable runnable : queue) {
                    runnable.run();
                }
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
