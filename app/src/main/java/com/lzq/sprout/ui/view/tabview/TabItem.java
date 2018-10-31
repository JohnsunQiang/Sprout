package com.lzq.sprout.ui.view.tabview;

import com.lzq.sprout.base.view.BaseMvpFragment;

public class TabItem {
    public int imageResId;
    public int lableResId;
    public Class<? extends BaseMvpFragment> tagFragmentClz;

    public TabItem(int imageResId, int lableResId, Class<? extends BaseMvpFragment> tagFragmentClz) {
        this.imageResId = imageResId;
        this.lableResId = lableResId;
        this.tagFragmentClz = tagFragmentClz;
    }
}
