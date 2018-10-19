package com.lzq.sprout.utils;

import com.orhanobut.logger.Logger;

public class Log {
    public static final String SPROUT_LOGTAG_PREFIX = "SRT_";
    private static final Log.Tag TAG = new Log.Tag("Log");

    public static final class Tag {
        final String mValue;
        // The length limit from Android framework is 23.
        private static final int MAX_TAG_LEN = 23 - SPROUT_LOGTAG_PREFIX.length();

        public Tag(String tag) {
            final int lenDiff = tag.length() - MAX_TAG_LEN;
            if (lenDiff > 0) {
                w(TAG, "Tag " + tag + " is " + lenDiff + " chars longer than limit.");
            }
            mValue = new StringBuilder()
                    .append(SPROUT_LOGTAG_PREFIX)
                    .append(lenDiff > 0 ? tag.substring(0, MAX_TAG_LEN) : tag)
                    .toString();
        }

        @Override
        public String toString() {
            return mValue;
        }
    }

    public static void d(Tag tag, String msg) {
        Logger.d(tag.toString(), msg);
    }

    public static void d(Tag tag, Object object) {
        Logger.d(tag.toString(), object);
    }

    public static void Debug(Tag tag, String msg) {
        if (isDebugMode()) {
            d(tag, msg);
        }
    }

    public static void Debug(Tag tag, Object object) {
        if (isDebugMode()) {
            d(tag, object);
        }
    }

    public static void i(Tag tag, String msg) {
        Logger.i(tag.toString(), msg);
    }

    public static void v(Tag tag, String msg) {
        Logger.v(tag.toString(), msg);
    }

    public static void w(Tag tag, String msg) {
        Logger.w(tag.toString(), msg);
    }

    public static void e(Tag tag, String msg, Throwable throwable) {
        Logger.e(throwable, tag.toString(), msg);
    }

    public static void xml(Tag tag, String xml) {
        d(tag, "xml");
        Logger.xml(xml);
    }

    private static boolean isDebugMode() {
        return false;
    }
}
