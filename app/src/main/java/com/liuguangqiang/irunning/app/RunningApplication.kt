package com.liuguangqiang.irunning.app

import android.app.Application
import com.liuguangqiang.support.utils.Logger

/**
 * Created by Eric on 2017/5/19.
 */
class RunningApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Logger.setTag("running")
    }

}