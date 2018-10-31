package com.lzq.sprout.base.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.lzq.sprout.base.factory.IPresenterMvpFactory;
import com.lzq.sprout.base.factory.PresenterMvpFactoryImpl;
import com.lzq.sprout.base.presenter.BaseMvpPresenter;
import com.lzq.sprout.base.proxy.IPresenterProxy;
import com.lzq.sprout.base.proxy.PresenterProxyImpl;
import com.lzq.sprout.utils.Constants;

public abstract class BaseMvpFragment<V extends IBaseMvpView, P extends BaseMvpPresenter<V>> extends Fragment implements IPresenterProxy<V, P> {

    private PresenterProxyImpl<V, P> mProxy = new PresenterProxyImpl<>(PresenterMvpFactoryImpl.<V, P>createFactory(getClass()));
    protected Activity mActivity;
    protected LoadService mBaseLoadService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (null != savedInstanceState) {
            mProxy.onRestoreInstanceState(savedInstanceState.getBundle(Constants.Presenter.PRESENTER_SAVE_BUNDLE));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = View.inflate(getActivity(), onCreateFragmentView(), null);

        mBaseLoadService = LoadSir.getDefault().register(rootView, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                onHttpReload(v);
            }
        });
        initViews(rootView);

        return mBaseLoadService.getLoadLayout();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadHttp();
    }

    protected void onHttpReload(View v) {
    }

    protected void loadHttp() {
        mBaseLoadService.showSuccess();
    }

    protected abstract int onCreateFragmentView();
    public abstract void initViews(View rootView);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity) context;
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
