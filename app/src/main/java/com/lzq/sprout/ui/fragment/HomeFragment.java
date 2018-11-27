package com.lzq.sprout.ui.fragment;

import android.view.View;

import com.lzq.sprout.R;
import com.lzq.sprout.base.view.BaseMvpFragment;
import com.lzq.sprout.base.view.IHomeView;
import com.lzq.sprout.presenter.HomePresenter;
import com.lzq.sprout.utils.Log;

public class HomeFragment extends BaseMvpFragment<IHomeView, HomePresenter> implements IHomeView {
    private static final Log.Tag TAG = new Log.Tag("HomeFragment");

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initViews(View rootView) {
        Log.d(TAG, "initViews");
        if (null != mPresenter) {
            mPresenter.setView(this);
            mPresenter.getMeizi();
        }
    }


    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }
}
