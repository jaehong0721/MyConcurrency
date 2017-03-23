package com.rena21c.myconcurrency;

import java.util.concurrent.CountDownLatch;

/**
 * Created by jaehong on 2017. 3. 23..
 */

public class BlockingCallbackSample {

    public static void blockingCall(int duration, final CustomOnFinishListener listener) {
        final CountDownLatch latch = new CountDownLatch(1);
        CallbackSample.call(duration, new OnFinishListener() {
            @Override
            public void onFinish() {
                latch.countDown();
                listener.onFinish2();
            }
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
