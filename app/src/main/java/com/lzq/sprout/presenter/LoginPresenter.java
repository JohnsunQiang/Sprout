package com.lzq.sprout.presenter;

import com.lzq.sprout.base.presenter.BaseMvpPresenter;
import com.lzq.sprout.base.view.ILoginView;

public class LoginPresenter extends BaseMvpPresenter<ILoginView> {

    public LoginPresenter(ILoginView view) {
        super(view);
    }

    public void login(String... params) {
//        MainDataModel<LoginInfo> mMainDataModel = new MainDataModel<LoginInfo>();
//        mMainDataModel.requestPostAPI(Constants.RequestApis.BASE_URL, params, new HttpSubscriber<BaseBean<LoginInfo>>() {
//            @Override
//            public void onStartCallBack() {
//            }
//
//            @Override
//            public void onSuccess(BaseBean<LoginInfo> loginResult) {
//                if (isViewAttached()) {
//                    getView().loginSuccess(loginResult);
//                }
//            }
//
//            @Override
//            public void onError(String msg) {
//                if (isViewAttached()) {
//                    getView().loginFail(msg);
//                }
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        });
    }

    @Override
    public void onDestroyPersenter() {

    }
}
