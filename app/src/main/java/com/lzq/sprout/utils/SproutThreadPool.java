package com.lzq.sprout.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SproutThreadPool {
    private static volatile Executor sPool;
    private static Handler sUiHandler = new Handler(Looper.getMainLooper());

    private static Executor getExecutor() {
        if (sPool != null) {
            return sPool;
        }

        synchronized (SproutThreadPool.class) {
            if (sPool == null) {
                sPool = Executors.newCachedThreadPool();
            }
        }

        return sPool;
    }

    @Deprecated
    public static void poolExecute(Runnable runnable) {
        getExecutor().execute(runnable);
    }


    public static void runOnUiThread(Runnable runnable) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            runnable.run();
        } else {
            sUiHandler.post(runnable);
        }
    }
}
