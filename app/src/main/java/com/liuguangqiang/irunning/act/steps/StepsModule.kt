package com.liuguangqiang.irunning.act.steps

import dagger.Module
import dagger.Provides

@Module
class StepsModule(private val view: StepsContract.View) {

    @Provides
    fun provideView() = view

    @Provides
    fun providePresenter(presenter: StepsPresenter): StepsContract.Presenter {
        return presenter
    }

}
