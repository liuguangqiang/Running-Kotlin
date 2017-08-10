package com.liuguangqiang.irunning.act.login

import com.liuguangqiang.irunning.data.entity.User
import com.liuguangqiang.irunning.data.service.TokenService
import com.liuguangqiang.irunning.data.service.UserService
import com.liuguangqiang.irunning.utils.LoginManager
import com.liuguangqiang.support.utils.Logger
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

    override fun login(username: String, password: String) {
        view.showLoading()
        tokenService.post(username, password)
                .flatMap { token ->
                    LoginManager.instance.saveToken(token.token)
                    userService.get(token.user_id)
                }
                .subscribe(object : Observer<User> {
                    override fun onNext(user: User?) {
                        view.onLoginSuccess()
                        LoginManager.instance.saveUser(user!!)
                    }

                    override fun onError(p0: Throwable?) {
                    }

                    override fun onCompleted() {
                        view.hideLoading()
                    }
                })
    }

}