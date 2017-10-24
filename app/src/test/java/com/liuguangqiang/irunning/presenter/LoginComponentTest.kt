package com.liuguangqiang.irunning.act.login

import com.liuguangqiang.irunning.presenter.LoginPresenterTest
import dagger.Component

/**
 * Created by Eric on 2017/7/11.
 */
@Component(modules = arrayOf(LoginModuleTest::class))
interface LoginComponentTest {

    fun inject(activity: LoginPresenterTest)
}