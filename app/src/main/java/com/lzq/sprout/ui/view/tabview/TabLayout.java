package com.lzq.sprout.ui.view.tabview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class TabLayout extends LinearLayout implements View.OnClickListener {
    private ArrayList<TabItem> mTabs;
    private OnTabClickListener mListener;
    private View mSelectView;
    private int mTabCount;

    public TabLayout(Context context) {
        super(context);
        initView();
    }

    public TabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TabLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        setOrientation(HORIZONTAL);
    }

    public void setCurrentTab(int i) {
        if (i < mTabCount && i >= 0) {
            View view = getChildAt(i);
            if (mSelectView != view) {
                view.setSelected(true);
                if (mSelectView != null) {
                    mSelectView.setSelected(false);
                }
                mSelectView = view;
            }
        }
    }

    public void initData(ArrayList<TabItem> tabs, OnTabClickListener listener) {
        this.mTabs = tabs;
        this.mListener = listener;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        params.weight = 1;
        if (tabs != null && tabs.size() > 0) {
            mTabCount = tabs.size();
            TabView mTabView = null;
            for (int i = 0; i < mTabCount; i++) {
                mTabView = new TabView(getContext());
                mTabView.setTag(tabs.get(i));
                mTabView.initData(tabs.get(i));
                mTabView.setOnClickListener(this);
                addView(mTabView, params);
            }
        } else {
            throw new IllegalArgumentException("tabs can not be empty");
        }
    }

    public void onDataChanged(int i, int badgeCount) {
        if (i < mTabCount && i >= 0) {
            TabView tabView = (TabView) getChildAt(i);
            tabView.onDataChanged(badgeCount);
        }
    }

    @Override
    public void onClick(View v) {
        mListener.onTabClick((TabItem) v.getTag());
    }

    public interface OnTabClickListener {
        void onTabClick(TabItem tabItem);
    }
}
