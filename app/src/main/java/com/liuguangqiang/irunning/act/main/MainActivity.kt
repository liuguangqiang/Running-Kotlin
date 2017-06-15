package com.liuguangqiang.irunning.act.main

import android.content.Intent
import android.os.Bundle
import com.liuguangqiang.irunning.R
import com.liuguangqiang.irunning.app.BaseActivity
import com.liuguangqiang.irunning.di.component.DaggerMainComponent
import com.liuguangqiang.irunning.di.module.MainModule
import com.liuguangqiang.irunning.service.RunningService
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {

    @Inject lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.test()
        startService(Intent(this, RunningService::class.java))
        tvTest.setText(R.string.app_name)
    }

    override fun inject() {
        DaggerMainComponent.builder().mainModule(MainModule(this)).build().inject(this)
    }

    override fun testView() {
        TODO("not implemented")
    }
}
