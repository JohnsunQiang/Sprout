package com.lzq.sprout.base.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lzq.sprout.base.factory.IPresenterMvpFactory;
import com.lzq.sprout.base.factory.PresenterMvpFactoryImpl;
import com.lzq.sprout.base.presenter.BaseMvpPresenter;
import com.lzq.sprout.base.proxy.IPresenterProxy;
import com.lzq.sprout.base.proxy.PresenterProxyImpl;
import com.lzq.sprout.utils.Constants;

public class BaseMvpFragment<V extends IBaseMvpView, P extends BaseMvpPresenter<V>> extends Fragment implements IPresenterProxy<V, P> {

    private PresenterProxyImpl<V,P> mProxy = new PresenterProxyImpl<>(PresenterMvpFactoryImpl.<V,P>createFactory(getClass()));

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (null != savedInstanceState) {
            mProxy.onRestoreInstanceState(savedInstanceState.getBundle(Constants.Presenter.PRESENTER_SAVE_BUNDLE));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mProxy.onResume((V) this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mProxy.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(Constants.Presenter.PRESENTER_SAVE_BUNDLE, mProxy.onSaveInstanceState());
    }

    @Override
    public void setPresenterFactory(IPresenterMvpFactory<V, P> presenterFactory) {
        mProxy.setPresenterFactory(presenterFactory);
    }

    @Override
    public IPresenterMvpFactory<V, P> getPresenterFactory() {
        return mProxy.getPresenterFactory();
    }

    @Override
    public P getMvpPresenter() {
        return mProxy.getMvpPresenter();
    }
}
