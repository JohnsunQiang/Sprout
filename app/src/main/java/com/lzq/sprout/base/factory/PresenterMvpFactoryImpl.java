package com.lzq.sprout.base.factory;

import com.lzq.sprout.base.presenter.BaseMvpPresenter;
import com.lzq.sprout.base.view.IBaseMvpView;
import com.lzq.sprout.utils.Log;

public class PresenterMvpFactoryImpl<V extends IBaseMvpView, P extends BaseMvpPresenter<V>> implements IPresenterMvpFactory<V,P> {
    public static final Log.Tag TAG = new Log.Tag("PresenterMvpFactoryImpl");

    /**
     * Presenter to create
     */
    private final Class<P> mPresenterClass;

    /**
     * create presenter factory according to annotation
     * @param viewClazz class of view
     * @param <V> type of view
     * @param <P> type of Presenter
     * @return
     */
    public static <V extends IBaseMvpView, P extends BaseMvpPresenter<V>> PresenterMvpFactoryImpl<V,P> createFactory(Class<?> viewClazz) {
        CreatePresenter annotation = viewClazz.getAnnotation(CreatePresenter.class);
        Class<P> aClass = null;
        if (null != annotation) {
            aClass = (Class<P>) annotation.value();
        }
        return aClass == null ? null : new PresenterMvpFactoryImpl<V, P>(aClass);
    }


    private PresenterMvpFactoryImpl(Class<P> presenterClass) {
        this.mPresenterClass = presenterClass;
    }

    @Override
    public P createMvpPresenter() {
        try {
            return mPresenterClass.newInstance();
        } catch (Exception e) {
            Log.e(TAG, "createMvpPresenter error", e);
            throw new RuntimeException("create mvp presenter error");
        }
    }
}
