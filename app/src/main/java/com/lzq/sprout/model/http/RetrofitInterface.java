package com.lzq.sprout.model.http;

import com.lzq.sprout.model.bean.BaseBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface RetrofitInterface<T> {

    @FormUrlEncoded
    @POST("login/u.php")
    Observable<BaseBean<T>> login(@Field("username") String username,
                                          @Field("password") String password,
                                          @Field("app") String type);
}
