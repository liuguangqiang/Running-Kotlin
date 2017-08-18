package com.liuguangqiang.irunning.act.main

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import com.liuguangqiang.irunning.R
import com.liuguangqiang.irunning.adapter.MainPagerAdapter
import com.liuguangqiang.irunning.app.BaseActivity
import com.liuguangqiang.irunning.service.RunningService
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import com.liuguangqiang.irunning.extension.disableShiftMode
import com.liuguangqiang.irunning.extension.setScrollable
import com.liuguangqiang.irunning.extension.setStatusBarColor

class MainActivity : BaseActivity(), MainContract.View {

    @Inject lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        presenter.test()
        startService(Intent(this, RunningService::class.java))
    }

    override fun inject() {
        DaggerMainComponent.builder().mainModule(MainModule(this)).build().inject(this)
    }

    override fun testView() {
        TODO("not implemented")
    }

    private fun initViews() {
        bottomMenu.disableShiftMode()
        bottomMenu.setOnNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.action_step -> {
                    mainViewPager.setCurrentItem(0, true)
                    setStatusBarColor(R.color.colorPrimary)
                }
                R.id.action_sport -> {
                    mainViewPager.setCurrentItem(1, true)
                    setStatusBarColor(R.color.white)
                }
                R.id.action_me -> {
                    mainViewPager.setCurrentItem(2, true)
                    setStatusBarColor(R.color.white)
                }
            }
            false
        }

        var adapter = MainPagerAdapter(supportFragmentManager)
        mainViewPager.adapter = adapter
        mainViewPager.offscreenPageLimit = 3
        mainViewPager.setScrollable(false)
    }

}
