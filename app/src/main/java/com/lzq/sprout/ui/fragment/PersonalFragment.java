package com.lzq.sprout.ui.fragment;

import android.view.View;

import com.lzq.sprout.R;
import com.lzq.sprout.base.view.BaseMvpFragment;
import com.lzq.sprout.base.view.IPersonalView;
import com.lzq.sprout.presenter.PersonalPresenter;

public class PersonalFragment extends BaseMvpFragment<IPersonalView, PersonalPresenter> implements IPersonalView {
    @Override
    protected int onCreateFragmentView() {
        return R.layout.fragment_presenter;
    }

    @Override
    public void initViews(View rootView) {

    }
}
