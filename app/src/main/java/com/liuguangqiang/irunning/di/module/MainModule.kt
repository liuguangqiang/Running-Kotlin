package com.liuguangqiang.irunning.di.module

import com.liuguangqiang.irunning.act.main.MainContract
import com.liuguangqiang.irunning.act.main.MainPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Eric on 2017/5/18.
 */
@Module
class MainModule(private val view: MainContract.View) {

    @Provides
    fun provideView() = view

    @Provides
    fun providePresenter(presenter: MainPresenter): MainContract.Presenter {
        return presenter
    }

}
