package com.lzq.sprout.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;

import com.lzq.sprout.app.SproutApp;

public class NetworkUtils {
    private static final Log.Tag TAG = new Log.Tag("NetworkUtil");

    public enum NetState {
        WIFI,
        MN2G,
        MN3G,
        MN4G,
        NONE;
    }

    /**
     * Gets active network type.
     *
     * @return
     */
    public static int getActiveNetworkType() {
        try {
            ConnectivityManager cm =
                    (ConnectivityManager) SproutApp.getMyApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info != null) {
                return info.getType();
            }
        } catch (SecurityException e) {
            Log.e(TAG, "getActiveNetworkType error", e);
        }

        return -1;
    }

    /**
     * Gets active network type name.
     *
     * @return type-subtype
     */
    public static String getActiveNetworkTypeName() {
        try {
            ConnectivityManager cm =
                    (ConnectivityManager) SproutApp.getMyApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(info.getTypeName()).append("-").append(info.getSubtypeName());
                return sb.toString();
            }
        } catch (SecurityException e) {
            Log.e(TAG, "getActiveNetworkTypeName error", e);
        }

        return "<unknown>";
    }

    public static boolean isNetworkConnected() {
        return getActiveNetworkType() >= 0;
    }

    /**
     * check if wifi network
     * @return
     */
    public static boolean isWifiConnected() {
        return getNetState() == NetState.WIFI;
    }

    /**
     * Gets active network type.
     *
     * @return
     */
    public static NetState getNetState() {
        try {
            ConnectivityManager connManager =
                    (ConnectivityManager) SproutApp.getMyApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
            if (networkInfo == null || !networkInfo.isConnectedOrConnecting()) {
                return NetState.NONE;
            }
            // 非收费网络，表明是wifi状态
            if (Build.VERSION.SDK_INT >= 16) {
                if (!connManager.isActiveNetworkMetered()) {
                    return NetState.WIFI;
                }
            } else {
                if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    return NetState.WIFI;
                }
            }
            final TelephonyManager telephony =
                    (TelephonyManager) SproutApp.getMyApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
            int networkType = telephony.getNetworkType();
            return getNetworkClass(networkType);
        } catch (Exception e) {
            Log.e(TAG, "getNetState error", e);
        }
        return NetState.NONE;
    }

    private static NetState getNetworkClass(int networkType) {
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return NetState.MN2G;
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return NetState.MN3G;
            case TelephonyManager.NETWORK_TYPE_LTE:
                return NetState.MN4G;
            default:
                return NetState.NONE;
        }
    }
}
