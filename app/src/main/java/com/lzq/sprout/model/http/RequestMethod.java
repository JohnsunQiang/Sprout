package com.lzq.sprout.model.http;

import com.lzq.sprout.model.bean.BaseBean;
import com.lzq.sprout.model.bean.Meizi;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

public class RequestMethod {

    private RetrofitInterface mRetrofitInterface;

    public RequestMethod() {
        //此处也可以用注解来做，
        this.mRetrofitInterface = RetrofitManager.getRetrofitManager().getServer();
    }

    public void getMeizi(HttpSubscriber<BaseBean<List<Meizi>>> subscriber, LifecycleProvider lifecycleProvider) {
        mRetrofitInterface.getMeizi()
                .compose(lifecycleProvider.<BaseBean<List<Meizi>>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getMeizi(DefaultObserver<BaseBean<List<Meizi>>> subscriber, LifecycleProvider lifecycleProvider) {
        mRetrofitInterface.getMeizi()
                .compose(lifecycleProvider.<BaseBean<List<Meizi>>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
