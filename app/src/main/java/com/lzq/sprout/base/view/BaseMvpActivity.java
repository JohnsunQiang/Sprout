package com.lzq.sprout.base.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lzq.sprout.app.SproutApp;
import com.lzq.sprout.base.presenter.BaseMvpPresenter;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseMvpActivity<V extends IBaseMvpView, P extends BaseMvpPresenter<V>> extends RxAppCompatActivity {

    protected Context mAppContext;
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        mAppContext = SproutApp.getMyApplicationContext();
        mPresenter = createPresenter();
        if (null != mPresenter) {
            mPresenter.setLifecycleProvider(this);
        }
        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mPresenter) {
            mPresenter.onDestroyPersenter();
            mPresenter = null;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    protected abstract int getLayoutId();

    protected abstract void initViews();

    protected abstract P createPresenter();

    protected boolean isActivityActive() {
        return !isFinishing() || !isDestroyed();
    }
}
