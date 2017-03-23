package com.rena21c.myconcurrency;

/**
 * Created by jaehong on 2017. 3. 23..
 */

public class ThreadUtil {

    public static void sleep(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
