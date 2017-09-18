package com.liuguangqiang.irunning.act.login

/**
 * Created by Eric on 2017/7/11.
 */
interface LoginContract {

    interface View {
        fun showLoading()

        fun hideLoading()

        fun onLoginSuccess()

        fun onLoginFailed(t: Throwable)
    }

    interface Presenter {
        fun login(username: String, password: String)
    }

}