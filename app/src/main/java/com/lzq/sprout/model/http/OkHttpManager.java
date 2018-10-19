package com.lzq.sprout.model.http;

import com.lzq.sprout.app.SproutApp;
import com.lzq.sprout.utils.Log;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpManager {
    private static final Log.Tag TAG = new Log.Tag("OkHttpUtil");
    private static OkHttpClient mOkHttpClient;

    //cache directory
    private static final File cacheDirectory = new File(SproutApp.getMyApplicationContext().getCacheDir().getAbsolutePath(), "httpCache");

    private static Cache cache = new Cache(cacheDirectory, 10 * 1024 * 1024);
    private static ResponseInterceptor responseInterceptor = new ResponseInterceptor();

    public static OkHttpClient getOkHttpClient() {
        if (null == mOkHttpClient) {
            synchronized (OkHttpManager.class) {
                if (null != mOkHttpClient) {
                    mOkHttpClient = new OkHttpClient.Builder()
                            .cookieJar(CookieJar.NO_COOKIES)
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .readTimeout(30, TimeUnit.SECONDS)
                            .writeTimeout(30, TimeUnit.SECONDS)
                            .addInterceptor(responseInterceptor)
                            .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                                @Override
                                public void log(String message) {
                                    Log.i(TAG, message);
                                }
                            }).setLevel(HttpLoggingInterceptor.Level.BODY))
                            .cache(cache)
                            .build();
                }
            }
        }
        return mOkHttpClient;
    }
}
