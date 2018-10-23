package com.lzq.sprout.base.proxy;

import android.os.Bundle;

import com.lzq.sprout.base.factory.IPresenterMvpFactory;
import com.lzq.sprout.base.presenter.BaseMvpPresenter;
import com.lzq.sprout.base.view.IBaseMvpView;
import com.lzq.sprout.utils.Constants;

public class PresenterProxyImpl<V extends IBaseMvpView, P extends BaseMvpPresenter<V>> implements IPresenterProxy<V, P> {
    private IPresenterMvpFactory<V, P> mFactory;
    private P mPresenter;
    private Bundle mBundle;
    private boolean mIsAttchView;

    public PresenterProxyImpl(IPresenterMvpFactory<V, P> presenterMvpFactory) {
        this.mFactory = presenterMvpFactory;
    }

    @Override
    public void setPresenterFactory(IPresenterMvpFactory<V, P> presenterFactory) {
        if (mPresenter != null) {
            throw new IllegalArgumentException("this method should call before getMvpPresenter(), we never modify it while it not null!");
        }
        this.mFactory = presenterFactory;
    }

    @Override
    public IPresenterMvpFactory<V, P> getPresenterFactory() {
        return mFactory;
    }

    @Override
    public P getMvpPresenter() {
        if (mFactory != null) {
            if (mPresenter == null) {
                mPresenter = mFactory.createMvpPresenter();
                mPresenter.onCreatePersenter(mBundle == null ? null : mBundle.getBundle(Constants.Presenter.PRESENTER_SAVE_BUNDLE));
            }
        }
        return mPresenter;
    }

    public void onResume(V mvpView) {
        getMvpPresenter();
        if (mPresenter != null && !mIsAttchView) {
            mPresenter.onMvpAttachView(mvpView);
            mIsAttchView = true;
        }
    }

    private void onDetachMvpView() {
        if (mPresenter != null && mIsAttchView) {
            mPresenter.onMvpDetachView();
            mIsAttchView = false;
        }
    }

    public void onDestroy() {
        if (mPresenter != null) {
            onDetachMvpView();
            mPresenter.onDestroyPersenter();
            mPresenter = null;
        }
    }

    public Bundle onSaveInstanceState() {
        Bundle bundle = new Bundle();
        getMvpPresenter();
        if (mPresenter != null) {
            Bundle presenterBundle = new Bundle();
            mPresenter.onMvpSaveInstanceState(presenterBundle);
            bundle.putBundle(Constants.Presenter.PRESENTER_SAVE_BUNDLE, presenterBundle);
        }
        return bundle;
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        mBundle = savedInstanceState;

    }
}
