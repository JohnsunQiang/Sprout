package com.lzq.sprout.model.http;

import com.lzq.sprout.utils.NetworkUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.observers.DisposableObserver;

public abstract class HttpSubscriber<T> extends DisposableObserver<T> {

    @Override
    public void onStart() {
        super.onStart();
        if (!NetworkUtils.isNetworkConnected()) {
            onError("网络不可用");
            if (!isDisposed()) {
                this.dispose();
            }
        } else {
            onStartCallBack();
        }
    }

    @Override
    public void onError(Throwable e) {
        if (!NetworkUtils.isNetworkConnected()) {
            onError("网络不可用");
        } else if (e instanceof SocketTimeoutException) {
            onError("服务器响应超时");
        } else if (e instanceof ConnectException) {
            onError("服务器请求超时");
        } else {
            onError("未知异常："+e.getMessage());
        }
    }

    @Override
    public void onComplete() {
        
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    public abstract void onStartCallBack();

    public abstract void onSuccess(T t);

    public abstract void onError(String msg);
}
