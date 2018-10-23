package com.lzq.sprout.base.factory;

import com.lzq.sprout.base.presenter.BaseMvpPresenter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CreatePresenter {
    Class<? extends BaseMvpPresenter> value();
}
