package com.liuguangqiang.irunning.act.login

import com.liuguangqiang.irunning.data.model.TokenModel
import com.liuguangqiang.irunning.data.service.TokenService
import dagger.Module
import dagger.Provides

@Module
class LoginModule(private val view: LoginContract.View) {

    @Provides fun provideView() = view

    @Provides fun providePresenter(presenter: LoginPresenter): LoginContract.Presenter {
        return presenter
    }

    @Provides fun provideService(service: TokenModel): TokenService {
        return service
    }

}
