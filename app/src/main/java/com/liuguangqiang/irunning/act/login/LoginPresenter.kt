package com.liuguangqiang.irunning.act.login

import com.liuguangqiang.irunning.data.entity.Token
import com.liuguangqiang.irunning.data.model.TokenModel
import com.liuguangqiang.support.utils.Logger
import rx.Observer
import javax.inject.Inject

/**
 * Created by Eric on 2017/7/11.
 */
class LoginPresenter : LoginContract.Presenter {

    @Inject lateinit var model: TokenModel

    var view: LoginContract.View

    @Inject
    constructor(view: LoginContract.View) {
        this.view = view
    }

    override fun login(username: String, password: String) {
        Logger.d("login:" + username)
        model.post(username, password).subscribe(object : Observer<Token> {
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