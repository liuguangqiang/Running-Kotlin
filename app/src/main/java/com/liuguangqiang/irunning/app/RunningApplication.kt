package com.liuguangqiang.irunning.app

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.liuguangqiang.support.utils.Logger
import io.fabric.sdk.android.Fabric
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by Eric on 2017/5/19.
 */
class RunningApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Logger.setTag("running")
        val fabric: Fabric = Fabric.Builder(this)
                .kits(Crashlytics())
                .debuggable(true)
                .build()
        Fabric.with(fabric)

        Realm.init(this)
        val realmConfig = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name("running")
                .build()

        Realm.setDefaultConfiguration(realmConfig)
    }

}