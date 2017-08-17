package com.liuguangqiang.irunning.act.me

import android.os.Bundle
import com.liuguangqiang.irunning.R
import com.liuguangqiang.irunning.app.BaseFragment
import com.liuguangqiang.irunning.data.entity.User
import com.liuguangqiang.irunning.utils.LoginManager
import kotlinx.android.synthetic.main.fragment_me.*

/**
 * Created by Eric on 2017/6/15.
 */
class MeFragment : BaseFragment() {

    override fun getContentLayout(): Int {
        return R.layout.fragment_me
    }

    override fun onCreated(bundle: Bundle?) {
        if (LoginManager.instance.isLogin()) {
            bindUser(LoginManager.instance.user!!)
        }
    }

    fun bindUser(user: User) {
        tvUsername.text = user.username
    }

}