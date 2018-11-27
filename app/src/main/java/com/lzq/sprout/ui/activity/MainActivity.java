package com.lzq.sprout.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.lzq.sprout.R;
import com.lzq.sprout.data.EventMessageMain;
import com.lzq.sprout.ui.Adapter.FragmentAdapter;
import com.lzq.sprout.ui.fragment.HomeFragment;
import com.lzq.sprout.ui.fragment.PersonalFragment;
import com.lzq.sprout.ui.view.tabview.TabItem;
import com.lzq.sprout.ui.view.tabview.TabLayout;
import com.lzq.sprout.utils.Log;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends RxAppCompatActivity implements TabLayout.OnTabClickListener {
    private static final Log.Tag TAG = new Log.Tag("MainActivity");

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    @BindView(R.id.main_viewpager)
    ViewPager mViewPager;
    private ArrayList<TabItem> mTabItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initView();
        initData();
    }

    private void initView() {
    }

    private void initData() {
        mTabItems = new ArrayList<>();
        mTabItems.add(new TabItem(R.drawable.selector_tab_msg, R.string.tab_home, HomeFragment.class));
        mTabItems.add(new TabItem(R.drawable.selector_tab_profile, R.string.tab_personal, PersonalFragment.class));
        mTabLayout.initData(mTabItems, this);
        mTabLayout.setCurrentTab(0);

        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), mTabItems);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleMessageCallback(EventMessageMain message) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onTabClick(TabItem tabItem) {
        mViewPager.setCurrentItem(mTabItems.indexOf(tabItem));
    }
}
