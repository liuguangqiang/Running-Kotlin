package com.liuguangqiang.irunning.utils

import com.bluelinelabs.logansquare.LoganSquare
import com.liuguangqiang.irunning.app.RunningApplication
import com.liuguangqiang.irunning.data.entity.User
import com.liuguangqiang.support.utils.Logger
import com.liuguangqiang.support.utils.PreferencesUtils

/**
 * Created by eric on 8/8/2017.
 */
class LoginManager {

    companion object {

        val PRE_NAME = "RUNNING"

        val PRE_TOKEN = "TOKEN"

        val PRE_USER = "USER"

        val instance = LoginManager()
    }

    var user: User? = null

    fun init() {
        var userJson: String = PreferencesUtils.getString(RunningApplication.context, PRE_NAME, PRE_USER)
        if (!userJson.isNullOrEmpty()) {
            user = LoganSquare.parse(userJson, User::class.java)
        }
    }

    fun saveToken(token: String) {
        PreferencesUtils.putString(RunningApplication.context, PRE_NAME, PRE_TOKEN, token)
    }

    fun getToken(): String {
        return PreferencesUtils.getString(RunningApplication.context, PRE_NAME, PRE_TOKEN)
    }

    fun saveUser(user: User) {
        this.user = user
        var userJson: String = LoganSquare.serialize(user)
        PreferencesUtils.putString(RunningApplication.context, PRE_NAME, PRE_USER, userJson)
    }

    fun isLogin(): Boolean {
        return user != null
    }

    fun logout() {
        this.user = null
        PreferencesUtils.putString(RunningApplication.context, PRE_NAME, PRE_TOKEN, "")
        PreferencesUtils.putString(RunningApplication.context, PRE_NAME, PRE_USER, "")
    }

}