package com.lzq.sprout.base.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzq.sprout.base.presenter.BaseMvpPresenter;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;

public abstract class BaseMvpFragment<V extends IBaseMvpView, P extends BaseMvpPresenter<V>> extends RxFragment {

    protected Activity mActivity;
    protected P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, rootView);
        mActivity = getActivity();
        mPresenter = createPresenter();
        if (null != mPresenter) {
            mPresenter.setLifecycleProvider(this);
        }
        initViews(rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    protected abstract int getLayoutId();

    protected abstract void initViews(View rootView);

    protected abstract P createPresenter();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mPresenter) {
            mPresenter.onDestroyPersenter();
            mPresenter = null;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    protected boolean isActivityActive() {
        return mActivity != null && (!mActivity.isFinishing() || !mActivity.isDestroyed());
    }

    protected boolean isFragmentActive() {
        return !isDetached() || !isRemoving() || isActivityActive();
    }
}
