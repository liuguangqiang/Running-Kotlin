package com.liuguangqiang.irunning.act.login

import com.liuguangqiang.irunning.data.entity.User
import com.liuguangqiang.irunning.data.service.TokenService
import com.liuguangqiang.irunning.data.service.UserService
import com.liuguangqiang.support.utils.Logger
import rx.Observable
import rx.Observer
import javax.inject.Inject

/**
 * Created by Eric on 2017/7/11.
 */
class LoginPresenter : LoginContract.Presenter {

    @Inject lateinit var tokenService: TokenService

    @Inject lateinit var userService: UserService

    var view: LoginContract.View

    @Inject
    constructor(view: LoginContract.View, tokenService: TokenService, userService: UserService) {
        this.view = view
        this.tokenService = tokenService
        this.userService = userService
    }

    fun getUser(userId: String) {
        userService.get(userId).subscribe(object : Observer<User> {
            override fun onCompleted() {
            }

            override fun onError(p0: Throwable?) {
            }

            override fun onNext(user: User?) {
                view.onLoginSuccess(user!!)
            }
        })
    }

    override fun login(username: String, password: String) {
        view.showLoading()
        tokenService.post(username, password)
                .flatMap { token ->
                    try {
//                        LoginManager.instance.saveToken(token.token)
                        userService.get(token.user_id)
                    } catch (e: Exception) {
                        Observable.error<User>(Throwable("error"))
                    }
                }
                .subscribe(object : Observer<User> {
                    override fun onNext(user: User?) {
                        if (user != null) {
                            view.onLoginSuccess(user)
//                            LoginManager.instance.saveUser(user!!)
                        } else {
                            Logger.d("onError: user is null")
                        }
                    }

                    override fun onError(p0: Throwable?) {
                        Logger.d("onError:" + p0.toString())
                        view.onLoginFailed(p0!!)
                    }

                    override fun onCompleted() {
                    }
                })
    }

}