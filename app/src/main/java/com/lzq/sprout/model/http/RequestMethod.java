package com.lzq.sprout.model.http;

import com.lzq.sprout.model.bean.BaseBean;
import com.lzq.sprout.model.bean.LoginInfo;

public class RequestMethod<T> {

    private RetrofitInterface mRetrofitInterface;

    public RequestMethod() {
        //此处也可以用注解来做，
        this.mRetrofitInterface = RetrofitManager.getRetrofitManager().getServer(RetrofitInterface.class);
    }

    public void loginRequest(String userName, String password, String type, HttpSubscriber<T> subscriber){
        mRetrofitInterface.login(userName, password, type)
                .compose(RxHelper.<LoginInfo>handleResult())
                .subscribe(subscriber);
    }

    public void loginRequestWithBaseBean(String userName, String password, String type, HttpSubscriber<BaseBean<T>> subscriber){
        mRetrofitInterface.login(userName, password, type)
                .compose(RxHelper.<BaseBean<LoginInfo>>schedulersThread())
                .subscribe(subscriber);
    }
}
