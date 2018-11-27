package com.lzq.sprout.model;

import com.lzq.sprout.model.bean.BaseBean;
import com.lzq.sprout.model.bean.Meizi;
import com.lzq.sprout.model.http.HttpSubscriber;
import com.lzq.sprout.model.http.RequestMethod;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

public class HomeModel {
    public void getMeizi(HttpSubscriber<BaseBean<List<Meizi>>> subscriber, LifecycleProvider provider) {
        RequestMethod requestMethod = new RequestMethod();
        requestMethod.getMeizi(subscriber, provider);
    }
}
