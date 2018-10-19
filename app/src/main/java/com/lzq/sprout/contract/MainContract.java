package com.lzq.sprout.contract;

import com.lzq.sprout.base.IBaseMvpPresenter;
import com.lzq.sprout.base.IBaseMvpView;

public class MainContract {
    public interface IMainView extends IBaseMvpView {
    }

    public interface IMainPresenter extends IBaseMvpPresenter<IMainView> {
    }
}
