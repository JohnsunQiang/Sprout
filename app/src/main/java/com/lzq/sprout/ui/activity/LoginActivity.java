package com.lzq.sprout.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lzq.sprout.R;
import com.lzq.sprout.base.view.BaseMvpActivity;
import com.lzq.sprout.base.view.ILoginView;
import com.lzq.sprout.model.bean.BaseBean;
import com.lzq.sprout.model.bean.LoginInfo;
import com.lzq.sprout.presenter.LoginPresenter;
import com.lzq.sprout.utils.AppUtils;
import com.lzq.sprout.utils.Constants;
import com.lzq.sprout.utils.Log;
import com.lzq.sprout.utils.SharedPreferenceUtils;
import com.lzq.sprout.utils.ToastUtils;

public class LoginActivity extends BaseMvpActivity<ILoginView, LoginPresenter> implements ILoginView {
    private static final Log.Tag TAG = new Log.Tag("LoginFragment");

    SharedPreferenceUtils mDefaultPref;

    EditText mEtUsername;
    EditText mEtPassword;
    Button mLoginBtn;
    CardView mCardview;
    FloatingActionButton mFabBtn;

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
        mDefaultPref.saveData(Constants.Login.KEY_LOGIN_TOKEN, "");
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
        mEtUsername = findViewById(R.id.et_username);
        mEtPassword = findViewById(R.id.et_password);
        mEtUsername.setText("1234");
        mEtPassword.setText("1234");

        mLoginBtn = findViewById(R.id.login_btn);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mEtUsername.getText().toString().trim();
                String password = mEtPassword.getText().toString().trim();
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Log.w(TAG, "username or password is null");
                    return;
                }
                getMvpPresenter().login(username, password);
            }
        });

        mCardview = findViewById(R.id.cardview);
        mFabBtn = findViewById(R.id.fab_btn);
    }

    @Override
    public void loginSuccess(BaseBean<LoginInfo> loginResullt) {
        mDefaultPref.saveData(Constants.Login.KEY_LOGIN_TOKEN, loginResullt.getData().getToken());
        mDefaultPref.saveData(Constants.Login.KEY_LAST_ACCOUNT, mEtUsername.getText().toString().trim());

        if (mIsFromLaunch) {
            AppUtils.startActivity(this, MainActivity.class);
            finish();
        }
    }

    @Override
    public void loginFail(String failMsg) {
        ToastUtils.showCenterToast("login fail = " + failMsg);
        Log.w(TAG, "login error=" + failMsg);
    }
}
