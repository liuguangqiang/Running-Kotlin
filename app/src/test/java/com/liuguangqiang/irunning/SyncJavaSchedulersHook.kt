package com.liuguangqiang.irunning

import rx.Scheduler
import rx.plugins.RxJavaSchedulersHook
import rx.schedulers.Schedulers

/**
 * Created by eric on 20/9/2017.
 */
class SyncJavaSchedulersHook : RxJavaSchedulersHook() {

    override fun getIOScheduler(): Scheduler {
        return Schedulers.immediate()
    }

}