package com.liuguangqiang.irunning.login

import com.liuguangqiang.irunning.SyncAndroidSchedulersHook
import com.liuguangqiang.irunning.SyncJavaSchedulersHook
import com.liuguangqiang.irunning.act.login.LoginContract
import com.liuguangqiang.irunning.act.login.LoginPresenter
import com.liuguangqiang.irunning.data.entity.User
import com.liuguangqiang.irunning.data.service.TokenService
import com.liuguangqiang.irunning.data.service.UserService
import org.junit.Assert
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Matchers
import org.mockito.Mockito
import org.mockito.Mockito.verify
import rx.Observable
import rx.android.plugins.RxAndroidPlugins
import rx.plugins.RxJavaPlugins
import javax.inject.Inject
import org.mockito.Mockito.`when` as _when

/**
 * Created by eric on 20/9/2017.
 */
class LoginPresenterTest {

    companion object {

        @Inject lateinit var tokenService: TokenService

        @Inject lateinit var userService: UserService

        lateinit var view: LoginContract.View

        @Inject lateinit var presenter: LoginPresenter

        @BeforeClass
        @JvmStatic
        fun setup() {
            swithToSync()
            tokenService = Mockito.mock(TokenService::class.java)
            userService = Mockito.mock(UserService::class.java)
            view = Mockito.mock(LoginContract.View::class.java)
            presenter = LoginPresenter(view, tokenService, userService)
        }

        fun swithToSync() {
            RxAndroidPlugins.getInstance().registerSchedulersHook(SyncAndroidSchedulersHook())
            RxJavaPlugins.getInstance().registerSchedulersHook(SyncJavaSchedulersHook())
        }
    }

    @Test
    fun testGetUser() {
        var user = User()
        user.username = "eric"
        user.id = "1"

        _when(userService.get(Matchers.anyString())).thenReturn(Observable.just(user))

        presenter.getUser("1")
        var captor: ArgumentCaptor<User> = ArgumentCaptor.forClass(User::class.java)
        verify(userService).get("1")
        verify(view).onLoginSuccess(captor.capture())

        var result: User = captor.value
        Assert.assertEquals(result.id, "1")
        Assert.assertEquals(result.username, "eric")
    }

}