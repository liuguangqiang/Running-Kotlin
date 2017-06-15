package com.liuguangqiang.irunning.act.main

import android.util.Log
import javax.inject.Inject

/**
 * Created by Eric on 2017/5/18.
 */
class MainPresenter
@Inject
constructor(val view: MainContract.View): MainContract.Presenter {

    override fun test() {
        Log.d("running", "MainPresenter test")
    }

}