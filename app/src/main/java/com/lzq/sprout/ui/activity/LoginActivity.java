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
import com.lzq.sprout.utils.ToastUtils;

import butterknife.BindView;

public class LoginActivity extends BaseMvpActivity<ILoginView, LoginPresenter> implements ILoginView {
    private static final Log.Tag TAG = new Log.Tag("LoginFragment");

    SharedPreferenceUtils mDefaultPref;

    @BindView(R.id.et_username)
    EditText mEtUsername;

    @BindView(R.id.et_password)
    EditText mEtPassword;

    @BindView(R.id.login_btn)
    Button mLoginBtn;

    @BindView(R.id.cardview)
    CardView mCardview;

    @BindView(R.id.fab_btn)
    FloatingActionButton mFabBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        mEtUsername.setText(Constants.Login.USERNAME);
        mEtPassword.setText(Constants.Login.PASSWORD);
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void loginSuccess(BaseBean<LoginInfo> loginResullt) {
        mDefaultPref.saveData(Constants.Login.KEY_LOGIN_TOKEN, loginResullt.getData().getToken());
        mDefaultPref.saveData(Constants.Login.KEY_LAST_ACCOUNT, mEtUsername.getText().toString().trim());

    }

    @Override
    public void loginFail(String failMsg) {
        ToastUtils.showCenterToast("login fail = " + failMsg);
        Log.w(TAG, "login error=" + failMsg);
    }

    @Override
    public boolean isViewActive() {
        return isActivityActive();
    }
}
