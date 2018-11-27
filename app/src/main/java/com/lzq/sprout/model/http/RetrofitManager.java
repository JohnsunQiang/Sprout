package com.lzq.sprout.model.http;

import com.google.gson.GsonBuilder;
import com.lzq.sprout.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private static RetrofitManager sRetrofitManager = null;
    private static Retrofit sRetrofit = null;

    public static RetrofitManager getRetrofitManager() {
        if (sRetrofitManager == null) {
            synchronized (RetrofitManager.class) {
                if (null == sRetrofitManager) {
                    sRetrofitManager = new RetrofitManager();
                }
            }
        }
        return sRetrofitManager;
    }

    private RetrofitManager() {
        resetRetrofit();
    }

    private void resetRetrofit() {
        if (null == sRetrofit) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(Constants.RequestApis.BASE_URL)
                    .client(OkHttpManager.getOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
    }

    public RetrofitInterface getServer() {
        return sRetrofit.create(RetrofitInterface.class);
    }

    public <T> T getServer(Class<T> server) {
        return sRetrofit.create(server);
    }
}
