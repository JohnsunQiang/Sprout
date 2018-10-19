package com.lzq.sprout.base;

public interface Callback<T> {
    void onSuccess(T data);

    void onFailure(String msg);

    void onComplete();
}