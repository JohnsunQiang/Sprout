package com.lzq.sprout.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.widget.Button;
import android.widget.EditText;

import com.lzq.sprout.R;
import com.lzq.sprout.base.view.BaseMvpActivity;
import com.lzq.sprout.base.view.ILoginView;
import com.lzq.sprout.model.bean.BaseBean;
import com.lzq.sprout.model.bean.LoginInfo;
import com.lzq.sprout.presenter.LoginPresenter;
import com.lzq.sprout.utils.Constants;
import com.lzq.sprout.utils.Log;
import com.lzq.sprout.utils.SharedPreferenceUtils;

public class LoginActivity extends BaseMvpActivity<ILoginView, LoginPresenter> implements ILoginView {
    private static final Log.Tag TAG = new Log.Tag("LoginFragment");

    SharedPreferenceUtils mDefaultPref;

    EditText etUsername;
    EditText etPassword;
    Button loginBtn;
    CardView cardview;
    FloatingActionButton fabBtn;

    private boolean mIsFromLaunch;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();

        mIsFromLaunch = getIntent().getBooleanExtra(Constants.Login.INTENT_PARAM_ISFROMLAUNCH, false);
    }

    private void initData() {
        // if it do before each login
        mDefaultPref = new SharedPreferenceUtils();
        mDefaultPref.saveData(Constants.Login.KEY_ACCESS_TOKEN, "");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);

        loginBtn = findViewById(R.id.login_btn);

        cardview = findViewById(R.id.cardview);
        fabBtn = findViewById(R.id.fab_btn);
    }

    @Override
    public void loginSuccess(BaseBean<LoginInfo> loginResullt) {

    }

    @Override
    public void loginFail(String failMsg) {

    }
}
