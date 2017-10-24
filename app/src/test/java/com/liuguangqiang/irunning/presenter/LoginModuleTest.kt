package com.liuguangqiang.irunning.act.login

import com.liuguangqiang.irunning.data.model.TokenModel
import com.liuguangqiang.irunning.data.model.UserModel
import com.liuguangqiang.irunning.data.service.TokenService
import com.liuguangqiang.irunning.data.service.UserService
import dagger.Module
import dagger.Provides
import org.mockito.Mockito

@Module
open class LoginModuleTest(private val view: LoginContract.View) {

    @Provides
    fun provideView() = view

    @Provides
    fun providePresenter(presenter: LoginPresenter): LoginContract.Presenter {
        return presenter
    }

    @Provides
    fun provideService(service: TokenModel): TokenService {
        return Mockito.mock(TokenService::class.java)
    }

    @Provides
    fun provideUserService(service: UserModel): UserService {
        return Mockito.mock(UserService::class.java)
    }

}
