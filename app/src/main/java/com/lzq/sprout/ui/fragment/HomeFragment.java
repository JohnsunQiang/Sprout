package com.lzq.sprout.ui.fragment;

import android.view.View;

import com.lzq.sprout.R;
import com.lzq.sprout.base.view.BaseMvpFragment;
import com.lzq.sprout.base.view.IHomeView;
import com.lzq.sprout.presenter.HomePresenter;

public class HomeFragment extends BaseMvpFragment<IHomeView, HomePresenter> implements IHomeView {

    @Override
    protected int onCreateFragmentView() {
        return R.layout.fragment_home;
    }

    @Override
    public void initViews(View rootView) {
    }
}
