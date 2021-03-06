package com.app.common.thread;

import android.os.Process;

import java.util.concurrent.ThreadFactory;

/**
 * Created by wangru
 * Date: 2018/1/27  14:45
 * mail: 1902065822@qq.com
 * describe:
 */

public class PriorityThreadFactory implements ThreadFactory {

    private final int mThreadPriority;

    public PriorityThreadFactory(int threadPriority) {
        mThreadPriority = threadPriority;
    }

    @Override
    public Thread newThread(final Runnable runnable) {
        Runnable wrapperRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Process.setThreadPriority(mThreadPriority);
                } catch (Throwable t) {

                }
                runnable.run();
            }
        };
        return new Thread(wrapperRunnable);
    }

}