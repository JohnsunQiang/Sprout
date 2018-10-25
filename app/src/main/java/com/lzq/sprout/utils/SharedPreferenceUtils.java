package com.lzq.sprout.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.lzq.sprout.app.SproutApp;

public class SharedPreferenceUtils {
    private static final Log.Tag TAG = new Log.Tag("SharePreferenceUtils");
    private static final String DEFAULT_PREF_NAME = "com.lzq.sprout.default";

    private SharedPreferences mSharedPreferences;

    public SharedPreferenceUtils() {
        this(DEFAULT_PREF_NAME);
    }

    public SharedPreferenceUtils(@NonNull String prefName) {
        if (!TextUtils.isEmpty(prefName)) {
            mSharedPreferences = SproutApp.getMyApplicationContext().getSharedPreferences(prefName, Context.MODE_PRIVATE);
        } else {
            Log.w(TAG, "SharedPreferenceUtils init error, null pref name");
        }
    }

    public <T> T getData(String key, @NonNull Object defValue, @NonNull Class<T> clazz) {
        T t = null;
        if (!TextUtils.equals(defValue.getClass().getSimpleName(), clazz.getSimpleName())) {
            throw new UnsupportedOperationException("defValue type does not equals whit clazz ");
        }
        if (null == mSharedPreferences) {
            Log.w(TAG, "getData error, null preference");
            return null;
        }

        switch (clazz.getSimpleName()) {
            case "String":
                t = (T) mSharedPreferences.getString(key, (String) defValue);
                break;
            case "Integer":
                t = (T) (Integer) mSharedPreferences.getInt(key, (Integer) defValue);
                break;
            case "Float":
                t = (T) (Float) mSharedPreferences.getFloat(key, (Float) defValue);
                break;
            case "Long":
                t = (T) (Long) mSharedPreferences.getLong(key, (Long) defValue);
                break;
            case "Boolean":
                t = (T) (Boolean) mSharedPreferences.getBoolean(key, (Boolean) defValue);
                break;
        }
        return t;
    }

    public Object getData(String key, Object defaultObject) {
        if (null == mSharedPreferences) {
            Log.w(TAG, "getData error, null preference");
            return null;
        }
        if (defaultObject instanceof String) {
            return mSharedPreferences.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return mSharedPreferences.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return mSharedPreferences.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return mSharedPreferences.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return mSharedPreferences.getLong(key, (Long) defaultObject);
        }
        return null;
    }

    public void saveData(String key, @NonNull Object value) {
        if (null == mSharedPreferences) {
            Log.w(TAG, "saveData error, null preference");
            return;
        }
        if (value instanceof String) {
            mSharedPreferences.edit().putString(key, (String) value).commit();
        } else if (value instanceof Integer) {
            mSharedPreferences.edit().putInt(key, (Integer) value).commit();
        } else if (value instanceof Float) {
            mSharedPreferences.edit().putFloat(key, (Float) value).commit();
        } else if (value instanceof Long) {
            mSharedPreferences.edit().putLong(key, (Long) value).commit();
        } else if (value instanceof Boolean) {
            mSharedPreferences.edit().putBoolean(key, (Boolean) value).commit();
        } else {
            Log.w(TAG, "saveData error, unknow value");
        }
    }
}
