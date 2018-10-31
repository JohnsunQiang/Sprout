package com.lzq.sprout.app;

import android.app.Application;
import android.content.Context;

import com.kingja.loadsir.core.LoadSir;
import com.lzq.sprout.commontips.CustomCallback;
import com.lzq.sprout.commontips.EmptyCallback;
import com.lzq.sprout.commontips.ErrorCallback;
import com.lzq.sprout.commontips.LoadingCallback;
import com.lzq.sprout.commontips.TimeoutCallback;
import com.lzq.sprout.utils.Log;

public class SproutApp extends Application {
    public static final Log.Tag TAG = new Log.Tag("SproutApp");
    private static Context sApplicationContext = null;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplicationContext = getApplicationContext();

        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    public static Context getMyApplicationContext() {
        return sApplicationContext;
    }
}
