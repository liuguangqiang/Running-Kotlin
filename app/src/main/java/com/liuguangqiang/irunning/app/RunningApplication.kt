package com.liuguangqiang.irunning.app

import android.app.Application
import android.content.Context
import android.util.Log
import com.crashlytics.android.Crashlytics
import com.liuguangqiang.irunning.utils.LoginManager
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
        context = this
        Logger.setTag("running")
        LoginManager.instance.init()

        //Fabric
        val fabric: Fabric = Fabric.Builder(this)
                .kits(Crashlytics())
                .debuggable(true)
                .build()
        Fabric.with(fabric)

        //Realm
        Realm.init(this)
        val realmConfig = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name("running")
                .build()
        Realm.setDefaultConfiguration(realmConfig)
    }

    companion object {

        lateinit var context: Context
    }

}