package com.lzq.sprout.model;

import android.text.TextUtils;

import com.lzq.sprout.base.model.BaseModel;
import com.lzq.sprout.model.bean.BaseBean;
import com.lzq.sprout.model.http.HttpSubscriber;
import com.lzq.sprout.utils.Constants;
import com.lzq.sprout.utils.Log;

public class MainDataModel<T> extends BaseModel<T> {
    public static final Log.Tag TAG = new Log.Tag("MainDataModel");

    @Override
    public void requestPostAPI(String url, HttpSubscriber<BaseBean<T>> subscriber) {
        if (null == mRequestMethod) {
            Log.w(TAG, "requestPostAPI fail, null mRequestMethod");
            return;
        }
        if (TextUtils.equals(url, Constants.RequestApis.LOGIN)) {
            login(subscriber);
        }
    }

    private void login(HttpSubscriber<BaseBean<T>> subscriber) {
        if (null == mParams) {
            Log.w(TAG, "login fail, null params");
            return;
        }
        mRequestMethod.loginRequestWithBaseBean(mParams[0], mParams[1], mParams[2], subscriber);
    }
}
