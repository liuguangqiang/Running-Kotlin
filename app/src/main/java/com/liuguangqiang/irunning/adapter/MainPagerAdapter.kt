package com.liuguangqiang.irunning.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.liuguangqiang.irunning.act.me.MeFragment
import com.liuguangqiang.irunning.act.step.StepFragment

/**
 * Created by Eric on 2017/6/15.
 */
class MainPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(arg0: Int): Fragment? {
        when (arg0) {
            0 -> return StepFragment()
            1 -> return MeFragment()
            2 -> return MeFragment()
            else -> return null
        }
    }

    override fun getCount(): Int {
        return 3
    }
}

