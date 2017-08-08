package com.liuguangqiang.irunning.utils

import com.liuguangqiang.irunning.app.RunningApplication
import com.liuguangqiang.support.utils.PreferencesUtils

/**
 * Created by eric on 8/8/2017.
 */
class LoginManager {

    fun saveToken(token: String) {
        PreferencesUtils.putString(RunningApplication.context, PRE_NAME, PRE_TOKEN, token)
    }

    fun getToken(): String {
        return PreferencesUtils.getString(RunningApplication.context, PRE_NAME, PRE_TOKEN)
    }

    companion object {

        val PRE_NAME = "RUNNING"

        val PRE_TOKEN = "TOKEN"

        val instance = LoginManager()
    }

}