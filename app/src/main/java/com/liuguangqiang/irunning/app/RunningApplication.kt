package com.liuguangqiang.irunning.app

import android.app.Application
import com.liuguangqiang.support.utils.Logger
import io.realm.Realm
import io.realm.RealmConfiguration



/**
 * Created by Eric on 2017/5/19.
 */
class RunningApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Logger.setTag("running")
        Realm.init(this)
        val realmConfig = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name("running")
                .build()

        Realm.setDefaultConfiguration(realmConfig)
    }

}