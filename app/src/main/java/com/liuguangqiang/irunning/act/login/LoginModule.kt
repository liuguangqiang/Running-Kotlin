package com.liuguangqiang.irunning.act.login

import dagger.Module
import dagger.Provides

@Module
class LoginModule(private val view: LoginContract.View) {

    @Provides fun provideView() = view

    @Provides fun providePresenter(presenter: LoginPresenter): LoginContract.Presenter {
        return presenter
    }

}
