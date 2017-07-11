package com.liuguangqiang.irunning.act.login

import dagger.Component

/**
 * Created by Eric on 2017/7/11.
 */
@Component(modules = arrayOf(LoginModule::class))
interface LoginComponent {

    fun inject(activity: LoginActivity)
}