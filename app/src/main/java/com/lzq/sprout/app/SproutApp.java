package com.lzq.sprout.app;

import android.app.Application;
import android.content.Context;

import com.lzq.sprout.utils.Log;

public class SproutApp extends Application {
    public static final Log.Tag TAG = new Log.Tag("SproutApp");
    private static Context sApplicationContext = null;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplicationContext = getApplicationContext();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    public static Context getMyApplicationContext() {
        return sApplicationContext;
    }
}
