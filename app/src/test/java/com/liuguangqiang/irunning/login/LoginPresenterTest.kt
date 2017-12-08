package com.liuguangqiang.irunning.login

import com.liuguangqiang.irunning.act.login.LoginContract
import com.liuguangqiang.irunning.act.login.LoginPresenter
import com.liuguangqiang.irunning.data.entity.User
import com.liuguangqiang.irunning.data.service.TokenService
import com.liuguangqiang.irunning.data.service.UserService
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.Matchers
import org.mockito.Mockito
import org.mockito.Mockito.verify
import rx.Observable
import org.mockito.Mockito.`when` as _when

/**
 * Created by eric on 20/9/2017.
 */
class LoginPresenterTest {

    companion object {

        lateinit var tokenService: TokenService

        lateinit var userService: UserService

        lateinit var view: LoginContract.View

        lateinit var presenter: LoginPresenter

        @BeforeClass
        @JvmStatic
        fun setup() {
            tokenService = Mockito.mock(TokenService::class.java)
            userService = Mockito.mock(UserService::class.java)
            view = Mockito.mock(LoginContract.View::class.java)
            presenter = LoginPresenter(view, tokenService, userService)
        }
    }

    @Test
    fun testGetUser() {
        var user = User()
        user.username = "eric"
        user.id = "1"

        _when(userService.get(Matchers.anyString())).thenReturn(Observable.just(user))

        presenter.getUser("1")
        verify(userService).get("1")
        verify(view).onLoginSuccess(user)
    }

}