package com.lzq.sprout.utils;

import android.content.Context;
import android.content.Intent;

public class AppUtils {

    public static void startActivity(Context context, Class<?> cla) {
        Intent intent = new Intent();
        intent.setClass(context, cla);
        context.startActivity(intent);
    }
}
