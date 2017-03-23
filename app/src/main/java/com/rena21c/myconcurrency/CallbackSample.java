package com.rena21c.myconcurrency;

import android.os.Handler;
import android.os.Looper;

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

}
