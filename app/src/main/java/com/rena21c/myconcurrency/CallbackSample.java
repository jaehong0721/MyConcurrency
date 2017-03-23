package com.rena21c.myconcurrency;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.CountDownLatch;

/**
 * Created by jaehong on 2017. 3. 23..
 */
public class CallbackSample {
    public static void call(final int delay, final OnFinishListener finish) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                // 시간 걸리는 작업
                ThreadUtil.sleep(delay);

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        finish.onFinish();
                    }
                });
            }
        }).start();
    }

    public static void blockingCall(final int delay, final OnFinishListener listener) {
        final CountDownLatch latch = new CountDownLatch(1);
        call(delay, new OnFinishListener() {
            @Override
            public void onFinish() {
                latch.countDown();
                listener.onFinish();
            }
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
        }
    }

}
