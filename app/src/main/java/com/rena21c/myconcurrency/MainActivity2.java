package com.rena21c.myconcurrency;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.LinkedList;

public class MainActivity2 extends AppCompatActivity {

    TextView tvOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinkedList<Runnable> queue = new LinkedList<>();
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
        for(int i=0; i<queue.size(); i++) {
            Runnable runnable = queue.get(i);
            Thread t = new Thread(runnable);
            t.start();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!queue.isEmpty()) {
                    queue.poll().run();
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
