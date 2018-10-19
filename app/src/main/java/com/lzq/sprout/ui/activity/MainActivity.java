package com.lzq.sprout.ui.activity;

import android.os.Bundle;

import com.lzq.sprout.base.BaseMvpActivity;
import com.lzq.sprout.contract.MainContract;
import com.lzq.sprout.presenter.MainPresenterImpl;
import com.lzq.sprout.utils.Log;

public class MainActivity extends BaseMvpActivity<MainContract.IMainPresenter> implements MainContract.IMainView {
    private static final Log.Tag TAG = new Log.Tag("MainActivity");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected MainContract.IMainPresenter createPresenter() {
        return new MainPresenterImpl();
    }
}
