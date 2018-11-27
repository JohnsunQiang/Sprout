package com.lzq.sprout.model.http;

import com.lzq.sprout.app.SproutApp;
import com.lzq.sprout.model.http.interceptor.CacheInterceptor;
import com.lzq.sprout.model.http.interceptor.RequestInterceptor;
import com.lzq.sprout.model.http.interceptor.ResponseInterceptor;
import com.lzq.sprout.utils.Constants;
import com.lzq.sprout.utils.Log;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpManager {
    private static final Log.Tag TAG = new Log.Tag("OkHttpManager");
    private static final long DEFAULT_CONNECT_TIMEOUT = 30l;
    private static final long DEFAULT_WRITE_TIMEOUT = 30l;
    private static final long DEFAULT_READ_TIMEOUT = 30l;
    private static OkHttpClient mOkHttpClient;

    //cache directory
    private static final File cacheDirectory =
            new File(SproutApp.getMyApplicationContext().getCacheDir().getAbsolutePath(), Constants.PACKAGE_NAME);

    private static Cache cache = new Cache(cacheDirectory, 20 * 1024 * 1024);

    public static OkHttpClient getOkHttpClient() {
        if (null == mOkHttpClient) {
            synchronized (OkHttpManager.class) {
                if (null == mOkHttpClient) {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    // add cache and interceptor
                    builder.cache(cache).addInterceptor(new CacheInterceptor());
                    builder.addInterceptor(new RequestInterceptor());
                    builder.addInterceptor(new ResponseInterceptor());
                    builder.addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                                @Override
                                public void log(String message) {
                                    Log.d(TAG, message);
                                }
                            }).setLevel(HttpLoggingInterceptor.Level.BODY));
                    // add time out info
                    builder.connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS);
                    builder.readTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS);
                    builder.writeTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS);

                    builder.retryOnConnectionFailure(true);
                    builder.cookieJar(CookieJar.NO_COOKIES);
                    mOkHttpClient = builder.build();
                }
            }
        }
        return mOkHttpClient;
    }
}
