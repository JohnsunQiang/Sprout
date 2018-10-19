package com.lzq.sprout.utils;

import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import com.lzq.sprout.app.SproutApp;

public class ToastUtils {
    private static Toast sToast = null;

    public static void showCenterToast(int id) {
        String text = SproutApp.getMyApplicationContext().getResources().getString(id);
        showCenterToast(text);
    }

    public static void showCenterToast(final String s) {
        SproutThreadPool.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showToast(s);
            }
        });
    }

    private static void showToast(String s) {
        if (TextUtils.isEmpty(s)) {
            return;
        }
        synchronized (ToastUtils.class) {
            if (null == sToast) {
                sToast = Toast.makeText(SproutApp.getMyApplicationContext(), null, Toast.LENGTH_SHORT);
            } else {
                hideToast();
            }
            sToast.setText(s);
            sToast.setGravity(Gravity.CENTER, 0, 0);
            sToast.show();
        }
    }

    private static void hideToast() {
        if (null != sToast) {
            sToast.cancel();
        }
    }
}
