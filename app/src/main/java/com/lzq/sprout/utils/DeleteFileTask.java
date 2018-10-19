package com.lzq.sprout.utils;

import android.text.TextUtils;

import java.io.File;

public class DeleteFileTask implements Runnable {
    private static final Log.Tag TAG = new Log.Tag("DeleteFileTask");

    private String[] mFilePaths;

    public DeleteFileTask(String... filePath) {
        mFilePaths = filePath;
    }

    @Override
    public void run() {
        if (null == mFilePaths || mFilePaths.length == 0) {
            Log.w(TAG, "delete file fail, null path");
            return;
        }

        for (String filepath : mFilePaths) {
            try {
                if (!TextUtils.isEmpty(filepath)) {
                    File file = new File(filepath);
                    if (file.exists() && file.isFile()) {
                        Log.v(TAG, "delete " + filepath);
                        file.delete();
                    }
                }
            } catch (Exception e) {
                Log.e(TAG, "delete file error", e);
            }
        }
    }
}
