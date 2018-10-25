package com.lzq.sprout.data;

import android.os.Bundle;
import android.os.Handler;

/**
 * message of eventbus for mainactivity
 */
public class EventMessageMain {
    /**
     * User-defined message code so that the recipient can identify
     * what this message is about. Each {@link Handler} has its own name-space
     * for message codes, so you do not need to worry about yours conflicting
     * with other handlers.
     */
    public int what;

    /**
     * arg1 and arg2 are lower-cost alternatives to using
     */
    public int arg1;

    /**
     * arg1 and arg2 are lower-cost alternatives to using
     */
    public int arg2;

    public Object obj;

    public Bundle data;
}
