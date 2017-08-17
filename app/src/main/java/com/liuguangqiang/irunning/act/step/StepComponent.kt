package com.liuguangqiang.irunning.act.step

import dagger.Component

/**
 * Created by eric on 16/8/2017.
 */
@Component(modules = arrayOf(StepModule::class))
interface StepComponent {

    fun inject(fragment: StepFragment)
}