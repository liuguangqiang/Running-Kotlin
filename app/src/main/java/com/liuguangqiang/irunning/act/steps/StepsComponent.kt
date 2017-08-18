package com.liuguangqiang.irunning.act.steps

import dagger.Component

@Component(modules = arrayOf(StepsModule::class))
interface StepsComponent {

    fun inject(target: StepsActivity)
}
