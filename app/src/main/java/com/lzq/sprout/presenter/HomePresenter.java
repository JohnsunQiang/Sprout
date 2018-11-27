package com.lzq.sprout.presenter;

import com.lzq.sprout.base.presenter.BaseMvpPresenter;
import com.lzq.sprout.base.view.IHomeView;
import com.lzq.sprout.model.HomeModel;
import com.lzq.sprout.model.bean.BaseBean;
import com.lzq.sprout.model.bean.Meizi;
import com.lzq.sprout.model.http.HttpSubscriber;
import com.lzq.sprout.utils.Log;

import java.util.List;

public class HomePresenter extends BaseMvpPresenter<IHomeView> {
    private static final Log.Tag TAG = new Log.Tag("HomePresenter");
    private HomeModel mHomeModel;

    public HomePresenter() {
        mHomeModel = new HomeModel();
    }

    public void getMeizi() {
        if (null != mHomeModel && isViewAttached()) {
            mHomeModel.getMeizi(new HttpSubscriber<BaseBean<List<Meizi>>>() {
                @Override
                public void onStartCallBack() {

                }

                @Override
                public void onSuccess(BaseBean<List<Meizi>> listBaseBean) {
                    Log.d(TAG, "listBaseBean=" + listBaseBean);
                }

                @Override
                public void onError(String msg) {

                }
            }, getLifecycleProvider());
        }
    }
}
