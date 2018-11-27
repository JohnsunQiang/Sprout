package com.lzq.sprout.model.http;

import com.lzq.sprout.model.bean.BaseBean;
import com.lzq.sprout.model.bean.LoginInfo;
import com.lzq.sprout.model.bean.Meizi;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @FormUrlEncoded
    @POST("login")
    Observable<BaseBean<LoginInfo>> login(@Field("username") String username,
                                          @Field("password") String password);

    @GET("福利/10/3")
    Observable<BaseBean<List<Meizi>>> getMeizi();

    /**
     * @param page
     * @param number
     * @return
     */
    @Headers("Cache-Control: public, max-age=100")//设置缓存 缓存时间为100s
    @GET("everySay/selectAll.do")
    Observable<BaseBean<List<Meizi>>> lookBack(@Query("page") int page, @Query("rows") int number);
}
