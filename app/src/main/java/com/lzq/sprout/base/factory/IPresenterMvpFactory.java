package com.lzq.sprout.base.factory;

import com.lzq.sprout.base.presenter.BaseMvpPresenter;
import com.lzq.sprout.base.view.IBaseMvpView;

public interface IPresenterMvpFactory<V extends IBaseMvpView, P extends BaseMvpPresenter<V>> {
    P createMvpPresenter();
}
