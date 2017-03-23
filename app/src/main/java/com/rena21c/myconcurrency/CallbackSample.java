package com.rena21c.myconcurrency;

/**
 * Created by jaehong on 2017. 3. 23..
 */
public class CallbackSample {
    public static void call(final int delay, final OnFinishListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 시간 걸리는 작업
                ThreadUtil.sleep(delay);
                listener.onFinish();
            }
        }).start();
    }

}
