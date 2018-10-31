package com.lzq.sprout.commontips;

import com.kingja.loadsir.callback.Callback;
import com.lzq.sprout.R;


public class EmptyCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.layout_empty;
    }

}
