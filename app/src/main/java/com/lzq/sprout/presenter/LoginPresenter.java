package com.lzq.sprout.presenter;

import com.lzq.sprout.base.presenter.BaseMvpPresenter;
import com.lzq.sprout.base.view.ILoginView;
import com.lzq.sprout.model.DataModel;
import com.lzq.sprout.model.Token;
import com.lzq.sprout.model.bean.BaseBean;
import com.lzq.sprout.model.bean.LoginInfo;
import com.lzq.sprout.model.http.HttpSubscriber;
import com.lzq.sprout.utils.Constants;

public class LoginPresenter extends BaseMvpPresenter<ILoginView> {


    public void login(String... params) {
        DataModel.requestModel(Token.API_MAIN_DATA)
                .setParams(params).requestPostAPI(Constants.RequestApis.LOGIN, new HttpSubscriber<BaseBean<LoginInfo>>() {
            @Override
            public void onStartCallBack() {
            }

            @Override
            public void onSuccess(BaseBean<LoginInfo> loginResult) {
                if (isViewAttached()) {
                    getView().loginSuccess(loginResult);
                }
            }

            @Override
            public void onError(String msg) {
                if (isViewAttached()) {
                    getView().loginFail(msg);
                }
            }

            @Override
            public void onFinish() {

            }
        });
    }

    @Override
    public void onDestroyPersenter() {

    }
}
