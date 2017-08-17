package com.liuguangqiang.irunning.act.step

import dagger.Module
import dagger.Provides

/**
 * Created by eric on 16/8/2017.
 */
@Module
class StepModule {

    var view: StepContract.View

    constructor(view: StepContract.View) {
        this.view = view
    }

    @Provides fun provideView(): StepContract.View {
        return view
    }

    @Provides fun providePresenter(presenter: StepPresenter): StepContract.Presenter {
        return presenter
    }

}