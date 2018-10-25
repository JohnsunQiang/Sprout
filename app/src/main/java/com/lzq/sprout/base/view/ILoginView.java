package com.lzq.sprout.base.view;

import com.lzq.sprout.model.bean.BaseBean;
import com.lzq.sprout.model.bean.LoginInfo;

public interface ILoginView extends IBaseMvpView {
    void loginSuccess(BaseBean<LoginInfo> loginResullt);
    void loginFail(String failMsg);
}
