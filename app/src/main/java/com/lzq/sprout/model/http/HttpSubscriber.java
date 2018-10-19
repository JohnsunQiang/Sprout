package com.lzq.sprout.model.http;

import android.app.ProgressDialog;
import android.content.Context;

import com.lzq.sprout.utils.NetworkUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

public abstract class HttpSubscriber<T> extends Subscriber<T> {

    public HttpSubscriber() {
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!NetworkUtils.isNetworkConnected()) {
            onError("网络不可用");
            onFinish();
            if (!isUnsubscribed()) {
                unsubscribe();
            }
        } else {
            onStartCallBack();
        }
//        else {
//            if (progressDialog == null && isShowDialog) {
//                progressDialog = new ProgressDialog(context);
//                progressDialog.setMessage("正在加载...");
//                progressDialog.show();
//            }
//        }
    }

    @Override
    public void onCompleted() {
        onFinish();
        if (!isUnsubscribed()) {
            unsubscribe();
        }
//        if (progressDialog != null && isShowDialog) {
//            progressDialog.dismiss();
//            progressDialog = null;
//        }
    }

    @Override
    public void onError(Throwable e) {
        onFinish();
        if (!NetworkUtils.isNetworkConnected()) {
            onError("网络不可用");
        } else if (e instanceof SocketTimeoutException) {
            onError("服务器响应超时");
        } else if (e instanceof ConnectException) {
            onError("服务器请求超时");
        } else if (e instanceof HttpException) {
            onError("服务器异常");
        } else {
            onError("未知异常："+e.getMessage());
        }
//        if (progressDialog != null && isShowDialog) {
//            progressDialog.dismiss();
//            progressDialog = null;
//        }
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    public abstract void onStartCallBack();

    public abstract void onSuccess(T t);

    public abstract void onError(String msg);

    public abstract void onFinish();
}
