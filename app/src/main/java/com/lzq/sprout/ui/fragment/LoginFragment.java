package com.lzq.sprout.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lzq.sprout.base.view.BaseMvpFragment;
import com.lzq.sprout.base.view.ILoginView;
import com.lzq.sprout.presenter.LoginPresenter;
import com.lzq.sprout.utils.Log;

public class LoginFragment extends BaseMvpFragment<ILoginView, LoginPresenter> implements ILoginView {
    private static final Log.Tag TAG = new Log.Tag("LoginFragment");

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getMvpPresenter().login("");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void resultOK() {

    }
}
