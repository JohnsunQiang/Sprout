package com.lzq.sprout.base.proxy;

import com.lzq.sprout.base.factory.IPresenterMvpFactory;
import com.lzq.sprout.base.presenter.BaseMvpPresenter;
import com.lzq.sprout.base.view.IBaseMvpView;

public interface IPresenterProxy<V extends IBaseMvpView,P extends BaseMvpPresenter<V>> {

    void setPresenterFactory(IPresenterMvpFactory<V, P> presenterFactory);

    IPresenterMvpFactory<V,P> getPresenterFactory();

    P getMvpPresenter();
}
