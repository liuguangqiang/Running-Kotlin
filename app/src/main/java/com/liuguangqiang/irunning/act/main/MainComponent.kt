package com.liuguangqiang.irunning.act.main

import dagger.Component

/**
 * Created by Eric on 2017/5/18.
 */
@Component(modules = arrayOf(MainModule::class))
interface MainComponent {

    fun inject(target: MainActivity)

}
