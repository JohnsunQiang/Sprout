package com.lzq.sprout.base;

import com.lzq.sprout.model.bean.BaseBean;
import com.lzq.sprout.model.http.HttpSubscriber;
import com.lzq.sprout.model.http.RequestMethod;

import javax.inject.Inject;

public abstract class BaseModel<T> {
    protected String[] mParams;
    @Inject
    protected RequestMethod mRequestMethod;

    public BaseModel setParams(String... args) {
        mParams = args;
        return this;
    }

    public abstract void requestGetAPI(String url);

    public abstract void requestPostAPI(String url, HttpSubscriber<BaseBean<T>> params);
}
