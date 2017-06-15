package com.liuguangqiang.irunning.app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by Eric on 2017/6/15.
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
    }

    abstract fun inject()

}