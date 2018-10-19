package com.lzq.sprout.presenter;

import com.lzq.sprout.base.BaseMvpPresenter;
import com.lzq.sprout.contract.MainContract;
import com.lzq.sprout.model.DataModel;
import com.lzq.sprout.model.Token;
import com.lzq.sprout.model.bean.BaseBean;
import com.lzq.sprout.model.bean.LoginInfo;
import com.lzq.sprout.model.http.HttpSubscriber;
import com.lzq.sprout.utils.Constants;

public class MainPresenterImpl extends BaseMvpPresenter<MainContract.IMainView> implements MainContract.IMainPresenter {

    public void login(String... params) {
        DataModel.requestModel(Token.API_MAIN_DATA)
                .setParams(params).requestPostAPI(Constants.RequestApis.LOGIN, new HttpSubscriber<BaseBean<LoginInfo>>() {
            @Override
            public void onStartCallBack() {

            }

            @Override
            public void onSuccess(BaseBean baseBean) {

            }

            @Override
            public void onError(String msg) {

            }

            @Override
            public void onFinish() {

            }
        });
    }
}
