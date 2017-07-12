package com.liuguangqiang.irunning.act.login

import com.liuguangqiang.irunning.data.entity.Token
import com.liuguangqiang.irunning.data.service.TokenService
import com.liuguangqiang.support.utils.Logger
import rx.Observer
import javax.inject.Inject

/**
 * Created by Eric on 2017/7/11.
 */
class LoginPresenter : LoginContract.Presenter {

    @Inject lateinit var tokenService: TokenService

    var view: LoginContract.View

    @Inject
    constructor(view: LoginContract.View, tokenService: TokenService) {
        this.view = view
        this.tokenService = tokenService
    }

    override fun login(username: String, password: String) {
        Logger.d("login:" + username)
        tokenService.post(username, password).subscribe(object : Observer<Token> {
            override fun onCompleted() {
            }

            override fun onError(e: Throwable?) {
                Logger.d("login onError:" + e.toString())
            }

            override fun onNext(t: Token?) {
                Logger.d("onNext:" + t!!.user_id)
                view.onLoginSuccess()
            }
        })
    }

}