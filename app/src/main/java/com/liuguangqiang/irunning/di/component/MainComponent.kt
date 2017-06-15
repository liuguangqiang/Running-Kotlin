package com.liuguangqiang.irunning.di.component

import com.liuguangqiang.irunning.di.module.MainModule
import com.liuguangqiang.irunning.act.main.MainActivity
import dagger.Component

/**
 * Created by Eric on 2017/5/18.
 */
@Component(modules = arrayOf(MainModule::class))
interface MainComponent {

    fun inject(target: MainActivity)

}
