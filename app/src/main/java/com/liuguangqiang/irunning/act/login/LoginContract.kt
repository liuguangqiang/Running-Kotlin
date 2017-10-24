package com.liuguangqiang.irunning.act.login

import com.liuguangqiang.irunning.data.entity.User

/**
 * Created by Eric on 2017/7/11.
 */
interface LoginContract {

    interface View {
        fun showLoading()

        fun hideLoading()

        fun onLoginSuccess(user: User)

        fun onLoginFailed(t: Throwable)
    }

    interface Presenter {
        fun login(username: String, password: String)
    }

}