package com.liuguangqiang.irunning

import rx.Scheduler
import rx.android.plugins.RxAndroidSchedulersHook
import rx.schedulers.Schedulers

/**
 * Created by eric on 20/9/2017.
 */
class SyncAndroidSchedulersHook : RxAndroidSchedulersHook() {

    override fun getMainThreadScheduler(): Scheduler {
        return Schedulers.immediate()
    }

}