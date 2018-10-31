package com.lzq.sprout.ui.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lzq.sprout.ui.view.tabview.TabItem;
import com.lzq.sprout.utils.Log;

import java.util.ArrayList;

public class FragmentAdapter extends FragmentPagerAdapter {
    private static final Log.Tag TAG = new Log.Tag("FragmentAdapter");
    private ArrayList<TabItem> mTabItems;

    public FragmentAdapter(FragmentManager fm, ArrayList<TabItem> tabItems) {
        super(fm);
        mTabItems = tabItems;
    }

    @Override
    public Fragment getItem(int arg0) {
        try {
            if (null != mTabItems) {
                return mTabItems.get(arg0).tagFragmentClz.newInstance();
            } else {
                Log.w(TAG, "getItem error, null tab");
            }
        } catch (Exception e) {
            Log.e(TAG, "getItem error", e);
        }
        return null;
    }

    public void resetTabs(ArrayList<TabItem> tabItems) {
        mTabItems = tabItems;
    }

    @Override
    public int getCount() {
        return null == mTabItems ? 0 : mTabItems.size();
    }
}
